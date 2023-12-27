<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>info.jsp(MVC)</h1>
	<h2>내정보 보기</h2>
	<%
	  // 로그인 세션확인 
	%>
	 <c:if test="${ empty id }">
	   <c:redirect url="./MemberLogin.me"/>	 
	 </c:if>
	
	<h3> 아이디 : ${requestScope.dto.id } </h3>
	<h3> 비밀번호 : ${requestScope.dto.pw } </h3>
	<h3> 이름 : ${dto.name }</h3>
	<h3> 나이 : ${dto.age } </h3>
	<h3> 성별 : ${dto.gender }</h3>
	<h3> 이메일 : ${dto.email }</h3>
	<h3> 회원가입일 : ${dto.regdate }</h3>
	
	
	
	<h2><a href="./Main.me">메인페이지로...</a></h2>
	




</body>
</html>