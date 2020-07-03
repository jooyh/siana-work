package com.siana.workshareapp.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.siana.workshareapp.common.exception.FileException;
import com.siana.workshareapp.common.utils.FileUtil;

public class BaseController {

	private Map paramMap;
//	private static final String SESSION_KEY = "userInfo";

	@Autowired
	private FileUtil fileUtil;

	/**
	 * NAME : getParamMap
	 * DESC : Interceptor 에서 정리된 파라미터를 Map 형식으로 전달
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param addSession
	 * @return
	 * </pre>
	 */
	public Map getParamMap(HttpServletRequest request) {
		this.setParamMap(request.getAttribute("params"));
		return paramMap;
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

}
