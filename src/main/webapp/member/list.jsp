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
	<h1> list.jsp (MVC) </h1>
	<%-- ${memberList } --%>
	<table border="1">
	  <tr>
	  	<td>아이디</td>
	  	<td>이름</td>
	  	<td>나이</td>
	  	<td>성별</td>
	  	<td>이메일</td>
	  	<td>회원가입일</td>
	  </tr>
	  
	  <c:forEach var="dto" items="${memberList }">
		  <tr>
		  	<td>${dto.id }</td>
		  	<td>${dto.name }</td>
		  	<td>${dto.age }</td>
		  	<td>${dto.gender }</td>
		  	<td>${dto.email }</td>
		  	<td>${dto.regdate }</td>
		  </tr>
	  </c:forEach>
	</table>
	
	<a href="./Main.me"> 메인페이지로... </a>
	
	
</body>
</html>