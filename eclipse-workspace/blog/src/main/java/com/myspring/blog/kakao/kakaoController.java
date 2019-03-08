package com.myspring.blog.kakao;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping("/kakao")
public class kakaoController {
	private Kakao_restapi kakao_restapi=new Kakao_restapi();
	
	@RequestMapping(value="/oauth",method= RequestMethod.GET)
	public String kakaoConnect() {

		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize?");
		url.append("client_id=" + "32364be2d7168ddeb7388972766852a9");
		url.append("&redirect_uri=http://localhost:8080/mytest04/kakao/callback");
		url.append("&response_type=code");

		return "redirect:" + url.toString();
	}
	
	@RequestMapping(value="/callback",produces="application/json",method= {RequestMethod.GET, RequestMethod.POST})
	public String kakaoLogin(@RequestParam("code")String code,RedirectAttributes ra,HttpSession session,HttpServletResponse response )throws IOException {
		
		System.out.println("kakao code:"+code);
		JsonNode access_token=kakao_restapi.getKakaoAccessToken(code);
	//	access_token.get("access_token");
      //   System.out.println("access_token:" + access_token.get("access_token"));

		JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(access_token.get("access_token"));

	        // Get id
	        String member_id = userInfo.get("id").asText();

	        String member_name = null;
	        
	        // 유저정보 카카오에서 가져오기 Get properties\
	        JsonNode properties = userInfo.path("properties");
	        JsonNode kakao_account = userInfo.path("kakao_account");
	        member_name = properties.path("nickname").asText();
	       // email = kakao_account.path("email").asText();
	        if(member_name!=null) {
	        	session.setAttribute("isLogOn",true);
	        	session.setAttribute("member_id",member_name);
	        }
	        System.out.println("id : " + member_id);
	        System.out.println("name : " + member_name);
	       // System.out.println("email : " + email);
		
	        return "redirect:/index.do";
	}
}
