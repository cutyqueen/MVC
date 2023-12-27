<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		 <jsp:include page="../inc/top.jsp"/>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<jsp:include page="../inc/left.jsp" />
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<form action="./BoardFileWriteAction.bo" method="post" enctype="multipart/form-data" >
			<article>
				<h1>아이티윌 소식 게시판</h1>
				<table id="notice">
					<tr>
						<th class="ttitle">게시판 글쓰기</th>
						<th class="twrite">(첨부파일)</th>
					</tr>
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="subject"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><input type="text" name="content"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pass"></td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td><input type="file" name="file" accept=".pdf"></td>
					</tr>
				</table>
				<div id="table_search">
					<input type="submit" value="글쓰기" class="btn">
				</div>
				<div class="clear"></div>
				<div id="page_control"></div>
			</article>
		</form>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<footer>
			<hr>
			<div id="copy">
				All contents Copyright 2011 FunWeb 2011 FunWeb Inc. all rights reserved<br> Contact mail:funweb@funwebbiz.com Tel +82 64 123 4315 Fax +82 64 123 4321
			</div>
			<div id="social">
				<img src="./images/facebook.gif" width="33" height="33" alt="Facebook"> <img src="./images/twitter.gif" width="34" height="34" alt="Twitter">
			</div>
		</footer>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>