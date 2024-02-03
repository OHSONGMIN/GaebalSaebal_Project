<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file ="/WEB-INF/jsp/include/header_admin.jspf" %>

<!DOCTYPE html>
<html>
<head>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>문의글상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
	#container { width: 800px; margin: auto; }
	#content { border: 1px solid #d3d3d3;
	 height: 150px;
	 display: block;
	 margin-left: auto;
	 margin-right: auto;
	 }
	 #content2 { border: 1px solid #d3d3d3;
	 height: 150px;
	 width: 700px;
	 display: block;
	 margin-left: auto;
	 margin-right: auto;
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

	#update{
		background-color: #007BFF;
		color: white;
		padding: 9px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
	}
	#update:hover{
		background-color: #8080ff;
	}
	#answersubmit{
		background-color: #007BFF;
		color: white;
		padding: 9px 20px;
		border: none;
		border-radius: 5px;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
	}
	#answersubmit:hover{
		background-color: #8080ff;
	}
	.delete:link {
	float: right;
	background-color: #dc3545;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none;
	}

	.delete:hover {
		background-color: #bd2130;
	}
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>
<div id="container">
<br>
	<h2>문의글상세</h2><br>
		<input type="hidden" name="questionIdx" value="${question.questionIdx }">
		<input type="hidden" name="memberIdx" value="${question.memberIdx }">
		<table>
			<tr>
				<th>제목</th>
				<td>${question.questionTitle }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${question.nickname }</td>
			</tr>
			<tr>
				<th>상품</th>
				<td>
					<c:if test = "${question.productIdx == 0}">
					지정안함
					</c:if>
					<c:if test = "${question.productIdx > 0}">
					${question.productName }
					</c:if>
				</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>
					<c:if test = "${question.cateIdx == 0}">
					지정안함
					</c:if>
					<c:if test = "${question.cateIdx > 0}">
					${question.cateName }
					</c:if>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td height="150px">${question.questionContent }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${question.questionDate }</td>
			</tr>
		</table>
<div id="answerForm">
	<form action="insert_answer" method="post">
		<c:if test = "${answerCnt == 0}">
			<br><hr><br>
			<p>답변 입력 창</p>
			<textarea id="content" name="content" rows="4" cols="86" placeholder="답변을 입력하세요" style="resize: none"></textarea>
			<input type="hidden" id ="questionIdx" name="questionIdx" value="${question.questionIdx }">
			<br>
			<p>
			<input id="answersubmit" type="submit" value="답변 등록">
			<a class="button" style="color: white;" href="get_question_list">문의 목록</a>
			</p>
		</c:if>
	</form>
		<c:if test = "${answerCnt > 0}">
			<br><hr><br>
			<p style="font-size: 1.2em; font-weight: bold;">답변 내용</p>
			<c:forEach var="answer" items="${answerList }">
			<input type="hidden" id ="questionId" name="questionId" value="${answer.questionIdx }">
			<input type="hidden" id ="answerIdx" name="answerIdx" value="${answer.answerIdx }">
				<div id="content2">${answer.content }</div>
			<br>
		<p>
			<button id="update">수정</button>
			<a class="button" style="color: white;" href="get_question_list">문의 목록</a>
			<a class="delete" href="delete_answer?answerIdx=${answer.answerIdx}&questionIdx=${answer.questionIdx}">삭제</a> 
		</p>
			</c:forEach>
		</c:if>
</div>
</div>
<script>

$(document).ready(function(){
	$("#update").on("click", Getupdate);
});

function Getupdate() {
	let questionIdx = $("#questionId").val();
	console.log("questionIdx : " + questionIdx);
	let answerIdx = $("#answerIdx").val();
	console.log("answerIdx : " + answerIdx);
	let element = document.getElementById("content2");
	let content = element.innerText;
	console.log("content : " + content);
	$("#content2").html("<textarea rows='6' cols='88' id='updateContent'>" + content
			+"</textarea>"
			+ "<button id='complete'>수정 완료</button>"
			+ "<button id='cancle'>취소</button>");
	
	$(document).ready(function(){
		$("#cancle").on("click", Getcancle);
	});
	function Getcancle() {
		let questionIdx = $("#questionId").val();
		location.href = "get_question?questionIdx=" + questionIdx;
	}

	$(document).ready(function(){
		$("#complete").on("click", ajaxGetupdate);
	});
	function ajaxGetupdate() {
		let answerIdx = $("#answerIdx").val();
		console.log("answerIdx : " + answerIdx);
		let questionIdx = $("#questionId").val();
		console.log("questionIdx : " + questionIdx);
		let inputContent = $("#updateContent").val();
		console.log("inputContent : " + inputContent);
		if(inputContent == ''){
			alert("내용을 입력하세요");
			return;
		}
		
				
		$.ajax({
			 type: "get",
             url: "update_answer", 
             data: "answerIdx=" + answerIdx + "&content=" + inputContent,
			success : function(data){
				element.textContent = inputContent;
			},
			error : function(jqXHR, textStatus, errorThrown){
				console.log("AJAX 오류" + errorThrown);
			}
		});
	}
}
</script>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>