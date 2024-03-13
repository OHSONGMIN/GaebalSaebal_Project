<%@page import="com.devquiz.biz.serviceimpl.NewsList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty loginMember}">
	<%@ include file="/WEB-INF/jsp/include/header_bf.jspf"%>
</c:if>

<c:if test="${not empty loginMember}">
	<%@ include file="/WEB-INF/jsp/include/header_af.jspf"%>
</c:if>
<%
	List<NewsList> newsList = NewsList.News();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<style>

#table1 {
	width: 1000px;
	margin: auto;
	/* margin: 10px auto 100px auto; */
	text-align: center;
	font-size: 14px;
	border-bottom: 1px solid #DCDCDC;
}

#table2 {
	width: 490px;
	margin: 0px 30px 0px 65px;
}

#table3 {
	width: 500px;
}

#ba1 {
	margin: 50px auto 10px 100px;
}

#ba2 {
	margin: auto auto 20px 100px;
}

/* #subTable #table2, #table3{
	width: 40%;
} */


/* #subTable {
	margin-left: 200px;
	margin-bottom: 50px;
} */

#mainTable>.badge-custom {
	background-color: #ee6c4d;
	color: white;
	width: 80px;
	height: 20px;
}

#mainTable {
	width: 1200px;
	margin: auto auto 40px auto;
}

#bottomTable {
	width: 1200px;
	margin: auto;
}

#ba2>.badge-custom {
	background-color: #98c1d9;
	color: white;
	width: 70px;
	height: 20px;
}

.badgeMsg {
	font-size: 13px;
	margin-left: 10px;
}

a {
	color: black;
}

th {
	font-size: 10px;
	text-align: center;
}

td{
	font-size:14px;
}
/* #table2 {
	margin-right: 30px;
} */

#table2 td {
    height: 10px;
}


</style>
</head>
<body>
	<div id="mainTable">
		<span id="ba1" class="badge badge-custom">HOT!</span> 
		<span class="badgeMsg">현재 가장 인기있는 게시물을 확인해보세요!</span>
		
		<table id="table1" class="table table-hover table-sm">
			<colgroup>
				<col style="width: 60%;">
				<col style="width: 25%;">
				<col style="width: 15%;">
			</colgroup>
			
			<thead>
				<tr>
					<th scope="col">글제목</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<c:forEach var="community" items="${hotCommunityList}">
				<tr>
					<td><a href="get_community?boardIdx=${community.boardIdx }">${community.title }</a>
					</td>
					<td>${community.regDate}</td>
					<td>${community.hit}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div id = "bottomTable">
		<div id="ba2">
			<span class="badge badge-custom">공지사항</span> <span class="badgeMsg"><a
				href="get_community_list_by_cate?cateIdx=100">더보기..</a></span>
			<!-- 공지사항 게시판 연결하기  -->
			<span class="badge badge-custom" style="margin-left: 370px;">NEWS!</span>
			<span class="badgeMsg">IT 관련 뉴스를 확인해보세요~!</span>
		</div>
		
	
		<div id="subTable">
			<div class="container">
				<div class="row">
					<table id="table2" class="table table-hover table-sm">
						<thead>
							<tr>
								<th scope="col">글제목</th>
								<th scope="col">작성일</th>
							</tr>
						</thead>
						<c:forEach var="notice" items="${noticeList }" begin="1" end="9">
							<tr>
								<td>&nbsp;&nbsp;<a href="get_community?boardIdx=${notice.boardIdx }">${notice.title }</a>
								</td>
								<td>${notice.regDate }</td>
							</tr>
						</c:forEach>
					</table>
					<table id="table3" class="table table-hover table-sm">
						<c:forEach var="news" items="<%=newsList%>" begin="1" end="10">
							<tr>
								<td class="table3"><a href="${news.link}">${news.title}</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<br><br><br><br><br><br>
	<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>