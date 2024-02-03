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
<title>주문</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
    
	#table_1 {
		margin: 0;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		font-size: 20px;
		width: 100%;
		border-collapse: collapse;
        border-top: 1px solid black;
        border-bottom: 1px solid black;
	}
	#table_1 th {
		padding: 5px;
        width: 20%;
	}
	#table_1 td {
		padding: 5px;
	}
    
    #table_2 {
		margin: 0;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
		font-size: 20px;
		width: 100%;
		border-collapse: collapse;
    }
    #table_2 thead {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
    }
    #table_2 tbody {
        border-bottom: 1px solid black;
    }
    #table_2 tfoot {
        border-bottom: 1px solid black;
    }
    
	#table_3 {
		margin: 0;
		margin-left: auto;
		margin-right: auto;
		text-align: left;
		font-size: 20px;
		width: 100%;
		border-collapse: collapse;
        border-top: 1px solid black;
        border-bottom: 1px solid black;
	}
	#table_3 th {
		padding: 5px;
        width: 20%;
	}
	#table_3 td {
		padding: 5px;
	}
    
    .order_button1 {
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
	.order_button1:hover {
		color: #E0FBFC;
		background-color: #3D5A80;
		border: none;
	}
</style>
</head>
<body>
	
	<div id="temp">
    	<hr>
        <h1 id="h1_1">주문하기</h1>
        <hr>
        <br>
        
		<form action="insert_order" method="post" onclick="confirm('결제를 진행하시겠습니까?')">
			<h2 class="h2">주문자</h2>
            <table id="table_1">
                <tr>
                    <th>이름</th>
                    <td>${ loginMember.name }</td>
                </tr>
                <tr>
                    <th>휴대전화</th>
                    <td>${ loginMember.phone }</td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>${ loginMember.email }</td>
                </tr>
            </table>
			
            <br><br><br>
			<h2 class="h2">주문 상품</h2>
			<table id="table_2">
				<thead>
					<tr>
						<th class="table_2_th1">상품</th>
						<th class="table_2_th2">이름</th>
						<th class="table_2_th3">가격</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="totalPrice" value="0"/>
					<c:forEach var="product" items="${ productList }">
						<tr>
							<td><img src="resources/images/profile.png"></td>
							<!-- <img src="resources/images/파일이름(product.productFile)"> -->
							<td>${ product.productName }</td>
							<td>${ product.productPrice }</td>
							<td><input type="hidden" name="orders" value="${ product.productIdx }"></td>
						</tr>
						<c:set var="totalPrice" value="${totalPrice + product.productPrice}"/>
					</c:forEach>
                </tbody>
                <tfoot>
					<tr>
						<td>총 결제 금액 </td>
						<td colspan="2" class="td_total">${ totalPrice }원</td>
						<td><input type="hidden" name="sumPrice" value="${ totalPrice }"></td>
					</tr>
                </tfoot>
			</table>
			
            <br><br><br>
			<h2 class="h2">결제</h2>
			<table id="table_3">
				<tr>
					<th>보유 적립금</th>
					<td>${ loginMember.point }원</td>
				</tr>
				<tr>
					<th>사용 예정 적립금</th>
					<td>${ totalPrice }원</td>
				</tr>
				<tr>
					<th>남은 적립금</th>
					<td>${ loginMember.point - totalPrice }원</td>
				</tr>
			</table>
			
			<button type="submit" class="order_button1">결제하기</button>
		</form>
	</div>

<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>