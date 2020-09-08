package com.siana.sianaApp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siana.sianaApp.common.service.BaseService;

@Service
public class CommentService  extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	/**
	 * NAME : insertComment
	 * DESC : 게시물 코멘트 등록
	 * DATE : 2020. 7. 8.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public boolean insertComment(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(),"insertComment");
		return sqlSession.insert(statement , params) > 0;
	}

	/**
	 * NAME : selectComment
	 * DESC : 게시물 코멘트 조회
	 * DATE : 2020. 7. 8.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public List selectComment(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(),"selectComment");
		return sqlSession.selectList(statement,params);
	}

}
