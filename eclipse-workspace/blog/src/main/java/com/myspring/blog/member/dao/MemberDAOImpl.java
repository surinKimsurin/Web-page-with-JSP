package com.myspring.blog.member.dao;



import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.blog.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberVO login(Map loginMap) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.login",loginMap);
		return member;
	}
	
	@Override
	public void register(MemberVO memberVO) throws DataAccessException{
		sqlSession.insert("mapper.member.register",memberVO);
	}
	
	@Override
	public String overlapped(String id) throws DataAccessException{
		String result=sqlSession.selectOne("mapper.member.overlapped",id);
		return result;
	}
}
