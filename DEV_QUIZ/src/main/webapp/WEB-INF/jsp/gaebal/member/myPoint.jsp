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
<title>포인트적립</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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
	width: 50%;	
	margin: 20px auto;
	text-align: center;
}

</style>
</head>
<body>
	<h2> 내 포인트 적립 현황</h2>
	<hr>
	
	<div id="container">
		<div id="pointTable">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col" width="20%">포인트</th>
						<th scope="col" width="20%">내역</th>
						<th scope="col" width="30%">적립일자</th>	
						<th scope="col" width="30%">소멸예정기간</th>				
					</tr>				
				</thead>
				<tbody>
				<c:forEach var="myPoint" items="${myPoints }">
						<tr>
							<td>${myPoint.point }</td>
							<c:choose>
								<c:when test="${myPoint.point > 0}">
									<td>적립</td>
								</c:when>
								<c:otherwise>
									<td>차감</td>
								</c:otherwise>
							</c:choose>
							<td>${myPoint.pointDate }</td>
							<td>${myPoint.dueDate}</td>
						</tr>
				</c:forEach>
				<c:if test="${myPoint }">
					<tr>
						<td colspan="2" class="center">포인트 적립 내역을 찾을 수 없습니다.</td>
					</tr>
				</c:if>
				</tbody>			
			</table>		
		</div>		
			<a href="go_mypage" class="mypage">마이페이지로 돌아가기</a>			
	</div>

<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>			
</body>
</html>