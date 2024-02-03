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
<title>내 주문 내역</title>
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
    
	#table_1 {
		margin: 0;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
		font-size: 20px;
		width: 100%;
		border-collapse: collapse;
	}
	
	#table_1>thead {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
	}
	
	#table_1 th {
		padding: 5px;
	}
	
	#table_1>tbody {
		border-bottom: 1px solid black;
	}
	
	#table_1 td {
		padding: 5px;
	}

    .table_1_th_1 {
        width: 30%;
    }
    .table_1_th_2 {
        width: 15%;
    }
    .table_1_th_3 {
        width: 30%;
    }
    .table_1_th_4 {
        width: 15%;
    }
</style>
</head>
<body>

	<div id="temp">
		<hr>
        <h1 id="h1_1">내 주문 내역</h1>
        <hr>
        <br>

		<table id="table_1">
			<thead>
				<tr>
					<th class="table_1_th_1">주문번호</th>
					<th class="table_1_th_2">주문금액</th>
					<th class="table_1_th_3">주문날짜</th>
					<th class="table_1_th_4">주문상태</th>
				</tr>
			</thead>
			<c:choose>
				<c:when test="${ empty orderList }">
					<tr>
						<td colspan="4">주문한 내역이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="order" items="${orderList}">
						<tr>
							<c:choose>
								<c:when test="${ order.orderDel == 0 }">
									<td><a href="select_order_detail?value=${ order.orderIdx }">${ order.orderIdx }</a></td>
								</c:when>
								<c:otherwise>
									<td>${ order.orderIdx }</td>
								</c:otherwise>
							</c:choose>
							<td>${ order.sumPrice }</td>
							<td>${ order.orderDate }</td>
							<c:choose>
								<c:when test="${ order.orderDel == 0 }">
									<td>결제완료</td>
								</c:when>
								<c:otherwise>
									<td>결제취소</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>

<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>