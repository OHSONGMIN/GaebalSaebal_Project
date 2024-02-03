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
<title>개발새발</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
	  integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gaebal/question.css">	  
<script>
/* 	function gaebalGoWriteQuestion() {
	    location.href = "have_a_question";
	} */
    function gaebalGoBack() {
        history.back();
    }
</script>
</head>
<body>
<br>
	<div class="commBody">
	<form id="searchForm" action="go_serach_question_list" method="get">
			<table class="table">
				<tr>
					<td>
					<select name="searchCondition">
						<c:forEach var="option" items="${conditionMap }">
							<option value="${option.value }">${option.key }</option>
						</c:forEach>
					</select>
						<input type="text" name="searchKeyword">
						<input type="submit" value="검색">
					</td>
				</tr>
			</table>
		</form>
	
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">문의번호</th>
					<th scope="col">카테고리</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty question}">
			<c:forEach var="qList" items="${question }">
				<tr>
				<th scope="row">${qList.questionIdx }</th>
				<%-- <td>${qList.questionIdx }</td> --%>
				<td>${qList.cateName }</td>
				<td><a href="question_detail?questionIdx=${qList.questionIdx}">${qList.questionTitle }</a></td>
				<td>${qList.nickname }</td>
				<td>${qList.questionDate }</td>
				</tr>
			</c:forEach>  	
			</c:if>
			<c:if test="${empty question}">
			<tr>
				<th>검색 결과가 없습니다.</th>
			</tr>
			</c:if>
			</tbody>
			<tfoot>
				<tr>
	       			<td colspan=5><button onclick= "gaebalGoBack()" class="btn btn-outline-secondary btn-sm">목록</button></td>
		  		</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>

