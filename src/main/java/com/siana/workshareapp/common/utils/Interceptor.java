package com.siana.workshareapp.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Interceptor extends HandlerInterceptorAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	private static final String ADM_SESSION_KEY = "admSession";
	private static final String ADM_MENU_KEY = "admMenu";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.convertRequestToMap(request);
		String uri = request.getRequestURI();
//		if(!uri.contains("/admin/login")) {
//			if(request.getSession().getAttribute(ADM_SESSION_KEY) == null) {
//				response.sendRedirect("/admin/login");
//				return super.preHandle(request, response, handler);
//			};
//
//			List<Map<String,String>> menuList = (List) request.getSession().getAttribute(ADM_MENU_KEY);
//			for(Map menu : menuList) {
//				if(uri.equals(menu.get("menuUrl"))) {
//					request.getSession().setAttribute("nowMenuNm", menu.get("menuNm"));
//				}
//			}
//		};

		logger.debug("===================       START       ===================");
		logger.debug(" Request URI \t:  " + uri);
		String ipAddr = request.getRemoteAddr();
		Map params = (Map) request.getAttribute("params");
		logger.debug(" params \t:  " + params.toString());

//		String authToken = (String) params.get("authToken");
//		authService.chkAuthToken(authToken);

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		logger.debug("===================       END       ===================");
	}

	/**
	 * NAME : convertRequestToMap
	 * DESC : 서버 요청 파라미터를 Map 형태로 변환
	 * DATE : 2020. 4. 3.
	 * <pre>
	 * @auther SIWAN
	 * @param request 서버로 전달된 요청
	 * @return paramMap 맵형식으로 변환된 요청 파라미터
	 * </pre>
	 */
	private static final String JSON_STRING_KEY = "params";
	private void convertRequestToMap(HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		StringBuffer json = new StringBuffer();
		String line = null;

		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String jsonString = json.toString();
		if(StringUtils.hasText(jsonString)) { // JSON string으로 전달 한 경우
			try {
				ObjectMapper om = new ObjectMapper();
				paramMap = om.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
				for( String key : paramMap.keySet() ){
					if(paramMap.get(key) != null) {
						String clsNm = paramMap.get(key).getClass().getName();
						if("LIST".contains(clsNm.toUpperCase())) {
							List<Map<String,Object>> cnvtList =
									om.readValue(
											paramMap.get(key).toString(), new TypeReference<List<Map<String,Object>>>(){}
									);
							paramMap.put(key,cnvtList);
						}
					}
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {// JSON string으로 전달 하지 않은 경우
			Enumeration enums = request.getParameterNames();
			while(enums.hasMoreElements()){
				String paramName = (String)enums.nextElement();
				String[] parameters = request.getParameterValues(paramName);
				// Parameter가 배열일 경우
				if(parameters.length > 1){
					paramMap.put(paramName, parameters);
				// Parameter가 배열이 아닌 경우
				}else{
					paramMap.put(paramName, parameters[0]);
				}
			}
		}
//		Map sessionMap = (Map) request.getSession().getAttribute(SESSION_USER_INFO_KEY);
//		paramMap.put(SESSION_USER_INFO_KEY, sessionMap);
		request.setAttribute("params", paramMap);
	}
}
