<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>start.jsp</h1>
	
	MVC 프로젝트의 시작지점
	
	<% 
	    System.out.println(" MVC 프로젝트의 시작지점 ");  
	    System.out.println(" * MVC프로젝트에서 실행가능한 유일한 JSP 파일 ");  
	    System.out.println(" * 주소줄에 .jsp 호출X ");  
	
	    // 실행주소 
// 		response.sendRedirect("./member");
		//response.sendRedirect("./member.me");
		//response.sendRedirect("./itwill.me");
		
		//[ JSP 내장객체 request,response ] 
		
		//response.sendRedirect("./MemberJoin.me");
		//response.sendRedirect("./MemberLogin.me");
		//response.sendRedirect("./Main.me");
	    
		//response.sendRedirect("./test.bo");
		//response.sendRedirect("./BoardWrite.bo");
// 		response.sendRedirect("./BoardList.bo");
		response.sendRedirect("./Ajax.bo");
	
	%>
	
	
	
	
	
</body>
</html>