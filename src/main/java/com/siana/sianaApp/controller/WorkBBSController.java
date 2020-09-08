package com.siana.sianaApp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.siana.sianaApp.common.controller.BaseController;
import com.siana.sianaApp.common.exception.FileException;
import com.siana.sianaApp.common.vo.ResultMap;
import com.siana.sianaApp.service.CommentService;
import com.siana.sianaApp.service.WorkBBSService;

@Controller
@RequestMapping("servlet/bbs")
public class WorkBBSController extends BaseController{
	@Autowired
	private WorkBBSService workBBSService;

	@Autowired
	private CommentService commentService;

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
	@RequestMapping("main")
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
	@RequestMapping("workbbs")
	public String workbbsPage() {
		return "bbs/workbbs/bbsList.part";
	}

	/**
	 * NAME : workbbsDetailPage
	 * DESC : 업무게시판 상세 이동
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("workbbsDetail")
	public String workbbsDetailPage() {
		return "bbs/workbbs/bbsDetail.part";
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
	@RequestMapping("workbbsWrite")
	public String workbbsWritePage() {
		return "bbs/workbbs/bbsWrite.part";
	}

	/**
	 * NAME : workbbsModifyPage
	 * DESC : 업무게시판 수정 페이지 이동
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @return
	 * </pre>
	 */
	@RequestMapping("workbbsModify")
	public String workbbsModifyPage() {
		return "bbs/workbbs/bbsWrite.part";
	}

	/*********[get Data]********************************************************************/

	/**
	 * NAME : getWorkbbsList
	 * DESC : 업무공유 게시판 목록 조회
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("getWorkbbsList")
	@ResponseBody
	public ResultMap getWorkbbsList(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(workBBSService.getWorkBBSList(super.getParamMap(request)));
	}

	/**
	 * NAME : getWorkbbsDetail
	 * DESC : 게시판 상세 조회
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("getWorkbbsDetail")
	@ResponseBody
	public ResultMap getWorkbbsDetail(HttpServletRequest request , HttpServletResponse response) {
		return new ResultMap(workBBSService.getWorkBBSDetail(super.getParamMap(request)));
	}

	/**
	 * NAME : workbbsWriteProc
	 * DESC : 업무공유 게시판 작성
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	@RequestMapping("workbbsWriteProc")
	@ResponseBody
	public ResultMap workbbsWriteProc(MultipartHttpServletRequest request , HttpServletResponse response) throws FileException {
		Map paramMap = super.getParamMap(request);
		return new ResultMap(workBBSService.insertWorkBBS(paramMap));
	}

	/**
	 * NAME : workbbsUpdateProc
	 * DESC : 게시물 수정
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	@RequestMapping("workbbsUpdateProc")
	@ResponseBody
	public ResultMap workbbsUpdateProc(MultipartHttpServletRequest request , HttpServletResponse response) throws FileException {
		Map paramMap = super.getParamMap(request);
		return new ResultMap(workBBSService.updateBBS(paramMap));
	}

	/**
	 * NAME : workbbsDeleteProc
	 * DESC : 게시물 삭제
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	@RequestMapping("workbbsDeleteProc")
	@ResponseBody
	public ResultMap workbbsDeleteProc(HttpServletRequest request , HttpServletResponse response) throws FileException {
		Map paramMap = super.getParamMap(request);
		return new ResultMap(workBBSService.deleteBBS(paramMap));
	}

	/**
	 * NAME : updateStatus
	 * DESC : 게시물 상태 업데이트
	 * DATE : 2020. 7. 8.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("updateStatus")
	@ResponseBody
	public ResultMap updateStatus(HttpServletRequest request , HttpServletResponse response) {
		Map params = super.getParamMap(request);
		return new ResultMap(workBBSService.updateBBSStatus(params));
	}

	/**
	 * NAME : registComment
	 * DESC : 게시물 코멘트 등록
	 * DATE : 2020. 7. 8.
	 * <pre>
	 * @auther jyh
	 * @param request
	 * @param response
	 * @return
	 * </pre>
	 */
	@RequestMapping("registComment")
	@ResponseBody
	public ResultMap registComment(HttpServletRequest request , HttpServletResponse response) {
		Map params = super.getParamMap(request);
		return new ResultMap(commentService.insertComment(params));
	}
}
