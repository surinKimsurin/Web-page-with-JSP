package com.myspring.blog.member.service;

import java.util.Map;

import com.myspring.blog.member.vo.MemberVO;

public interface MemberService {
	public MemberVO login(Map loginMap) throws Exception;
	public void register(MemberVO member)throws Exception;
	public String  overlapped(String id) throws Exception;

}
