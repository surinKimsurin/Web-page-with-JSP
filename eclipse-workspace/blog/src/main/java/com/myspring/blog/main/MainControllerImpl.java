package com.myspring.blog.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller("mainController")
@EnableAspectJAutoProxy
public class MainControllerImpl implements MainController {
	@RequestMapping(value="/index.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
	HttpSession session;
	ModelAndView mav=new ModelAndView();
	String viewName=(String)request.getAttribute("viewName");
	mav.setViewName(viewName);
	
	session=request.getSession();
	return mav;
	}
}
