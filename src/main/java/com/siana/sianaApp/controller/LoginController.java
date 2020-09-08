package com.siana.sianaApp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siana.sianaApp.common.controller.BaseController;
import com.siana.sianaApp.common.vo.ResultMap;
import com.siana.sianaApp.service.AccountService;

@Controller
@RequestMapping("servlet")
public class LoginController extends BaseController{

	protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AccountService accountService;

	/******[ Page Actions ]**************************************************************************************/
	/**
	 * NAME : loginPage
	 * DESC : 로그인 페이지 이동
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @return login.jsp
	 * </pre>
	 */
	@RequestMapping("login")
	public String loginPage() {
		return "login.page";
	}

	/**
	 * NAME : loginProc
	 * DESC : 입력받은 ID , PW 를 통해 사용자 정보를 Session에 저장 후 조회 결과 리턴
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return ResultMap
	 * </pre>
	 */
	@RequestMapping("loginProc")
	@ResponseBody
	public ResultMap loginProc(HttpServletRequest request , HttpServletResponse response) {
		Map userInfo = accountService.login(super.getParamMap(request));
		HttpSession session = request.getSession();
		session.setAttribute("userInfo",userInfo);
		boolean result = (userInfo != null && !userInfo.isEmpty());
		return new ResultMap(result);
	}

	@RequestMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		response.sendRedirect("/servlet/login");
	}
}
