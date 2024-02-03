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
<title>결제 실패</title>
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
	
    .p_1 {
		margin: 0;
		padding: 0;
		font-size: 25px;
		text-align: left;
    }
</style>
</head>
<body>
	
	<div id="temp">
        <hr>
        <h1 id="h1_1">결제 실패하였습니다.</h1>
        <hr>
        <br>
		<p class="p_1">${ failMsg }</p>
	</div>
    
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>