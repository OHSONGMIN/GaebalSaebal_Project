<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>회원상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 1240px; margin: auto; }
	.button:link{
		background-color: #007BFF;
		color: white;
		padding: 10px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
	}
	.button:hover{		
		background-color: #8080ff;
	}
	
	.button2:link{
		background-color: #007BFF;
		color: white;
		padding: 6px 12px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
	}
	.button2:hover{		
		background-color: #8080ff;
	}
	h2, h3, p { text-align: center; }
	table { border-collapse: collapse; }
	table, th, td {
		border: 1px solid #B3B3B3;
		margin: auto;
	}
	th, td {
		padding: 7px;
	}
	th { background-color: #f2f2f2;
	text-align: center;
	width: 200px; }
	td {
		width: 800px;
	}
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>
<div id="container">
<br>
	<h2>회원정보 상세</h2><br>
		<input type="hidden" name="memberIdx" value="${member.memberIdx }">
		<table>
			<tr>
				<th>아이디</th>
				<td>${member.id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${member.name }</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>${member.nickname }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.email }</td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td>${member.phone }</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td>
					<c:if test = "${point == 0}">
					0
					</c:if>
					<c:if test = "${point > 0}">
					${point }
					<a class="button2" href="get_point?memberIdx=${member.memberIdx }">내역보기</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>주문 건수</th>
				<td>
					<c:if test = "${order == 0}">
					주문 내역 없음
					</c:if>
					<c:if test = "${order > 0}">
					${order }
					<a class="button2" href="get_order?memberIdx=${member.memberIdx }">내역보기</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>작성한 게시글 수</th>
				<td>
					<c:if test = "${cnt == 0}">
					0
					</c:if>
					<c:if test = "${cnt > 0}">
					<a href="get_list?memberIdx=${member.memberIdx }">${cnt }</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>가입 날짜</th>
				<td>${member.joinDate }</td>
			</tr>
			<tr>
				<th>가입 상태</th>
				<td>
					<c:if test = "${member.isDel == 0}">
					회원
					</c:if>
					<c:if test = "${member.isDel == 1}">
					탈퇴 회원
					</c:if>
				</td>
			</tr>
			<tr>
				<th>권한</th>
				<td>
					<c:if test = "${member.isAdmin == 0}">
					회원
					</c:if>
					<c:if test = "${member.isAdmin == 1}">
					관리자
					</c:if>
				</td>
			</tr>
		</table>
		<br>
	<p>
		<a class="button" href="delete_member?memberIdx=${member.memberIdx }">강제탈퇴</a>
		<a class="button" style="color: white;"href="get_member_list">회원목록</a>
	</p>
	
</div>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>