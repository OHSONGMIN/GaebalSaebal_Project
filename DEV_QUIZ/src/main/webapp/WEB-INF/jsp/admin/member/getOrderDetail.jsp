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
<title>주문상세내역 조회</title>
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

	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>
<div id="container">
	<br><h2>주문 내역 상세</h2><br>
		<table class="main">
		<thead>
			<tr>
				<th width="250">주문 상품</th>
				<th width="250">상품 가격</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="order" items="${orderDetailList }">
			<tr>
				<td>${order.productName }</td>
				<td>${order.productPrice }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table><br>
	<p>
		<a class="button" href="get_member_list">회원목록</a>
		<a class="button" style="color: white;" href="get_member?memberIdx=${orderDetailList.get(0).getMemberIdx() }">회원 정보로 돌아가기</a>
		
	</p>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>