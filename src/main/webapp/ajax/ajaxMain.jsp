<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
	  $('#btn1').click(function(){
		  //alert(" 1.ajax 테스트 클릭! ");
		  $.ajax({
			  url : "./AjaxAction.bo",
			  success : function(data){
				  alert(" 페이지 다녀옴-성공!");
				  //$('body').append('1. 문자 결과 :'+ data +"<hr>");
				  //$('body').append('1. 숫자 결과 :'+ (data+100) +"<hr>");
				  //$('body').append('1. 숫자 결과 :'+ (Number(data)+100) +"<hr>");
				  //$('body').append('1. 숫자 결과 :'+ (parseInt(data)+100) +"<hr>");
				  //-> 문자 데이터로 전달 => 숫자로 연산하기 위해서는 형변환
				  
				  
				  // 2. 일반객체는 사용불가
				 // $('body').append('2. 객체 결과 :'+ data.bno +"<hr>"); 
// 				  $('body').append('2. 객체 결과 :'+ data +"<hr>"); 
				  
				  console.log(data);
				  // 3. JSON 객체 
				  //$('body').append("3. json객체 결과 : "+data.name);
				  $('body').append("3. json객체 결과 : "+ (data.bno+100) );
				  
			  }
		  });//ajax
	  }); //click	  
	  
	  
  });
</script>
</head>
<body>
		<h1>/ajax/ajaxMain.jsp</h1>
		
		<input type="button" value="1.ajax 테스트" id="btn1">
		
		
</body>
</html>