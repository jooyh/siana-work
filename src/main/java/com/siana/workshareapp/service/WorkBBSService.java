package com.siana.workshareapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siana.workshareapp.common.exception.FileException;
import com.siana.workshareapp.common.service.BaseService;
import com.siana.workshareapp.common.service.CommonService;
import com.siana.workshareapp.common.utils.Pagination;

@Service
public class WorkBBSService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CommentService commentService;

	/**
	 * NAME : getWorkBBSList
	 * DESC : 업무게시판 목록 조회
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public Map getWorkBBSList(Map params) {
		String statementForTotCnt= super.getStatement(this.getClass().getSimpleName(),"getWorkBBSTotCnt");
		int totCnt = sqlSession.selectOne(statementForTotCnt,params);

		Integer page = (Integer) params.get("page") == null? 1:(Integer) params.get("page");
		Integer range = (Integer) params.get("range") == null? 1:(Integer) params.get("range");
		Pagination pageInfo = new Pagination(totCnt,page);
		params.put("pageInfo",pageInfo);

		String statement = super.getStatement(this.getClass().getSimpleName(),"getWorkBBSList");
		List bbsList = sqlSession.selectList(statement,params);

		Map result = new HashMap();
		result.put("pageInfo",pageInfo);
		result.put("datas", bbsList);
		return result;
	}

	/**
	 * NAME : getWorkBBSDetail
	 * DESC : 업무게시판 상세 조회
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public Map getWorkBBSDetail(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(),"getWorkBBSDetail");
		Map data = sqlSession.selectOne(statement,params);
		data.put("files",commonService.selectFiles(params));
		data.put("comments",commentService.selectComment(params));
		data.put("hist",this.selectBBSHistList(params));
		return data;
	}

	/**
	 * NAME : insertWorkBBS
	 * DESC : 게시물 등록
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	public boolean insertWorkBBS(Map params) throws FileException {
		String bbsStatement = super.getStatement(this.getClass().getSimpleName(),"insertWorkBBS");
		boolean insertFlag = sqlSession.insert(bbsStatement,params) == 1;
		if(insertFlag) {
			List<Map> files = (List) params.get("files");
			for(Map file : files) {
				file.put("session",params.get("session"));
				file.put("bbsId",params.get("bbsId"));
				if(!commonService.insertFile(file)) {
					throw new FileException("파일정보 저장중 오류가 발생하였습니다.");
				}
			}
		}
		return insertFlag;
	}

	/**
	 * NAME : updateBBSStatus
	 * DESC : 게시물 상태변경
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param param
	 * @return
	 * </pre>
	 */
	public boolean updateBBSStatus(Map param) {
		String statementForHist = super.getStatement(this.getClass().getSimpleName(),"insertBBSHistory");
		sqlSession.insert(statementForHist,param);
		String statement = super.getStatement(this.getClass().getSimpleName(),"updateBBSStatus");
		return sqlSession.update(statement,param) > 0;
	}

	/**
	 * NAME : selectBBSHistList
	 * DESC : 게시판 이력 목록 조회
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param param
	 * @return
	 * </pre>
	 */
	public List selectBBSHistList(Map param) {
		String statement = super.getStatement(this.getClass().getSimpleName(),"selectBBSHistList");
		return sqlSession.selectList(statement,param);
	}

	/**
	 * NAME : deleteBBS
	 * DESC : 게시물 삭제
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param param
	 * @return
	 * </pre>
	 */
	public boolean deleteBBS(Map param) {
		String statementForHist = super.getStatement(this.getClass().getSimpleName(),"insertBBSHistory");
		sqlSession.insert(statementForHist,param);
		String statement = super.getStatement(this.getClass().getSimpleName(),"deleteBBS");
		return sqlSession.update(statement,param) > 0;
	}

	/**
	 * NAME : updateBBS
	 * DESC : 게시물 수정
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * @throws FileException
	 * </pre>
	 */
	public boolean updateBBS(Map params) throws FileException {

		String statementForHist = super.getStatement(this.getClass().getSimpleName(),"insertBBSHistory");
		sqlSession.insert(statementForHist,params);

		String statement = super.getStatement(this.getClass().getSimpleName(),"updateBBS");
		if(params.get("deletedFiles") != null || params.get("files") != null) {
			params.put("etc","파일변경");
		}
		boolean updateFlag = sqlSession.update(statement,params) > 0;

		if(updateFlag) {
			List<Map> deletedFiles = (List) params.get("deletedFiles");
			List<Map> files = (List) params.get("files");
			for(Map file : deletedFiles) {
				file.put("session",params.get("session"));
				file.put("bbsId",params.get("bbsId"));
				if(commonService.deleteFile(file)) throw new FileException("파일정보 삭제 중 오류가 발생했습니다.");
			}
			for(Map file : files) {
				file.put("session",params.get("session"));
				file.put("bbsId",params.get("bbsId"));
				if(commonService.insertFile(file)) throw new FileException("파일정보 저장중 오류가 발생하였습니다.");
			}
		}
		return updateFlag;
	}
}
