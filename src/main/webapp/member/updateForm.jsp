<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript">
  	function check(){
  		// 비밀번호 입력여부 체크
  		var pw = document.fr.pw.value;
  		if(pw == ""){
  			alert(' 비밀번호를 입력하세요! ');
  			document.fr.pw.focus();
  			return false;
  		}
  		
  	}  
  </script>
</head>
<body>
		<h1>updateForm.jsp (MVC)</h1>
		<%
			//  로그인정보 체크
		%>
		
<%-- 		${requestScope.dto } <hr> --%>
<%-- 		${sessionScope.dto } <hr> --%>
		
		<fieldset>
			<legend> ITWILL 회원정보 수정 </legend>
			<form action="./MemberUpdateProAction.me" method="post" name="fr" onsubmit="return check();">
				아이디 : <input type="text" name="id" value="${dto.id }" readonly> <br>
				비밀번호 : <input type="password" name="pw"> <br>
				이름 : <input type="text" name="name" value="${dto.name }"><br>
				성별 : <input type="radio" name="gender" value="남" 
						 <c:if test="${dto.gender.equals('남') }">
						    checked
						 </c:if>
						>남
				       <input type="radio" name="gender" value="여" 
				        <c:if test="${dto.gender.equals('여') }">
						    checked
						 </c:if>
				       >여<br>
				이메일 : <input type="email" name="email" value="${dto.email }" readonly><br>
				
				<hr>
				<input type="submit" value=" 수정하기 ">
			</form>		
		</fieldset>
		
		
		
		
		
		
</body>
</html>