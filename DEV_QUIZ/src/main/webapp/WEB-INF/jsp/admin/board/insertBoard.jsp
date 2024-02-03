<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file ="/WEB-INF/jsp/include/header_admin.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 1240px; margin: auto; }
	#submbtn {
		border-radius: 4px;
		border: none;
		padding: 9px 20px;
		text-align: center;
		color: white;
		background-color: #007BFF;
	}
	.button:link{
		background-color: #007BFF;
		color: white;
		padding: 10px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
	}
	#submbtn:hover{
		background-color: #8080ff;
	}
	#button:hover{		
		background-color: #8080ff;
	}
	h2, h3, p { text-align: center; }
	table { border-collapse: collapse; }
	table, th, td {
		border: 1px solid #B3B3B3;
		margin: auto;
	}
	th, td {
		padding: 7px;
	}
	th { background-color: #f2f2f2;
	text-align: center;
	width: 200px; }
	td {
		width: 800px;
	}
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>
<div id="container">
	<br><h2>공지사항 등록</h2><br>
	
	<form action="insert_board" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="10" cols="40"></textarea>
				</td>
			</tr>
		</table>
		<br>
	<p>
		<input type="submit" id="submbtn" value="공지사항 등록">
		<a class="button" style="color: white;" href="get_notice_board_list">글 목록 가기</a>
	</p>
	</form>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>