<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/header_af.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
h2 {
	text-align: center;
	margin: 30px;
}

hr {
	border: 1px solid grey;
	width: 50%;
	margin-bottom: 30px;
}

#container {
	width: 1200px;
	margin: auto;
}

h1, h3, p {
	text-align: center;
}

table {
	width: 100%;
}

th {
	background-color: lightgray;
}

.center {
	text-align: center;
}

.border-none, .border-none td {
	border: none;
}

.left-community {
	width: 200px;
	float: left;
	position: relative;
	margin: 0 auto;
}

.right-community {
	width: 800px;
	float: center;
	position: relative;
	margin: 0 auto;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<h2>내가 쓴 글 조회</h2>
	<hr>
	<div id="container">
		<div class="right-community">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="10%">번호</th>
						<th width="50%">제목</th>
						<th width="30%">작성일</th>
						<th width="10%">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="myWrite" items="${myWrite }">
						<tr>
							<td>${myWrite.boardIdx }</td>
							<td><a href="update_my_write_page?boardIdx=${myWrite.boardIdx }">${myWrite.title }</a>
							</td>
							<td>${myWrite.regDate }</td>
							<td>${myWrite.hit }</td>
						</tr>
					</c:forEach>
					<c:if test="${empty myWrite }">
						<tr>
							<td colspan="6" class="center">작성하신 게시글이 없습니다</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		<p>
			<a href="go_mypage">마이페이지로 돌아가기</a>
		</p>
	</div>
	<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>