package com.myspring.blog.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.blog.member.dao.MemberDAO;
import com.myspring.blog.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public MemberVO login(Map loginMap) throws Exception{
		return memberDAO.login(loginMap);
	}
	
	@Override
	public void register(MemberVO member)throws Exception{
		memberDAO.register(member);
	}
	
	@Override
	public String  overlapped(String id) throws Exception{
		return memberDAO.overlapped(id);
	}
	

}
