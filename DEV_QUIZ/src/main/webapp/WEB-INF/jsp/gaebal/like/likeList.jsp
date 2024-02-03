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
<title>마이페이지 - 찜 목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
function checkAll(source) { // 상단 체크시 전체 체크되는 함수
    var checkboxes = document.getElementsByName('likes'); // 체크박스 그룹의 이름

    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = source.checked;
    }
}

function deleteLike() {
	let answer = confirm("선택한 상품을 목록에서 삭제하시겠습니까?");
	if(answer) {
		let $form = $("#form");
		$form.attr("action", "delete_like").submit();
		alert("삭제되었습니다.");
	}
}
	
function insertCart() {
	let answer = confirm("선택한 상품을 장바구니에 추가하시겠습니까?");
	if(answer) {
		let $form = $("#form");
		$form.attr("action", "insert_cart_like").submit();
		alert("추가되었습니다.");
	}
}
</script>
<style>
	#temp {
		margin: 0;
		padding: 5% 20%;
	}
	
	#h1_1 {
		margin: 0;
		padding: 0;
		font-size: 30px;
		text-align: center;
	}
	
	#table_1 {
		margin: 0;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
		font-size: 20px;
		width: 100%;
		border-collapse: collapse;
	}
	
	#table_1>thead {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
	}
	
	#table_1 th {
		padding: 5px;
	}
	
	#table_1>tbody {
		border-bottom: 1px solid black;
	}
	
	#table_1 td {
		padding: 5px;
	}
	
	.like_button1 {
		cursor: pointer; /* 버튼 위로 올라가면 손가락 모양 */
		width: 45%;
		height: 60px;
		font-size: 20px;
		text-align: center;
		color: #293241;
		padding: 10px;
		background-color: #fff;
		border: 2px solid #98c1d9; /* 테두리 */
		border-radius: 5px; /* 테두리 모서리부분 */
		font-weight: 800; /* 글자 굵기 */
	}
	
	.like_button1:hover {
		color: #fff;
		background-color: #EE6C4D;
		border: none;
	}
	
	.like_button2 {
		cursor: pointer; /* 버튼 위로 올라가면 손가락 모양 */
		width: 45%;
		height: 60px;
		font-size: 20px;
		text-align: center;
		color: #293241;
		padding: 10px;
		background-color: #98C1D9;
		border: 2px solid #98c1d9; /* 테두리 */
		border-radius: 5px; /* 테두리 모서리부분 */
		font-weight: 800; /* 글자 굵기 */
	}
	
	.like_button2:hover {
		color: #E0FBFC;
		background-color: #3D5A80;
		border: none;
	}
    
    #table_1_th_1 {
        width: 5%;
    }
    #table_1_th_2 {
        width: 25%;
    }
    #table_1_th_3 {
        width: 20%;
    }
    #table_1_th_4 {
        width: 20%;
    }
    #table_1_th_5 {
        width: 20%;
    }
    
</style>
</head>
<body>

    <div id="temp">
    	<hr>
        <h1 id="h1_1">내 찜 목록</h1>
        <hr>
        <br>
        
        <form id="form" method="post">
            <table id="table_1">
                <thead>
                    <tr>
                        <th id="table_1_th_1"><input type="checkbox" onclick="checkAll(this)"></th>
                        <th id="table_1_th_2">상품</th>
                        <th id="table_1_th_3">이름</th>
                        <th id="table_1_th_4">가격</th>
                        <th id="table_1_th_5">담은 날짜</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${ empty likeList }">
                            <tr>
                                <td colspan="5">찜 목록에 담긴 상품이 없습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="like" items="${likeList}">
                                <tr>
                                    <td><input type="checkbox" class="check" name="likes" value="${ like.productIdx }"></td>
                                    <td><img src="resources/images/profile.png"></td> <!-- 상품 사진 넣을 자리 --> <!-- 찜, 장바구니, 주문하기 -->
                                    <!-- <img src="resources/images/파일이름(like.productFile)"> -->
                                    <td>${ like.productName }</td>
                                    <td>${ like.productPrice }</td>
                                    <td>${ like.likeDate }</td>
                                </tr>
                            </c:forEach>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <button onclick="deleteLike()" class="like_button1">찜 목록에서 삭제</button>
                                        <button onclick="insertCart()" class="like_button2">장바구니에 담기</button>
                                    </td>
                                </tr>
                            </tfoot>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </form>
    </div>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>