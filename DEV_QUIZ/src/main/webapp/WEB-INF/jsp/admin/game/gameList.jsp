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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/gameList.css">	 
<script>
 $(document).ready(function () {
	//gameList(cateIdx);

});
//문제 등록 버튼을 누르면 게임 관리 메인 페이지로 이동하는 함수
function gameInsert() {
	location.href = "go_game_manage_page";
}
// 문제 수정 버튼을 누르면 수정 페이지로 이동하는 함수
function adminSetGame(quizIdx) {
	location.href = "admin_set_game?quizIdx=" + quizIdx;
}
// 문제 삭제 버튼을 누르면 삭제 알람이 뜨고 확인되면 삭제되는 함수
function adminDelGame(quizIdx) {
	var confirmPop = confirm("게임을 삭제하면 복구할 수 없습니다.\n정말로 삭제하시겠습니까?");
    if (confirmPop == true) {
        //return false;
    	location.href = "admin_del_game?quizIdx=" + quizIdx;
    } else {
    	history.go(0);
    }
}
// 카테고리별 게임 리스트를 확인할 수 있는 ajax함수
function gameList(cateIdx) {
	$.ajax({
		url: "admin_get_game",
		type: "post",
		data: { cateIdx : cateIdx },
		success: function (data) {
			let dispTag = " " ;
			
/* 			dispTag += '<form id="searchForm" action="go_serach_question_list" method="get">';
			dispTag += '<table class="table">';
			dispTag += '<tr>';
			dispTag += '<td>';
			dispTag += '<select name="searchCondition">';
			dispTag += '<c:forEach var="option" items="${gameMap }">';
			dispTag += '<option value="${option.value }">${option.key }</option>';
			dispTag += '</c:forEach>';			
			dispTag += '</select>';
			dispTag += '<input type="text" name="searchKeyword">';	
			dispTag += '<input type="submit" value="검색">';	
			dispTag += '</td></tr></table></form>'; */
			
			for (let i = 0; i < data.length; i++) {
			    dispTag += "<tr>";
			    dispTag += "<th>" + data[i].quizIdx + "번. 문제" + data[i].quiz + " <" + data[i].savedPoint + "포인트, " + data[i].savedScore + "점>"
			    		+ "&nbsp;&nbsp;&nbsp;<button class='btn btn-outline-secondary btn-sm' onclick='adminSetGame(" + data[i].quizIdx + ")'>수정</button>&nbsp;&nbsp;&nbsp;"
			    		+ "<button class='btn btn-outline-secondary btn-sm' onclick='adminDelGame(" + data[i].quizIdx + ")'>삭제</button></th>";
			    dispTag += "</tr>";
			    
			    dispTag += "<tr>";
			    dispTag += "<td>보기 1." + data[i].exampleNo1 + "</td>";
			    dispTag += "</tr>";
			    
			    dispTag += "<tr>";
			    dispTag += "<td>보기 2." + data[i].exampleNo2 + "</td>";
			    dispTag += "</tr>";
			    
			    dispTag += "<tr>";
			    dispTag += "<td>보기 3." + data[i].exampleNo3 + "</td>";
			    dispTag += "</tr>";
			    
			    dispTag += "<tr>";
			    dispTag += "<td>보기 4." + data[i].exampleNo4 + "</td>";
			    dispTag += "</tr>";
			    
			    dispTag += "<tr>";
			    dispTag += "<td>보기 5(정답)." + data[i].quizAnswer + "</td>";
			    dispTag += "</tr>";
			}
			dispTag += "</table>"; // 테이블 닫기
			
			$("#gameListTable").html(dispTag);
		},
		error: function() {
			console.log("게임 데이터 받기 실패닷! 다시 도전~~");
		}
	});
	
}

</script>
</head>
<body>
<%--  \${game } : ${game } <br>
\${cate } : ${cate } --%>
<!-- <h1>gameList.jsp</h1> -->
<br>
<div class="commonBody">
	<h1>게임 조회</h1>
	<div id="explain">
		<button class="btn btn-outline-secondary btn-sm" onclick="gameInsert()">게임 등록</button>
    	<!-- <button class='btn btn-outline-secondary btn-sm'>게임 검색</button> -->
    	<div>
<%--     	<table class="table">
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
			</table> --%>
		</div>
	   <!--  <button class="btn btn-outline-secondary btn-sm" id="explainGameInsert" onclick="showExplanation()">문제 등록 설명</button>
	    <p id="explanationText" style="display: none;">
	        1. 문제를 입력한다.<br>
	        2. 적립 포인트를 선택한다.<br>
	        3. 점수를 선택한다.<br>
	        4. 정답을 포함한 보기 5개를 입력한다.<br>
	        5. 문제 등록을 버튼 클릭!
	    </p> -->
<!-- 	    <button class="btn btn-outline-secondary btn-sm" id="quizListBtn">게임 조회</button> -->
	    <div>
	    <hr>
	    <c:forEach var="gameCate" items="${cate}">
	    	<button class='btn btn-outline-secondary btn-sm' onclick="gameList(${gameCate.cateIdx})" value="${gameCate.cateIdx }">${gameCate.cateName }</button>
    	</c:forEach>
	    </div>
	</div>
    <div id="gameList">
    	<br>
    	<table id="gameListTable" class="table">
    		
    	</table>
    </div>
</div>
</body>
</html>





