<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript">
  	function check(){
  		// 아이디 입력여부 체크
  		var id = document.fr.id.value;
  		if(id == ""){
  			alert(' 아이디를 입력하세요! ');
  			document.fr.id.focus();
  			return false;
  		}
  		
  		
  		
  	}  
  </script>
</head>
<body>
		<h1>insertForm.jsp(MVC)</h1>
		<fieldset>
			<legend> ITWILL 회원가입 </legend>
			<form action="./MemberJoinAction.me" method="post" name="fr" onsubmit="return check();">
				아이디 : <input type="text" name="id"> <br>
				비밀번호 : <input type="password" name="pw"> <br>
				이름 : <input type="text" name="name"><br>
				성별 : <input type="radio" name="gender" value="남" checked>남
				       <input type="radio" name="gender" value="여">여<br>
				나이 : <input type="number" name="age"><br>
				이메일 : <input type="email" name="email"><br>
				
				<hr>
				<input type="submit" value=" 회원가입 ">
			</form>		
		</fieldset>
		
		
		
		
		
		
		
</body>
</html>