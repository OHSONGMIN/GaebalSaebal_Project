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
<title>게임 실행하기!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css"> 
<style>
.gameBody { /* 전체 페이지 이거 쓰면 되겠답 */
	    height: auto; /* 또는 원하는 높이 */
	   	margin: 0 auto;
	    background: #DDA0DD; /* .gameBody의 배경색 */
	    position: relative; /* 상대 위치 설정 */
	    
		border: 1px solid red;
		width: 60%;
		margin: 0 auto;
	}
	
 	button {
		border: none;         /* 테두리 제거 */
	    background: none;     /* 배경 제거 */
	    padding: 0;           /* 내부 간격 제거 */
	    margin: 0;            /* 외부 여백 제거 */
	    outline: none;        /* 포커스 표시 제거 */
	    text-decoration: none; /* 텍스트 밑줄 제거 */
	    box-shadow: none;     /* 그림자 제거 */
	} 
.games button {
	 /* 원하는 스타일 설정 */
	    cursor: pointer;
	 	border : 1px solid black;
	    background: black;
	    color: white;
	    border-radius: 50%;
	    width: 200px;
	    height: 200px;
	    margin: 0 30px 0 30px;
	    text-align: center; /* 가운데 정렬을 위한 스타일 추가 */
}
</style>

</head>
<body>

    <!-- 게임 선택 -->
    <div class="gameBody">
    	<div class="games">
			<p>하낫둘 하낫둘</p>
			<p><a href="devquiz_main">메인화면 가기</a></p>
		</div>
    		
    </div>
    	
    
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>
</html>



