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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<title>게임 추가</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
	  integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/gameUpdate.css">	 
<!--  <style>
/* 	.commonBody {
	    height: auto;
	    background: #DDA0DD;
	    position: relative;
	    border: 1px solid red;
	    width: 60%;
	    margin: 0 auto;
	    text-align: center;
	} */
	.gameManage {
	    width: 100%;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	}
</style> -->
<script>
$(document).ready(function () {
    $("#registerQuestion").on("click", function () {
    	showTable();
    });

/*     $("#quizInquiry").on("click", function(){
        gameCheck();
        hideGameInsertTable();
    }); */
});
// 문제 등록 버튼을 누르면 게임 관리 메인 페이지로 이동하는 함수
function showTable() {
	location.href = "go_game_manage_page";
}
// 문제 등록 테이블을 숨기는 함수
function hideGameInsertTable() {
    $("#gameInsertTable").hide();
}
// 문제 등록 설명을 펼쳤다 숨겼다 하는 함수
function showExplanation() {
    var explanationText = document.getElementById("explanationText");
    if (explanationText.style.display === "none") {
        explanationText.style.display = "block";
    } else {
        explanationText.style.display = "none";
    }
}
function adminUpdateGame(quizIdx) {
	location.href = "admin_update_game?quizIdx=" + quizIdx;
}
function adminDelGame(quizIdx) {
	var confirmPop = confirm("게임을 삭제하면 복구할 수 없습니다.\n정말로 삭제하시겠습니까?");
    if (confirmPop == true) {
        //return false;
    	location.href = "admin_del_game?quizIdx=" + ${game.quizIdx };
    } else {
    	history.go(0);
    }
}
function adminGoGameList() {
	location.href = "go_game_list";
}
</script>
</head>
<body>
<br>
<div class="commonBody">
	<h1>게임 수정</h1>
	<div id="explain">
		<button class="btn btn-outline-secondary btn-sm" id="registerQuestion">게임 등록</button>
	    <button class="btn btn-outline-secondary btn-sm" id="explainGameInsert" onclick="showExplanation()">게임 수정 설명</button>
	    <p id="explanationText" style="display: none;">
	    	<br>
	        *** (필수) 카테고리, 포인트, 점수를 다시 선택한다. ***<br>
	        1. 수정할 부분을 올바르게 수정한다.<br>
	        2. 문제 등록을 버튼 클릭!
	    </p>
	    <button onclick="adminGoGameList()" id="quizInquiry" class="btn btn-outline-secondary btn-sm">게임 조회</button>
	</div>
    <div id="clickGameCate">
    
    </div>
    <div id="gameList">
    	<table class="table" id="gameListTable">
    		
    	</table>
    </div>
	<form action="admin_update_game" method="post" enctype="multipart/form-data">
		<table class="table" class='gameManage' id="gameInsertTable">
			<tr>
			<th>퀴즈 카테고리</th>
				<td>
				<c:forEach var="cate" items="${cate }">
				<label>
			    	<input type="radio" name="cateIdx" value="${cate.cateIdx }">
			    	${cate.cateName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  	</label>
				</c:forEach>
			  	</td>
			</tr>
			<tr>
				<th><br><br>퀴즈</th>
				<td>
					<textarea rows="5" cols="30" name="quiz">${game.quiz }</textarea>
				</td>
			</tr>
 			<tr>
				<th>적립 포인트</th>
				<td>
					<label>
						<input type="radio" name="savedPoint" value=100>
					    	100&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedPoint" value=300>
					    	300&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedPoint" value=500>
					    	500&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedPoint" value=700>
					    	700&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedPoint" value=1000>
					    	1000
					</label>
				</td>
			</tr>
			<tr>
				<th>점수</th>
				<td>
					<label>
						<input type="radio" name="savedScore" value=10>
					    	10 점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedScore" value=15>
					    	15 점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedScore" value=30>
					    	30 점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedScore" value=45>
					    	45 점&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</label>
					<label>
						<input type="radio" name="savedScore" value=90>
					    	90 점
					</label>
				</td>
			</tr>
			<tr>
				<th><br><br>보기 1</th>
				<td>
					<textarea rows="3" cols="30" name="exampleNo1">${game.exampleNo1 }</textarea>
				</td>
			</tr>
			<tr>
				<th><br><br>보기 2</th>
				<td>
					<textarea rows="3" cols="30" name="exampleNo2">${game.exampleNo2 }</textarea>
				</td>
			</tr>
			<tr>
				<th><br><br>보기 3</th>
				<td>
					<textarea rows="3" cols="30" name="exampleNo3">${game.exampleNo3 }</textarea>
				</td>
			</tr>
			<tr>
				<th><br><br>보기 4</th>
				<td>
					<textarea rows="3" cols="30" name="exampleNo4">${game.exampleNo4 }</textarea>
				</td>
			</tr>
			<tr>
				<th><br><br>보기 5 (정답)</th>
				<td>
					<textarea rows="3" cols="30" name="quizAnswer">${game.quizAnswer }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="center">
					<input type="hidden" name="quizIdx" value="${game.quizIdx }" >
					<input type="submit" value="문제 수정">
					<button onclick="adminDelGame(quizIdx)">문제 삭제</button>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html> 





