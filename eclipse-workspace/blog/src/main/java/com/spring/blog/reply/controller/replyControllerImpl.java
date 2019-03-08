package com.spring.blog.reply.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("replyController")
public class replyControllerImpl {
	/*
	@RequestMapping(value="/addReply.do/{num}",method= {RequestMethod.POST})
	public void addReply(@PathVariable("num")int articleNO,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		
		String reply_id=(String)request.getAttribute("post_id");
		String reply_content=(String)request.getAttribute("message");
		System.out.println(reply_id+reply_content);
	}*/
}

