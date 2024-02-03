<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty loginMember}">
	<%@ include file="/WEB-INF/jsp/include/header_bf.jspf"%>
</c:if>

<c:if test="${not empty loginMember}">
	<%@ include file="/WEB-INF/jsp/include/header_af.jspf"%>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
h2 {
	text-align: center;
	margin: 30px;
}

hr {
	border: 1px solid grey;
	width: 700px;
	margin-bottom: 30px;
}

table {
	text-align: center;
}

.container {
	width: 400px;
	margin: 50px auto 200px auto;
}
</style>
</head>
<body>
	<h2>마이페이지</h2>
	<hr>
	<div class="container">
		<table class="table table-striped">
			<tr>
			<tr>
				<td><a href="like_list">찜</a></td>
			</tr>
			<tr>
				<td><a href="cart_list">장바구니</a></td>
			</tr>
			<tr>
				<td><a href="myproduct_list">내 상품</a></td>
			</tr>
				<td><a href="select_order">주문 내역 확인</a></td>
			</tr>
			<tr>
				<td><a href="go_my_point">포인트 내역 확인</a></td>
			</tr>
			<tr>
				<td><a href="go_my_write">내가 쓴 글 조회</a></td>
			</tr>
			<tr>
				<td><a href="go_update_password">회원정보수정</a></td>
			</tr>
			<tr>
				<td><a href="go_delete">회원탈퇴</a></td>
			</tr>
		</table>
	</div>

	<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>