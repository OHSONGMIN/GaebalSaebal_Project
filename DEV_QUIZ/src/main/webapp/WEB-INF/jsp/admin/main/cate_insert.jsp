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
<title>카테고리 추가해보자</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
	  integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/cate_insert.css">	  
<script>
$(document).ready(() => {
	$("#cateInsertBtn").on("click", cateInsert);
});
	
function cateInsert() {
let selInputCateType = $("select[name='cateType']").val();
let inputCateName = $("input[name='cateName']").val();

	alert('카테고리가 추가되었습니다. - "' + inputCateName + '"');
		
	$.ajax({
			url: "cate_insert",
			type: "post",
			data: { 
				cateType: selInputCateType,
		        cateName: inputCateName
			},
			success: function (data) {
				console.log("성공");
				console.log(data);
				
				let dispTag = " ";
				
				dispTag += "<table class='table'>"; 
				dispTag += "<thead><tr><th colspan='2'>카테고리 목록</th></tr></thead>";
				
				for(let i = 0; i < data.length; i++) {
					dispTag += "<tr>";
					dispTag += "<td>" + data[i].cateIdx + "</td>";
					dispTag += "<td>" + data[i].cateName + "</td>";
					dispTag += "</tr>";
				}
				
				dispTag += "</table>";
				
				$(".cateBody").html(dispTag);
				$("input[name='cateName']").val('');
			},
			error: function() {
				console.log("실패");
			}
	});
}
</script>
</head>
<body>
<br>
	<div class="commonBody">
		<table class="table">
			<tr>
				<th>추가 도메인</th>
				<td>
					<select name="cateType">
						<option value="board">게시글</option>
						<option value="game">게임</option>
						<option value="pointStatus">포인트유형</option>
						<option value="prods">상품</option>
						<option value="question">문의</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>카테고리이름 (중복 불가. 목록 먼저 확인해주세요!)</th>
				<td>
					<input type="text" name="cateName">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="center">
					<button id="cateInsertBtn" class="btn btn-outline-secondary btn-sm">카테고리 추가</button>
				</td>
			</tr>
		</table>
<!-- 		<hr>
		<p>카테고리 조회</p>
		<button class="btn btn-outline-secondary btn-sm">전체</button>
		<button class="btn btn-outline-secondary btn-sm">게시글</button>
		<button class="btn btn-outline-secondary btn-sm">게임</button>
		<button class="btn btn-outline-secondary btn-sm">포인트</button>
		<button class="btn btn-outline-secondary btn-sm">상품</button> -->
	</div>
	<div class="cateBody">
		<table class="table">
		<thead>
			<tr>
				<th colspan="2">카테고리 목록</th>
			</tr>
		</thead>
		<c:forEach var="cate" items="${cate }">
			<tr>
				<td>${cate.cateIdx}</td>
				<td>${cate.cateName}</td>
			</tr>
		</c:forEach>
		</table>
		<br>
	</div>
</body>
</html>

