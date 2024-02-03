<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
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
<title>여기가 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"> 
</head>
<style>
	h3 {
		text-align: center;
	}
</style>
<body>
<br><br>
	<h3>관리자 ${admin.nickname}님, 환영합니다.</h3>
	<br><br>
<%@ include file="/WEB-INF/jsp/include/admin-footer.jspf"%>
</body>
</html>
