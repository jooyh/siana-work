package com.siana.workshareapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.siana.workshareapp.common.controller.BaseController;
import com.siana.workshareapp.common.exception.FileException;
import com.siana.workshareapp.common.service.CommonService;
import com.siana.workshareapp.common.vo.ResultMap;
import com.siana.workshareapp.service.WorkBBSService;

@Controller
@RequestMapping()
public class WorkBBSController extends BaseController{
	@Autowired
	private WorkBBSService workBBSService;

	@Autowired
	private CommonService commonService;

	/*********[get Page]********************************************************************/
	/**
	 * NAME : bbsMainPage
	 * DESC : 게시판 메인 페이지 이동
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("/bbs/main")
	public String bbsMainPage(HttpServletRequest request) {
		return "bbs/main.part";
	}

	/**
	 * NAME : workbbsPage
	 * DESC : 업무게시판 페이지 이동
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("/bbs/workbbs")
	public String workbbsPage() {
		return "bbs/workbbs/bbsList.part";
	}

	/**
	 * NAME : workbbsWritePage
	 * DESC : 업무게시판 등록 페이지 이동
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("/bbs/workbbsWrite")
	public String workbbsWritePage() {
		return "bbs/workbbs/bbsWrite.part";
	}

	/*********[get Data]********************************************************************/

	@RequestMapping("/bbs/getWorkbbsList")
	@ResponseBody
	public ResultMap getWorkbbsList(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(workBBSService.getWorkBBSList(super.getParamMap(request)));
	}

	@RequestMapping("/bbs/workbbsWriteProc")
	@ResponseBody
	public Map workbbsWriteProc(MultipartHttpServletRequest request , HttpServletResponse response) throws FileException {
		Map paramMap = super.getParamMap(request);
		workBBSService.insertWorkBBS(paramMap);
		return paramMap;
	}
}
