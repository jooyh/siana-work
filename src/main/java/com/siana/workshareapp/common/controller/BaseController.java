package com.siana.workshareapp.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siana.workshareapp.common.exception.FileException;
import com.siana.workshareapp.common.utils.FileUtil;

public class BaseController {

	private Map paramMap;
//	private static final String SESSION_KEY = "userInfo";

	@Autowired
	private FileUtil fileUtil;

	/**
	 * NAME : getParamMap
	 * DESC : HttpServletRequest 로 전달받은 파라미터를 Map 형식으로 전달
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param addSession
	 * @return
	 * </pre>
	 */
	public Map getParamMap(HttpServletRequest request) {
		this.setParamMap(this.convertJsonRequestToMap(request));
		return paramMap;
	}

	/**
	 * NAME : getParamMap
	 * DESC : MultipartHttpServletRequest 로 전달받은 파라미터를 Map 형식으로 전달 (file 업로드 포함)
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	public Map getParamMap(MultipartHttpServletRequest request) throws FileException{
		Map parameterMap = convertRequestToMap(request);
		List files = this.getFileMap(request);
		parameterMap.put("files",files);
		return parameterMap;
	}

	/**
	 * NAME : getParamMap
	 * DESC : DESC : Interceptor 에서 정리된 파라미터를 Map 형식으로 전달 (세션정보 포함)
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return
	 * </pre>
	 */
//	public Map getParamMap(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Map sessionInfo = (Map) session.getAttribute(SESSION_KEY);
//		this.setParamMap(request.getAttribute("params"));
//		this.paramMap.put("session",sessionInfo);
//		return paramMap;
//	}

	private void setParamMap(Object object) {
		this.paramMap = (Map) object;
	}

	/**
	 * NAME : getFileMap
	 * DESC : 파일 업로드 후 파일정보를 Map으로 전달
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	public List getFileMap(MultipartHttpServletRequest request) throws FileException {
		return fileUtil.uploadFiles(request);
	}

	private Map convertRequestToMap(HttpServletRequest request) {
		Map parameterMap = new HashMap();
		Enumeration enums = request.getParameterNames();
		while(enums.hasMoreElements()){
			String paramName = (String)enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);
			if(parameters.length > 1){
				parameterMap.put(paramName, parameters);
			}else{
				parameterMap.put(paramName, parameters[0]);
			}
		}
		Map sessionMap = (Map) request.getSession().getAttribute("userInfo");
		parameterMap.put("session", sessionMap);
		return parameterMap;
	}

	/**
	 * NAME : convertJsonRequestToMap
	 * DESC : 서버로 요청된 JSON string 파라미터를 Map 형태로 변환
	 * DATE : 2020. 4. 3.
	 * <pre>
	 * @auther SIWAN
	 * @param request 서버로 전달된 요청
	 * @return paramMap 맵형식으로 변환된 요청 파라미터
	 * </pre>
	 */
	private Map convertJsonRequestToMap(HttpServletRequest request){
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

		Map sessionMap = (Map) request.getSession().getAttribute("userInfo");
		paramMap.put("session", sessionMap);
//		request.setAttribute("params", paramMap);
		return paramMap;
	}

}
