<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${empty admin}">
	    <%@ include file="/WEB-INF/jsp/include/header_admin.jspf" %>
	</c:if>
	<c:if test="${not empty admin}">
	    <%@ include file="/WEB-INF/jsp/include/header_admin_af.jspf" %>
	</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조회 회원이 작성한 게시글 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 1240px; margin: auto; }
	.getadmin:link {
		display: inline-block;
		color: black;
		text-decoration: none;
	}
	.getadmin:hover{
		display: inline-block;
		color: coral;
		text-decoration: none;
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
	.button:hover{		
		background-color: #8080ff;
	}
	h2, h3, p { text-align: center; }
	table {
		border-collapse: collapse;
		width: 1240px;
		text-align: center;
		border: 1px solid #ddd;
		background-color: white;
		margin: auto;
	}
	th, td {
		padding: 10px;
		text-align: left;
		border-bottom: 1px solid #ddd;
		text-align: center;
	}
	th { background-color: #f2f2f2; }
	.main tbody tr:hover{
		background-color: #d3d3d3;
		opacity: 0.9;
		cursor: pointer;
	}
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>
<div id="container">
<br>
<h2>작성한 글 목록</h2><br>
	<input type="hidden" name="memberIdx" value="${member.memberIdx }">
	<table class="main">
		<thead>
			<tr>
				<th width="50">#</th>
				<th width="100">카테고리</th>
				<th width="300">제목</th>
				<th width="150">작성일</th>
				<th width="100">조회수</th>
				<th width="100">관리자</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="boardIdList" items="${boardIdList }">
			<tr onclick="location.href='get_board_id?boardIdx=${boardIdList.boardIdx }'" style="cursor: pointer;">
				<td>${boardIdList.boardIdx }</td>
				<td>${boardIdList.cateName }</td>
				<td>${boardIdList.title }</td>
				<td>${boardIdList.regDate }</td>
				<td>${boardIdList.hit }</td>
				<td><a class="getadmin" href="delete_board_id?boardIdx=${boardIdList.boardIdx }&memberIdx=${boardIdList.memberIdx }">삭제</a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty boardIdList }">
			<tr>
				<td colspan="6" class="center">회원이 작성한 게시글이 없습니다</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<br>
		<p>
			<a class="button" href="get_member_list">회원 목록</a>
			<a class="button" style="color: white;" href="get_member?memberIdx=${boardIdList.get(0).getMemberIdx() }">회원 정보로 돌아가기</a>
		</p>
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>