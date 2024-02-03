<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>개발새발</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
	  integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gaebal/questionInsert.css">	
<script>
let cateName;
let cateIdx;
let productIdx;
let productName;

$(document).ready(function () {
    $("#questionCate").on("change", function () {
        cateIdx = $(this).val();
        cateName = $(this).find(":selected").data("secondvalue"); 
        
        gaebalGetProdsMainCate();
        
        console.log("cateName : " + cateName);
        console.log("cateIdx : " + cateIdx);
        
    });
    
});

function validateForm() {
    var selectedValue = document.getElementById("questionCate").value;

    if (selectedValue === "") {
        alert("카테고리를 선택해주세요.");
        return false; // 폼 전송을 막음
    }

    // 추가적인 유효성 검사 로직이 필요한 경우 여기에 추가

    return true; // 폼 전송 허용
}

// 1. cateName을 서버에 보낸다.
function gaebalGetProdsMainCate() {
	$.ajax({
        url: "get_prods_main_cate",
        type: "post",
        data: { cateName: cateName},
        success: function (data) {
           /*  alert("상품 대분류 이름 받기 성공!"); */
            console.log(data);
            console.log("받은 데이터의 길이(prodsMainCate) : " + data.length);

            let dispTag = " ";
            if (data.length === 0) {
                dispTag += '<input type="hidden" name="productIdx" value="0">';
                $("#prodsMainCateDropdown").html(dispTag);
                $("#prodsNameDropdown").html(dispTag);
            } else {
                dispTag += "<td>상품 대분류</td>"
                    dispTag += "<td><select id='prodsMain' onchange='gaebalGetProds()' name='prodsMainCateIdx'>";
                    dispTag += "<option value='' disabled selected>상품 카테고리를 선택해주세요</option>";
                    for (let i = 0; i < data.length; i++) {
                        dispTag += '<option value=' + data[i].cateIdx + ' data-secondvalue="' + data[i].cateName + '">' + data[i].cateName + '</option>';
                    }
                    dispTag += "</select></td>";
                    $("#prodsMainCateDropdown").html(dispTag);
                    console.log("productName: " + productName);
            }
        },
        error: function() {
          /*   alert("상품 대분류 이름 받기 실패 ㅠ"); */
        }
    });
}
function gaebalGetProds() {
	productIdx = $("#prodsMain").val();
	productName = $("#prodsMain").find(":selected").data("secondvalue");
	
	console.log("gaebalGetProds에서 사용가능한 productIdx : " + productIdx); //삭제
	console.log("productName in gaebalGetProds: " + productName); //삭제 
	
    $.ajax({
        url: "get_prods",
        type: "post",
        data: { prodsMainCate : productName, prodsIdx : productIdx },
        success: function (data) {
            console.log(data);
            console.log("받은 데이터의 길이(prodsName) : " + data.length);

			let prodsNameTag = " ";
			if (data.length === 0) {
				let dispTag = " ";
                dispTag += '<td><input type="hidden" name="productIdx" value="0"></td>';
                $("#prodsNameDropdown").html(dispTag);
			} else {
				prodsNameTag += "<td>상품명</td>"
					prodsNameTag += "<td><select id='prodName' name='productIdx'>";
					prodsNameTag += "<option value='' disabled selected>상품명을 선택해주세요</option>";
					for (let i=0; i < data.length; i++) {
						prodsNameTag += '<option value=' + data[i].productIdx + '>' + data[i].productName + '</option>';
					}
					prodsNameTag += "</select></td>";
					$("#prodsNameDropdown").html(prodsNameTag);
			}
        },
        error: function() {
            alert("상품 이름 받아오는 ajax 실패! 다시 도전하세요~~");
        }
    });
}
</script>
</head>
<body>

<div class="commonBody">
	<form action="update_question" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
        <table class="table">
            <tr>
                <td>제목</td>
                <td><input type="text" name="questionTitle" value="${question.questionTitle }"></td>
            </tr>
            <tr>
                <td>카테고리</td>
                <td>
                    <select id="questionCate" name="cateIdx">
                    	<option value="" disabled selected>카테고리를 선택하세요</option>
                        <c:forEach var="qCate" items="${cate}">
                            <option value="${qCate.cateIdx}" data-secondvalue="${qCate.cateName}">${qCate.cateName}</option>
                        </c:forEach>
                    </select>
                </td>
             </tr>   
             <tr id="prodsMainCateDropdown">
             <!-- Ajax 데이터 출력되는 곳 ~~~ -->
             </tr>
             <tr id="prodsNameDropdown">
             <!-- Ajax 데이터 출력되는 곳 2.상품 이름 ~~~ -->
             </tr>             
            <tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="file">
				</td>
			</tr>
            <tr>
                <td>내용</td>
                <td>
                    <textarea rows="10" cols="20" name="questionContent">${question.questionContent}</textarea>
                </td>
            </tr>
            <tfoot>
                <tr>
                    <td colspan=2>
                    	<input type="hidden" name="questionIdx" value="${question.questionIdx }" >
                        <input type="submit" value="문의 수정">
                    </td>
                </tr>
            </tfoot>
        </table>
	</form>
</div>
</body>
</html>
