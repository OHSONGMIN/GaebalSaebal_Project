<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty loginMember}">
	<%@ include file="/WEB-INF/jsp/include/header_bf.jspf" %>
</c:if>
<c:if test="${not empty loginMember}">
	<%@ include file="/WEB-INF/jsp/include/header_af.jspf" %>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#temp {
		margin: 0;
		padding: 5% 20%;
	}
    
	#h1_1 {
		margin: 0;
		padding: 0;
		font-size: 30px;
		text-align: center;
	}
	
    .h2 {
		margin: 0;
		padding: 0;
		font-size: 25px;
		text-align: left;
    }
    
	.table_1 {
		margin: 0;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
		font-size: 20px;
		width: 100%;
		border-collapse: collapse;
        border-top: 1px solid black;
        border-bottom: 1px solid black;
	}
	.table_1 th {
		padding: 5px;
        width: 20%;
	}
	.table_1 td {
		padding: 5px;
	}
    .table_1_th_1 {
        width: 30%;
    }
    .table_1_th_2 {
        width: 30%;
    }
    .table_1_th_3 {
        width: 30%;
    }
    .table_1_thead {
        border-bottom: 1px solid black;
    }
    .table_1_tfoot {
        border-top: 1px solid black;
    }
    
    .cancel_button {
        cursor: pointer; /* 버튼 위로 올라가면 손가락 모양 */
		width: 30%;
		height: 60px;
		font-size: 20px;
		text-align: center;
		color: #293241;
		padding: 10px;
        margin: 10px;
        margin-left: 35%;
        margin-right: 35%;
		background-color: #fff;
		border: 2px solid #98c1d9; /* 테두리 */
		border-radius: 5px; /* 테두리 모서리부분 */
		font-weight: 800; /* 글자 굵기 */
    }
	.cancel_button:hover {
		color: #fff;
		background-color: #EE6C4D;
		border: none;
	}
</style>
</head>
<body>

	<div id="temp">
		<form action="update_order" method="post" onsubmit="confirm('결제를 취소하시겠습니까?')">
			<hr>
	        <h1 id="h1_1">주문번호 : ${ orderIdx }</h1>
	        <hr>
	        <br>

			<table class="table_1">
				<thead class="table_1_thead">
					<tr>
						<th class="table_1_th_1">상품</th>
						<th class="table_1_th_2">이름</th>
						<th class="table_1_th_3">가격</th>
					</tr>
				</thead>
				<tbody class="table_1_tbody">
					<c:set var="totalPrice" value="0"/>
					<c:forEach var="order" items="${ OrderDetailList }">
						<tr>
							<td><img src="resources/images/profile.png"></td>
							<!-- <img src="resources/images/파일이름(order.productFile)"> -->
							<td>${ order.productName }</td>
							<td>${ order.productPrice }</td>
						</tr>
						<c:set var="totalPrice" value="${totalPrice + order.productPrice}"/>
					</c:forEach>
				</tbody>
				<tfoot class="table_1_tfoot">
					<tr>
						<td>총 결제 금액</td>
						<td colspan="2" class="td_total">${ totalPrice }원</td>
					</tr>
				</tfoot>
			</table>
			
			<input type="hidden" name="orderIdx" value="${ orderIdx }">
			<button type="submit" class="cancel_button">주문 취소</button>
		</form>
	</div>

<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>