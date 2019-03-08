<%@ page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="evaluation.EvaluationDAO" %>
<%@ page import="likey.LikeyDAO" %>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="javax.servlet.http.HttpServlet" %>
<%!
	public static String getClientIP(HttpServletRequest request){
		String ip=request.getHeader("X-FORWARDED-FOR");
		
		if(ip==null||ip.length()==0){
			ip=request.getHeader("Proxy-Client-IP");
		}
		
		if(ip==null||ip.length()==0){
			ip=request.getHeader("WL-Proxy-Client-IP");
		}
		
		if(ip==null||ip.length()==0){
			ip=request.getRemoteAddr();
		}
		return ip;
	}
%>
<%
	request.setCharacterEncoding("UTF-8");
	UserDAO userDAO=new UserDAO();
	String userID=null;
	String evaluationID=null;
	boolean emailChecked=userDAO.getUserEmailChecked(userID);

	if(session.getAttribute("userID")!=null){
		userID=(String)session.getAttribute("userID");
	}
	
	if(userID==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.')");
		script.println("location.href='userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	if(request.getParameter("evaluationID")!=null)
	{
		evaluationID=request.getParameter("evaluationID");
	}

	EvaluationDAO evaluationDAO=new EvaluationDAO();
	LikeyDAO likeyDAO=new LikeyDAO();
	int result=likeyDAO.like(userID, evaluationID, getClientIP(request));

		if(result==1){
			result=evaluationDAO.like(evaluationID);
			if(result==1){
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('성공적으로 추천이 완료되었습니다.')");
				script.println("location.href='index.jsp'");
				script.println("</script>");
				script.close();
				return;
			}else{
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('데이터베이스 오류가 발생되었습니다.')");
				script.println("history.back();");
				script.println("</script>");
				script.close();
				return;
			}
			
		}else{
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('이미 추천을 누른 글입니다')");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	
	
	
%>