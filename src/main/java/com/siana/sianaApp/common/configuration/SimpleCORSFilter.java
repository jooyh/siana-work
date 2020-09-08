package com.siana.sianaApp.common.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * FILE NAME   : SimpleCORSFilter.java
 * PACKAGE     : com.study.market.configuration
 * PROJECT     : market
 * CREATE DATE : 2020. 4. 21.
 * CREATE BY   : SIWAN
 * HISTORY =====================================
 * [ DATE ]       [ NAME ]     [ DESC ]
 * 2020. 4. 21.     SIWAN       최초작성
 */
public class SimpleCORSFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		servletResponse.setHeader("Access-Control-Max-Age", "3600");
		servletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Accept");
		/*여기의 *을 내가 허용하고 싶은 특정 도메인으로 바꾸면 설정한 도메인에 한에서만 크로스 도메인을 허용하게된다. 여러 도메인의 경우 여러번 설정하면된다. */
		servletResponse.setHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(request, servletResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

