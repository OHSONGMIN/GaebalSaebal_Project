<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file ="/WEB-INF/jsp/include/header_admin.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 800px; margin: auto; }
	#submbtn{
		border-radius: 4px;
		border: none;
		padding: 10px 20px;
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
	.button:hover{		
		background-color: #8080ff;
	}
	#delete:link{
		float: right;
		background-color: #dc3545;
		color: white;
		padding: 9px 18px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		text-decoration: none;
	}
	#delete:hover{
		background-color: #bd2130;
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
<br>
	<h2>공지사항 상세</h2><br>	
	<form action="update_board" method="get">
		<input type="hidden" name="boardIdx" value="${board.boardIdx }">
		<table>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" value="${board.title }">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="content" rows="10" cols="40">${board.content }</textarea>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.regDate }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.hit }</td>
			</tr>
		</table>
	<br>
	<p>
		<input type="submit" id="submbtn" value="수정 완료">
		<a class="button" style="color: white;" href="get_notice_board_list">공지사항 목록</a>
		<a id="delete" href="delete_notice_board?boardIdx=${board.boardIdx }">공지사항 삭제</a>
	</p>
	</form>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>