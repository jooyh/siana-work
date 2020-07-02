package com.siana.workshareapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siana.workshareapp.common.controller.BaseController;
import com.siana.workshareapp.common.vo.ResultMap;
import com.siana.workshareapp.service.AccountService;

@Controller
@RequestMapping("bbs/account")
public class AccountController extends BaseController{

	@Autowired
	private AccountService accountService;

	@RequestMapping("/test")
	@ResponseBody
	public ResultMap test(HttpServletRequest request, HttpServletResponse response) {
		return new ResultMap(accountService.test(super.getParamMap(request)));
	}
}
