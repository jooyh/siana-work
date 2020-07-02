package com.siana.workshareapp.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.siana.workshareapp.common.exception.FileException;
import com.siana.workshareapp.common.utils.FileUtil;

public class BaseController {

	private Map paramMap;

	@Autowired
	private FileUtil fileUtil;

	public Map getParamMap(HttpServletRequest request) {
		this.setParamMap(request.getAttribute("params"));
		return paramMap;
	}

	private void setParamMap(Object object) {
		this.paramMap = (Map) object;
	}

	public List getFileMap(MultipartHttpServletRequest request) throws FileException {
		return fileUtil.uploadFiles(request);
	}

}
