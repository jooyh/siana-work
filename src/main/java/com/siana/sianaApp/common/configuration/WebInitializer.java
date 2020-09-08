package com.siana.sianaApp.common.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		this.addDispatcherServlet(servletContext);
		this.addUtf8CharacterEncodingFilter(servletContext);
//		this.addXssEscapeServletFilter(servletContext);
//		this.addSimpleCORSFilter(servletContext);
	} /** * Dispatcher Servlet 을 추가한다.
		* CORS 를 가능하게 하기 위해서 dispatchOptionsRequest 설정을 true 로 한다.
		* * @param servletContext */
	private void addDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.getEnvironment().addActiveProfile("production");
		applicationContext.register(ServletConfiguration.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setInitParameter("dispatchOptionsRequest", "true");
	}
	// CORS 를 위해서 option request 도 받아들인다.
	/** * UTF-8 캐릭터 인코딩 필터를 추가한다. * @param servletContext */
	private void addUtf8CharacterEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, false, "/*");
	}

	private void addSimpleCORSFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter("simpleCORSFilter",SimpleCORSFilter.class);
		filter.addMappingForUrlPatterns(null, false, "/*");
	}

	private void addXssEscapeServletFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic filter = servletContext.addFilter("xssEscapeServletFilter",XssEscapeServletFilter.class);
		filter.addMappingForUrlPatterns(null, false, "/*");
	}

}