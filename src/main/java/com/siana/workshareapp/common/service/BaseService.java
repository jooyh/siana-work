package com.siana.workshareapp.common.service;

import java.util.Map;

public class BaseService {

	protected String getStatement (String nameSpace,String methodNm) {
		StringBuffer sb = new StringBuffer().append(nameSpace).append('.').append(methodNm);
		return sb.toString();
	}

	protected boolean validParams(Map params,String ... validKeys) {
		return true;
	}
}
