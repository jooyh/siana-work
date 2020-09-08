package com.siana.sianaApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.siana.sianaApp.common.controller.BaseController;

@Controller
@RequestMapping("servlet/admin/menu")
public class MenuController extends BaseController{

	protected static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	/**
	 * NAME : menu
	 * DESC : 메뉴관리 페이지 이동
	 * DATE : 2020. 7. 10.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("menuManage")
	public String manageForMenu() {
		return "menu/menuManage.part";
	}

}
