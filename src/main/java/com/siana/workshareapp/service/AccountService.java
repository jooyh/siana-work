package com.siana.workshareapp.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siana.workshareapp.common.service.BaseService;

@Service
public class AccountService extends BaseService{

	@Autowired
	private SqlSession sqlSession;

	public List test(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "test");
		return sqlSession.selectList(statement);
	}

}
