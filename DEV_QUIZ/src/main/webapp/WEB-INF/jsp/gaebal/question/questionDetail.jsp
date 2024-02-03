<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gaebal/questionDetail.css">
<script>
    function gaebalGoBack() {
        history.back();
    }
    function gaebalGoWriteQuestion() {
        location.href = "have_a_question";
    }
    function gaebalSetQuestion() {
    	location.href = "set_question?questionIdx=" + ${question.questionIdx};
    }
function gaebalDeleteQuestion() {
	var confirmPop = confirm("문의글을 삭제하면 복구할 수 없습니다.\n정말로 삭제하시겠습니까?");
    if (confirmPop == true) {
        //return false;
    	location.href = "del_question?questionIdx=" + ${question.questionIdx};
    } else {
    	history.go(0);
    }
}
</script>
</head>
<body>
<br>
	<div class="commonBody">
		<table class="table">
			<thead>
				<tr>
					<th colspan=5 id="title">${question.questionTitle}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="questionInfo" id="nickname">작성자: ${question.nickname}</td>
					<td colspan=3 class="questionInfo" id="ori_Date">
						첨부파일 : ${question.questionOri}<br>
						등록일 : ${question.questionDate}
					</td>
				</tr>
				<tr>					
					<td colspan=4 class='questionContent'>
						<c:if test="${not empty question.questionFile}">
	    					<img src="${pageContext.request.contextPath }/resources/img_product/${question.questionFile}" alt=""><br><br>
						</c:if>
						${fn:replace(question.questionContent, replaceChar, "<br/>")}
					</td>
				</tr>
			</tbody>
			<tfoot>
 				 <tr>
            		<td><button onclick="gaebalGoBack()" class="btn btn-outline-secondary btn-sm">목록</button></td>
            		<td><button onclick="gaebalGoWriteQuestion()" class="btn btn-outline-secondary btn-sm">글쓰기</button></td>
            	<c:if test="${loginMember.memberIdx eq question.memberIdx}">
            		<td><button onclick="gaebalSetQuestion()" class="btn btn-outline-secondary btn-sm" id="upBtn">수정</button></a></td>
            		<td><button onclick="gaebalDeleteQuestion()" class="btn btn-outline-secondary btn-sm" id="delBtn">삭제</button></td>
       			</c:if>
        		</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>

