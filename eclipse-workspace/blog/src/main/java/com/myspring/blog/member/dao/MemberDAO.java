package com.myspring.blog.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.blog.member.vo.MemberVO;

public interface MemberDAO {
	public MemberVO login(Map loginMap) throws DataAccessException;
	public void register(MemberVO memberVO) throws DataAccessException;
	public String overlapped(String id) throws DataAccessException;

}
