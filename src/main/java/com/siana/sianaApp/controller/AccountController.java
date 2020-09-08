package com.siana.sianaApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siana.sianaApp.common.controller.BaseController;
import com.siana.sianaApp.common.vo.ResultMap;
import com.siana.sianaApp.service.AccountService;

/**
 * @author jyh
 *
 */
@Controller
@RequestMapping("servlet/admin/account")
public class AccountController extends BaseController{

	protected static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	/**
	 * NAME : registUserPage
	 * DESC : 사용자 등록 페이지 이동
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @return registUser.jsp
	 * </pre>
	 */
	@RequestMapping("registUser")
	public String registUserPage() {
		return "account/registUser.part";
	}

	/**
	 * NAME : userListPage
	 * DESC : 사용자 목록페이지 이동
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("userList")
	public String userListPage() {
		return "account/userList.part";
	}

	/**
	 * NAME : updateUserPage
	 * DESC : 사용자 수정 페이지
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @return updateUser.jsp
	 * </pre>
	 */
	@RequestMapping("updateUser")
	public String updateUserPage() {
		return "account/registUser.part";
	}

	/******[ Data Actions ]**************************************************************************************/

	/**
	 * NAME : registUserProc
	 * DESC : 사용자 등록
	 * DATE : 2020. 7. 10.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("registUserProc")
	@ResponseBody
	public ResultMap registUserProc(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(accountService.registUser(super.getParamMap(request)));
	}


	/**
	 * NAME : checkUserId
	 * DESC : 사용자 계정 중복체크
	 * DATE : 2020. 7. 10.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("checkUserId")
	@ResponseBody
	public ResultMap checkUserId (HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(accountService.duplicatedCheckUserId(super.getParamMap(request)));
	}

	/**
	 * NAME : getUserList
	 * DESC : 사용자 목록조회
	 * DATE : 2020. 7. 10.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	public ResultMap getUserList(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(accountService.selectUserList(super.getParamMap(request)));
	}

	/**
	 * NAME : getUserInfo
	 * DESC : 사용자 정보조회
	 * DATE : 2020. 7. 10.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public ResultMap getUserInfo(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(accountService.selectUserInfo(super.getParamMap(request)));
	}

	/**
	 * NAME : updateUserProc
	 * DESC : 사용자 정보 수정
	 * DATE : 2020. 7. 10.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("updateUserProc")
	@ResponseBody
	public ResultMap updateUserProc(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(accountService.updateUser(super.getParamMap(request)));
	}

}
