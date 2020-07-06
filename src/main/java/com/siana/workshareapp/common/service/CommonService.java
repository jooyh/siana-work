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


	public List selectCommCdList(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "selectCommCdList");
		return sqlsession.selectList(statement,params);
	}

	public boolean insertFile(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "insertFile");
		return sqlsession.insert(statement,params) == 1;
	}

}
