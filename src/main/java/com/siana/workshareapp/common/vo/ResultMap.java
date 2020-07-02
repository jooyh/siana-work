package com.siana.workshareapp.common.vo;

import java.util.HashMap;

import javax.annotation.Resource;

@Resource
@SuppressWarnings("serial")
public class ResultMap extends HashMap<String,Object>{

	private int code = 0;
	private String msg = "";

	public ResultMap(Object body) {
		this.put("result", body);
		this.put("code", code);
		this.put("msg", msg);
	}
	public ResultMap(Object result, int code) {
		this.code = code;
		this.put("result", result);
		this.put("code", this.code);
		this.put("msg", msg);
	}
	public ResultMap(Object result, int code, String msg) {
		this.code = code;
		this.msg = msg;
		this.put("result", result);
		this.put("code", this.code);
		this.put("msg", this.msg);
	} 

}
