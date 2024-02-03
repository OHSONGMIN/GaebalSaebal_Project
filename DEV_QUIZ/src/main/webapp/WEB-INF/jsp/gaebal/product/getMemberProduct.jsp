<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>상품 상세페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	function likeNow() {
<%if (session.getAttribute("loginMember") == null) {%>
	alert("로그인 후 사용 가능합니다.");
		location.href = "go_login";
<%} else {%>
	let answer = confirm("찜 목록에 등록하시겠습니까?");
		if (answer) {
			let $form = $("#form_button");
			$form.attr("action", "insert_like_detail").submit();
			alert("등록되었습니다.");
		}
<%}%>
	}

	function cartNow() {
<%if (session.getAttribute("loginMember") == null) {%>
	alert("로그인 후 사용 가능합니다.");
		location.href = "go_login";
<%} else {%>
	let answer = confirm("장바구니 목록에 등록하시겠습니까?");
		if (answer) {
			let $form = $("#form_button");
			$form.attr("action", "insert_cart_detail").submit();
			alert("등록되었습니다.");
		}
<%}%>
	}
</script>
<style>
#container {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-top: 20px;
}

.product-details {
	width: 400px;
	margin-left: 600px;
	margin-right: 100px;
	margin-top: 20px;
	margin-bottom: 20px;
}

.product-details img {
	width: 100%;
	height: auto;
}

.product-info {
	flex: 5;
}

.product-info p {
	margin: 0;
}

.product-info p:first-child {
	margin-top: 0px;
	margin-bottom: 50px;
	font-size: 25px;
	font-weight: bold;
}

.product-info p:nth-child(2), .product-info p:nth-child(3) {
	font-size: 20px;
	margin-bottom: 30px;
	font-weight: bold;
}

.product-info p:nth-child(3) span {
	color: green;
	margin-left: 60px;
}

.additional-content {
	border-top: 1px solid #ccc;
	margin-top: 50px;
	text-align: center;
	margin-bottom: 50px;
	border-bottom: 1px solid #ccc;
}

.action-buttons {
	margin-top: 190px;
	padding-top: 10px;
	text-align: left;
}

.action-buttons button {
	margin-right: 30px;
	padding: 10px 20px;
	background-color: #0080ff;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.action-buttons button:hover {
	background-color: #8080ff;
}
</style>
</head>
<body>
	<div id="container">
		<div class="product-details">
			<img
				src="${pageContext.request.contextPath}/resources/img_product/${product.productFile}"
				alt="${product.productName}">
		</div>

		<div class="product-info">

			<p>${product.productName}</p>
			<p>
				수량: <span style="margin-left: 70px;"><input type="number"
					name="quantity" value="1" style="width: 40px;"> 개</span>
			</p>
			<p>
				가격: <span style="color: green; margin-left: 60px;">${product.productPrice}원</span>
			</p>

			<!-- 찜, 장바구니, 구매 버튼 -->
			<div class="action-buttons">
				<form id="form_button" method="post">
					<input type="hidden" name="productIdx"
						value="${product.productIdx}">
					<!-- 수량은 무조건 1로 고정되어있음 -->
					<button onclick="likeNow(); return false;">찜</button>
					<button onclick="cartNow(); return false;">장바구니</button>
				</form>
			</div>
		</div>
	</div>

	<div class="additional-content">
		<p style="font-weight: bold; margin-top: 30px; font-size: 30px;">상품
			설명</p>
		<div style="margin-bottom: 30px;">
			<c:forEach var="line"
				items="${fn:split(product.productContent, '.')}">
				<p>
					<c:out value="${line}" />
				</p>
			</c:forEach>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>
