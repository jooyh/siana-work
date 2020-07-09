package com.siana.workshareapp.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService extends BaseService{

	@Autowired
	private SqlSession sqlsession;


	/**
	 * NAME : selectCommCdList
	 * DESC : 공통코드 목록 조회
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public List selectCommCdList(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "selectCommCdList");
		return sqlsession.selectList(statement,params);
	}

	/**
	 * NAME : insertFile
	 * DESC : 파일등록
	 * DATE : 2020. 7. 7.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public boolean insertFile(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "insertFile");
		return sqlsession.insert(statement,params) == 1;
	}


	/**
	 * NAME : selectFiles
	 * DESC :
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public List selectFiles(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "selectFiles");
		return sqlsession.selectList(statement,params);
	}

	/**
	 * NAME : deleteFile
	 * DESC : 파일 삭제
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public boolean deleteFile(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "deleteFile");
		return sqlsession.update(statement,params) == 1;
	}

}
