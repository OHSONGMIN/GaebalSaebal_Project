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
let cateIdx;
let cateName;
let productIdx;
let productName;

$(document).ready(function () {
    $("#questionCate").on("change", function(){
        cateIdx = $(this).val();
        cateName = $(this).find(":selected").data("secondvalue");

        gaebalGetProdsMainCate();
        
        console.log("cateIdx : " + cateIdx);
        console.log("cateName : " + cateName);
    });
});

function validateForm() {
    var selectedCate = document.getElementById("questionCate").value;
    var selectedProdsMain = document.getElementById("prodsMain").value;
    var selectedProdName = document.getElementById("prodName").value;

    if (selectedCate === "") {
        alert("카테고리를 선택해주세요");
        return false; // 폼 전송을 막음
    }
    if (selectedProdsMain === "") {
        alert("상품 카테고리를 선택해주세요");
        return false;
    }
    if (selectedProdName === "") {
        alert("상품명을 선택해주세요");
        return false;
    }

    // 추가적인 유효성 검사 로직이 필요한 경우 여기에 추가
    // 상품 대분류 선택안해도 폼 전송을 막음
    // 상품 이름 선택안해도 폼 전송을 막음

    return true; // 폼 전송 허용
}


//1. cateName을 서버에 보낸다.
// [이하영] : 문의글 작성시, 카테고리를 '상품' 선택했을때 상품 대분류 td가 출력되게 함.
function gaebalGetProdsMainCate() {
	$.ajax({
        url: "get_prods_main_cate",
        type: "post",
        data: { cateName: cateName},
        success: function (data) {
          /*   alert("상품 대분류 이름 받기 성공!"); */
            console.log(data);
            console.log("받은 데이터의 길이(prodsMainCate) : " + data.length);

            let dispTag = " ";
            if (data.length === 0) {
/*                 dispTag += '<td rowspan="2" colspan="2"><input type="hidden" name="productIdx" value="0"></td>'; */
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
         /*    alert("상품 대분류 이름 받기 실패 ㅠ"); */
        }
    });
}

// 1. cateName을 서버에 보낸다.
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
<br>
<div class="commonBody">
	<form action="question_write" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
        <table class="table">
            <tr>
                <td>제목 : &nbsp;&nbsp;&nbsp;<input type="text" name="questionTitle"></td>
                <td>
                	<!-- <input type="file" name="file"> -->
                	<label for="uploadFile">이미지</label> <input class="product-pro"
					type="file" title="이미지" name="uploadFile" id="formFile"
					accept="image/*" onchange="previewImage(this);">
                </td>
            </tr>
            <tr>
            	<td>카테고리</td>
                <td>
					<select id="questionCate" name="cateIdx">
                    	<option value="" disabled selected>카테고리를 선택해주세요</option>
                        <c:forEach var="qCate" items="${cate}">
                            <option value="${qCate.cateIdx}" data-secondvalue="${qCate.cateName}">${qCate.cateName}</option>
                        </c:forEach>
                    </select>
				</td>
                <td>
                </td>
             </tr>
             <tr id="prodsMainCateDropdown">
             <!-- Ajax 데이터 출력되는 곳 1.상품 대분류 ~~~ -->
             </tr>
             <tr id="prodsNameDropdown">
             <!-- Ajax 데이터 출력되는 곳 2.상품 이름 ~~~ -->
             </tr>
             <tr>
                <td colspan='2'>내용</td>
             </tr>
            <tr>
                <td colspan='2'>
                    <textarea rows="15" cols="70" name="questionContent"></textarea>
                </td>
            </tr>
            <tfoot>
                <tr>
                    <td colspan=2>
                        <input type="submit" value="문의 등록">
                    </td>
                </tr>
            </tfoot>
        </table>
	</form>
</div>
</body>
</html>
