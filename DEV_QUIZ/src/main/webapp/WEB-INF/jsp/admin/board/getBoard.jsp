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
	#container { width: 1240px; margin: auto; }

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
	.button:hover{
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
<br>
	<h2>글상세</h2><br>
		<input type="hidden" name="boardIdx" value="${board.boardIdx }">
		<input type="hidden" name="memberIdx" value="${board.memberIdx }">
		<table>
			<tr>
				<th>제목</th>
				<td>${board.title }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${nickname }</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>${category }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td height="150px">${board.content }</td>
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
		<a class="button" href="delete_board?boardIdx=${board.boardIdx }">글삭제</a>
		<a class="button" style="color: white;" href="get_board_list">글목록</a>
	</p>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>