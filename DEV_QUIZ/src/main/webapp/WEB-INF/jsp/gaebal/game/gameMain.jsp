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
<title>게임 실행하기!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gaebal/gameMain.css">
</head>
<body>
    <!-- 게임 선택 -->
	<div class="gameBody">
		<div class="games">
		<c:forEach var="quizCate" items="${quizCate }">
			<a href="game_play?cateIdx=${quizCate.cateIdx}"><button class="gameBtn">${quizCate.cateName}</button></a>
		</c:forEach>
		</div>
	</div>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>
