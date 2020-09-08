package com.siana.sianaApp.common.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.siana.sianaApp.common.service.CommonService;
import com.siana.sianaApp.common.vo.ResultMap;

@Controller
@RequestMapping("servlet/bbs")
public class CommonController extends BaseController{

	@Autowired
	private CommonService commonService;

	@Value("${file.uploadBaseDir}")
	private String uploadBaseDir;


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


	@RequestMapping(value="/fileDownload", method = RequestMethod.GET)
	public ModelAndView download(HttpServletRequest request,
			@RequestParam("path")String path
			,@RequestParam("snm") String sNm
			,@RequestParam("onm") String oNm) {

		String contextPath = request.getSession().getServletContext().getRealPath("/");

		File file = new File(contextPath+path+sNm);
		File rFile = new File(contextPath+path+oNm);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("download");
		mav.addObject("downloadFile",file);
		mav.addObject("realFileName",rFile);
		return mav;
	}
}
