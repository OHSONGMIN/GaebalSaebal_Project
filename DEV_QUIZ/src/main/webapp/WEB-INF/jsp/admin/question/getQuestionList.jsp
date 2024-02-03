<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/header_admin.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 1240px; margin: auto; }
	h2, h3, p { text-align: center; }	
	table {
		border-collapse: collapse;
		width: 1240px;
		text-align: center;
		border: 1px solid #ddd;
		background-color: white;
	}
	th, td {
		padding: 10px;
		text-align: left;
		border-bottom: 1px solid #ddd;
		text-align: center;
	}
	th { background-color: #f2f2f2; }
	.main tbody tr:hover{
		background-color: #d3d3d3;
		opacity: 0.9;
		cursor: pointer;
	}
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>

<div id="container">
<br><br>
<h2>문의 내역</h2><br>
	<table class="main">
		<thead>
			<tr>
				<th width="100">#</th>
				<th width="150">카테고리</th>				
				<th width="300">문의 제목</th>
				<th width="150">문의 날짜</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="question" items="${qeustionList }">
			<tr onclick="location.href='get_question?questionIdx=${question.questionIdx }'" style="cursor: pointer;">
				<td>${question.questionIdx }</td>
				<td>${question.cateName }</td>
				<td>${question.questionTitle }</td>
				<td>${question.questionDate }</td>
			</tr>
		</c:forEach>
		<c:if test="${empty qeustionList }">
			<tr>
				<td colspan="4" class="center">등록된 문의글이 없습니다</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	
			<div class = "center">
		    <c:choose>
		        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
		        <c:when test="${paging.page<=1}">
		            <span>[이전]</span>
		        </c:when>
		        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
		        <c:otherwise>
		            <a href="get_question_list?page=${paging.page-1}">[이전]</a>
		        </c:otherwise>
		    </c:choose>
		
		    <%--  for(int i=startPage; i<=endPage; i++)      --%>
		    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		        <c:choose>
		            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
		            <c:when test="${i eq paging.page}">
		                <span>${i}</span>
		            </c:when>
		
		            <c:otherwise>
		                <a href="get_question_list?page=${i}">${i}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		
		    <c:choose>
		        <c:when test="${paging.page>=paging.maxPage}">
		            <span>[다음]</span>
		        </c:when>
		        <c:otherwise>
		                <a href="get_question_list?page=${paging.page+1}">[다음]</a>
		        </c:otherwise>
		    </c:choose>
		</div>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>