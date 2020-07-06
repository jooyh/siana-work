package com.siana.workshareapp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siana.workshareapp.common.service.BaseService;
import com.siana.workshareapp.common.vo.FileVO;

@Service
public class WorkBBSService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

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
	public List getWorkBBSList(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(),"getWorkBBSList");
		return sqlSession.selectList(statement);
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

	public boolean insertWorkBBS(Map params) {

		List<FileVO> files = (List) params.get("files");
		for(FileVO file : files) {

		}
		String bbsStatement = super.getStatement(this.getClass().getSimpleName(),"insertWorkBBS");
		sqlSession.insert(bbsStatement);

		return false;
	}
}
