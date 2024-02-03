<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file ="/WEB-INF/jsp/include/header_admin.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 1240px; margin: auto; }
	.getadmin:link {
		display: inline-block;
		color: black;
		text-decoration: none;
	}
	.getadmin:hover{
		display: inline-block;
		color: coral;
		text-decoration: none;
	}
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
	<h2>공지사항</h2>	
	<!-- 검색을 위한 폼 -->
	<form action="getNoticeBoardList" method="get">
		<table class="border-none">
			<tr>
				<td>
					<select name="searchCondition">
					<c:forEach var="option" items="${conditionMap }">
						<option value="${option.value }">${option.key }</option>
					</c:forEach>
					</select>
					<input type="text" name="searchKeyword">
					<input type="submit" value="검색">
					<div style="float: right;"><a class="getadmin" href="insert_board_view">새글등록</a></div>
				</td>
			</tr>
		</table>
	</form>
	
	<!-- 데이터 표시 영역 -->
	
	<table class="main">
		<thead>
			<tr>
				<th width="100">#</th>
				<th width="200">제목</th>
				<th width="150">작성일</th>
				<th width="150">조회수</th>
				<th width="70">관리</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="board" items="${NoticePagingList }">
			<tr onclick="location.href='get_notice_board?boardIdx=${board.boardIdx }'" style="cursor: pointer;">
				<td>${board.boardIdx }</td>
				<td>${board.title }</td>
				<td>${board.regDate }</td>
				<td>${board.hit }</td>
				<td><a class="getadmin" href="delete_notice_board?boardIdx=${board.boardIdx }">삭제</a></td>
			</tr>
		</c:forEach>
		<c:if test="${empty NoticePagingList }">
			<tr>
				<td colspan="5" class="center">공지사항이 없습니다</td>
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
		            <a href="get_notice_board_list?page=${paging.page-1}">[이전]</a>
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
		                <a href="get_notice_board_list?page=${i}">${i}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		
		    <c:choose>
		        <c:when test="${paging.page>=paging.maxPage}">
		            <span>[다음]</span>
		        </c:when>
		        <c:otherwise>
		                <a href="get_notice_board_list?page=${paging.page+1}">[다음]</a>
		        </c:otherwise>
		    </c:choose>
		</div>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>