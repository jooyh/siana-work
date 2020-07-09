package com.siana.workshareapp.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.siana.workshareapp.common.service.BaseService;

@Service
public class AccountService extends BaseService{

	protected static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private ShaPasswordEncoder encoder;

	@Autowired
	private SqlSession sqlSession;

	/**
	 * NAME : login
	 * DESC : 사용자 로그인 (ID,PW 를 통한 계정정보 조회)
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return user infomation map
	 * </pre>
	 */
	public Map login(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "login");
		String orgPw = (String) params.get("userPw");
		String encodedPw = encoder.encodePassword(orgPw, "SIANA");
		params.put("userPw",encodedPw);
		return sqlSession.selectOne(statement,params);
	}

	/**
	 * NAME : registUser
	 * DESC : 사용자 등록
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param param
	 * @return boolean ? 성공 : 실패
	 * </pre>
	 */
	public boolean registUser(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "registUser");
		String orgPw = (String) params.get("userPw");
		String encodedPw = encoder.encodePassword(orgPw, "SIANA");
		params.put("userPw",encodedPw);
		return sqlSession.insert(statement,params) > 0;
	}

	/**
	 * NAME : updateUser
	 * DESC : 사용자 수정
	 * DATE : 2020. 7. 3.
	 * <pre>
	 * @auther jyh
	 * @param param
	 * @return boolean ? 성공 : 실패
	 * </pre>
	 */
	public boolean updateUser(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(), "updateUser");
		String orgPw = (String) params.get("userPw");
		if(orgPw != null &&orgPw.equals("")) {
			String encodedPw = encoder.encodePassword(orgPw, "SIANA");
			params.put("userPw",encodedPw);
		}
		return sqlSession.update(statement) > 0;
	}

	/**
	 * NAME : duplicatedCheckUserId
	 * DESC : 사용자 아이디 중복 체크
	 * DATE : 2020. 7. 9.
	 * <pre>
	 * @auther jyh
	 * @param params
	 * @return
	 * </pre>
	 */
	public boolean duplicatedCheckUserId(Map params) {
		String statement = super.getStatement(this.getClass().getSimpleName(),"duplicatedCheckUserId");
		int cnt = sqlSession.selectOne(statement,params);
		return cnt == 0;
	}

}
