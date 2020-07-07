package com.siana.workshareapp.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siana.workshareapp.common.service.CommonService;
import com.siana.workshareapp.common.vo.ResultMap;

@Controller
public class CommonController extends BaseController{

	@Autowired
	private CommonService commonService;


	/**
	 * NAME : getCommCode
	 * DESC : 공통코드 조회
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @return
	 * </pre>
	 */
	@RequestMapping("getCode")
	@ResponseBody
	public ResultMap getCommCode(HttpServletRequest request) {
		return new ResultMap(commonService.selectCommCdList(super.getParamMap(request)));
	}

}
