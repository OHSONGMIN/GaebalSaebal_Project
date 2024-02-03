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
<title>상품등록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
h2 {
	margin-top: 20px;
    color: #333;
    font-size: 1.5em;
    text-align: center;
}
.product {
	margin-bottom: 20px;
}

.product label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

.product .product-pro {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

.button {
	background-color: #007BFF;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.button:hover {
    background-color: #8080ff; 
}
</style>
</head>
<body>
	<div class="container" style="max-width: 700px; margin: 0 auto;">
		<h2>상품 등록</h2>
		<form action="insert_product" method="post"
			enctype="multipart/form-data">

			<div class="product">
			 <input type="hidden" name="productStock" value="1">
			 
				<label for="cateIdx">카테고리</label> <select class="product-pro"
					name="cateIdx" title="카테고리" required>
					<c:forEach var="cate" items="${cate}">
						<option value="${cate.cateIdx}">${cate.cateName}</option>
					</c:forEach>
				</select>
			</div>

			<div class="product">
				<label for="productName">상품명</label> <input class="product-pro"
					type="text" name="productName" title="상품명"
					placeholder="productName" required>
			</div>

			<div class="product">
				<label for="productPrice">가격</label> <input class="product-pro"
					type="text" name="productPrice" title="가격"
					placeholder="productPrice" required>
			</div>

			<div class="product">
				<label for="uploadFile">이미지</label> <input class="product-pro"
					type="file" title="이미지" name="uploadFile" id="formFile"
					accept="image/*" onchange="previewImage(this);" required>
			</div>

			<div class="product">
				<label for="imagePreview">이미지 미리보기</label> <img id="imagePreview"
					src="" alt="imagePreview"
					style="max-width: 300px; max-height: 300px;">
			</div>

			<script>
				// 이미지 미리보기를 위한 JavaScript 코드
				function previewImage(input) {
					var preview = document.getElementById('imagePreview');
					preview.style.display = 'block';

					var reader = new FileReader();
					reader.onload = function(e) {
						preview.src = e.target.result;
					};

					reader.readAsDataURL(input.files[0]);
				}
			</script>
			<div class="product">
				<label for="productContent">상품정보</label>
				<textarea class="product-pro" name="productContent" title="상품정보"
					rows="15" required></textarea>
			</div>

			<input class="button" type="submit" value="등록하기" title="등록하기" /> <input
				class="button" type="reset" value="다시입력" title="다시입력" /> <input
				class="button" type="button" value="작성취소" title="작성취소"
				onclick="location.href='get_product_list'" />
		</form>
	</div>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>