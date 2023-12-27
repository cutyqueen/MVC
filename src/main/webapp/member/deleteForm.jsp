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
		<h1>deleteForm.jsp(mvc)</h1>
		
		<c:if test="${empty sessionScope.id }">
			<c:redirect url="./MemberLogin.me"/>
		</c:if>
		
		
		<fieldset>
			<legend> 회원정보 삭제(탈퇴) </legend>
			<form action="./MemberDeleteAction.me" method="post">
			    <input type="hidden" name="id" value="${id }">
				비밀번호 : <input type="password" name="pw">
				<input type="submit" value="삭제하기">			
			</form>			
		</fieldset>
		
		
		

</body>
</html>