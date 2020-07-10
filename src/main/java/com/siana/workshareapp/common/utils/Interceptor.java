package com.siana.workshareapp.common.utils;

import java.util.Map;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

	private static final String USER_SESSION_KEY = "userInfo";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();

		HttpSession session = request.getSession();

//		if(!uri.contains("/servlet/bbs/login")) {
			if(session.getAttribute(USER_SESSION_KEY) == null) {
				response.sendRedirect("/servlet/login");
			}
			if(uri.contains("/servlet/admin/")) {
				Map userInfo = (Map) session.getAttribute(USER_SESSION_KEY);
				if(!"A".equals(userInfo.get("status"))) {
					response.sendRedirect("/servlet/login");
					throw new Exception("접근권한이 없습니다.");
				}
			}
//		}

		logger.debug("===================       START       ===================");
		logger.debug(" Request URI \t:  " + uri);
		String ipAddr = request.getRemoteAddr();
//		this.convertRequestToMap(request);
//		Map params = (Map) request.getAttribute("params");
//		logger.debug(" params \t:  " + params.toString());
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

//	/**
//	 * NAME : convertRequestToMap
//	 * DESC : 서버 요청 파라미터를 Map 형태로 변환
//	 * DATE : 2020. 4. 3.
//	 * <pre>
//	 * @auther SIWAN
//	 * @param request 서버로 전달된 요청
//	 * @return paramMap 맵형식으로 변환된 요청 파라미터
//	 * </pre>
//	 */
//	private static final String JSON_STRING_KEY = "params";
//	private void convertRequestToMap(HttpServletRequest request){
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		StringBuffer json = new StringBuffer();
//		String line = null;
//
//		try {
//			BufferedReader reader = request.getReader();
//			while((line = reader.readLine()) != null) {
//				json.append(line);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String jsonString = json.toString();
//		if(StringUtils.hasText(jsonString)) { // JSON string으로 전달 한 경우
//			try {
//				ObjectMapper om = new ObjectMapper();
//				paramMap = om.readValue(jsonString, new TypeReference<Map<String, Object>>(){});
//				for( String key : paramMap.keySet() ){
//					if(paramMap.get(key) != null) {
//						String clsNm = paramMap.get(key).getClass().getName();
//						if("LIST".contains(clsNm.toUpperCase())) {
//							List<Map<String,Object>> cnvtList =
//									om.readValue(
//											paramMap.get(key).toString(), new TypeReference<List<Map<String,Object>>>(){}
//									);
//							paramMap.put(key,cnvtList);
//						}
//					}
//				}
//			} catch (JsonParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		Map sessionMap = (Map) request.getSession().getAttribute("userInfo");
//		paramMap.put("session", sessionMap);
//		request.setAttribute("params", paramMap);
//	}
}
