package com.myspring.blog.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspring.blog.member.service.MemberService;
import com.myspring.blog.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value="/member")
public class MemberControllerImpl implements MemberController{
	
	@Autowired
	MemberVO memberVO;
	@Autowired
	MemberService memberService;
	
	
	@Override
	@RequestMapping(value="/loginForm.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/loginForm");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/overlapped.do", method=RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResponseEntity resEntity=null;
		String result=memberService.overlapped(id);
		resEntity= new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value="/login.do", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView login(@RequestParam Map<String, String> loginMap,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO=memberService.login(loginMap);
		
		if(memberVO!=null&&memberVO.getMember_id()!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("isLogOn", true);
			session.setAttribute("member_id",memberVO.getMember_id());
			session.setAttribute("memberVO",memberVO);
			String action=(String)session.getAttribute("action");
			if(action!=null &&action.equals("/member/register.do")) {
				mav.setViewName("forward:"+action);
			}else {
				mav.setViewName("redirect:/index.do");
			}
		}else {
			String message="���̵� ��й�ȣ�� Ʋ���ϴ�";
			mav.addObject("message",message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}
	
	@Override
	@RequestMapping(value="/logout.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		session.setAttribute("isLogOn",false);
		session.removeAttribute("memberInfo");
		mav.setViewName("redirect:/index.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/registerForm.do", method= {RequestMethod.POST, RequestMethod.GET})
		public ModelAndView registerForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
			ModelAndView mav=new ModelAndView();
			mav.setViewName("/member/registerForm");
			
			return mav;
		}
	
	@Override
	@RequestMapping(value="/register.do", method= {RequestMethod.POST})
		public ResponseEntity register(@ModelAttribute("memberVO") MemberVO _memberVO,
						HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message=null;
		ResponseEntity resEntity=null;
		HttpHeaders responseHeaders=new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			memberService.register(_memberVO);
			message="<script>";
			message+="alert('ȸ�������� ���ƽ��ϴ�. �α���â���� �̵��մϴ�.');";
			message+="location.href='"+request.getContextPath()+"/member/loginForm.do';";
			message+="</script>";
		}catch(Exception e) {
			message="<script>";
			message+="alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���');";
			message+="location.href='"+request.getContextPath()+"/member/registerForm.do';";
			message+="</script>";
		}
		resEntity=new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
		
		}
	}
