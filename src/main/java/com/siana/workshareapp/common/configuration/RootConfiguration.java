package com.siana.workshareapp.common.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.siana.workshareapp.common.utils.FileUtil;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

@Configuration
public class RootConfiguration {

	private static final String APP_CONFIG_FILE_PATH = "props/siana.properties";

	@Autowired
    private ApplicationContext applicationContext;

	@Value("${db.driverClass}")
	private Class jdbcDriverClassName;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String userName;
	@Value("${db.password}")
	private String password;

	@Bean
	@Order(0)
	public static final PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setOrder(0);
		ppc.setLocations(new Resource[] { new ClassPathResource(APP_CONFIG_FILE_PATH) });
		return ppc;
	}

	@Bean
	public DataSource dataSourceSpied() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(this.jdbcDriverClassName);
		dataSource.setUrl(this.url);
		dataSource.setUsername(this.userName);
		dataSource.setPassword(this.password);
		return dataSource;
	}
	@Bean
	public Log4jdbcProxyDataSource dataSource() {
		DataSource ds = this.dataSourceSpied();
		Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource(ds);
		Log4JdbcCustomFormatter logFormatter = new Log4JdbcCustomFormatter();
		logFormatter.setLoggingType(LoggingType.MULTI_LINE);
		logFormatter.setSqlPrefix("SQL         :  ");
		dataSource.setLogFormatter(logFormatter);
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		 sqlSessionFactoryBean.setMapperLocations(applicationContext
//				 .getResources("classpath*:mappers/*.xml"));
				 .getResources("classpath*:com/siana/workshareapp/**/mappers/*.xml"));
		return sqlSessionFactoryBean;
	}

	 @Bean
	 public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		 SqlSessionTemplate session = new SqlSessionTemplate(sqlSessionFactory);
		 return session;
	 }

	 @Bean
	 public ShaPasswordEncoder shaPasswordEncoder() {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(512);
		shaPasswordEncoder.setEncodeHashAsBase64(true);
		return shaPasswordEncoder;
	 }

	 @Bean
	 public CommonsMultipartResolver multipartResolver() {
		 CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		 multipartResolver.setMaxUploadSize(100000000);
		 multipartResolver.setMaxInMemorySize(100000000);
		 return multipartResolver;
	 }

	 @Bean
	 public FileUtil fileUtil() {
		 return new FileUtil();
	 }

//	 @Bean
//	 public MapperScannerConfigurer mapperScannerConfigurer() {
//		 MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		 mapperScannerConfigurer.setBasePackage("com.study.market.*.dao");
//		 return mapperScannerConfigurer;
//	 }
}