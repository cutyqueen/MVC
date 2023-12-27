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
	<h1>main.jsp (MVC)</h1>
	
	<!-- 
	     메인페이지는 로그인을 수행한 사용자만 확인가능
	     로그인 없이 접근한 경우 로그인 처리 할 수 있도록 페이지 이동
	 -->
	 <%-- <c:if test="${ id == null }"> --%>
	 <c:if test="${ empty id }">
	   <c:redirect url="./MemberLogin.me"/>	 
	 </c:if>
	
	
	<h2> 사용자 아이디(JSP): <%=session.getAttribute("id") %></h2> 
	<h2> 사용자 아이디(EL): ${sessionScope.id }</h2> 
	<h2> 사용자 아이디(EL): ${id }</h2> 
	
	<input type="button" value="로그아웃" onclick=" location.href='./MemberLogout.me';">
	
	<hr>
	
	<h3><a href="./MemberInfo.me"> 회원정보 조회 </a></h3>
	
	<hr>
	
	<h3><a href="./MemberUpdate.me"> 회원정보 수정 </a></h3>
	
	<hr>
	
	<h3><a href="./MemberDelete.me"> 회원정보 삭제 </a></h3>
	
	<hr>
	
	<h3><a href="./BoardWrite.bo"> 게시판 글쓰기 </a></h3>
	
	
	
	
	<%-- <c:if test="${id == 'admin' }"> --%>
	<%-- <c:if test="${id eq 'admin' }"> --%>
	<c:if test="${ !empty id && id.equals('admin')}">
		<!-- 관리자(admin) 전용 메뉴 -->
		<hr>
		
		<h3><a href="./MemberList.me"> 회원목록 조회 </a></h3>
	</c:if>

	
	
	
</body>
</html>