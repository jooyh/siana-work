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

@Service
public class WorkBBSService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private CommonService commonService;

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
		if(params.get("page") == null ) params.put("page",0);
		String statement = super.getStatement(this.getClass().getSimpleName(),"getWorkBBSList");
		List bbsList = sqlSession.selectList(statement,params);
		statement = super.getStatement(this.getClass().getSimpleName(),"getWorkBBSTotCnt");
		int totCnt = sqlSession.selectOne(statement,params);
		Map result = new HashMap();
		result.put("datas", bbsList);
		result.put("totCnt", totCnt);
		result.put("params", params);
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
		return sqlSession.selectOne(statement);
	}

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
}
