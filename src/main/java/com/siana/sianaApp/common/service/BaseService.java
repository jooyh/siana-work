package com.siana.sianaApp.common.service;

import java.util.Map;

import com.siana.sianaApp.common.utils.Pagination;

public class BaseService {

	protected String getStatement (String nameSpace,String methodNm) {
		StringBuffer sb = new StringBuffer().append(nameSpace).append('.').append(methodNm);
		return sb.toString();
	}

	protected boolean validParams(Map params,String ... validKeys) {
		return true;
	}

	protected Pagination getPageInfo(Map params , int totCnt) {
		Integer page = (Integer) params.get("page") == null? 1:(Integer) params.get("page");
		Integer range = (Integer) params.get("range") == null? 1:(Integer) params.get("range");
		Pagination pageInfo = new Pagination(totCnt,page);
		return pageInfo;
	}
}
