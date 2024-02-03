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
<title>내 상품</title>
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
        width: 45%;
    }
    .table_1_th_2 {
        width: 45%;
    }
</style>
</head>
<body>
	
	<div id="temp">
		<hr>
		<h1 id="h1_1">내 보유 상품</h1>
		<hr>
		<br>
		
		<table id="table_1">
			<thead>
				<tr>
					<th class="table_1_th_1">상품</th>
					<th class="table_1_th_2">이름</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty myProductList }">
						<tr>
							<td colspan="2">보유한 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="product" items="${ myProductList }">
							<tr>
								<td><img src="resources/images/profile.png"></td> <!-- 상품 사진 -->
								<td>${ product.productName }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>