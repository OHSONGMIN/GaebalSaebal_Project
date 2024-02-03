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
<title>상품목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
#container {
    width: 80%;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
    color: #333;
    font-size: 1.5em;
    text-align: center;
}

form {
    margin-bottom: 20px;
    text-align: center;
}

form table {
    margin: 0 auto;
    width: 50%;
}

table th, tr, td {
    text-align: center;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

th, td {
    border: 1px solid #ddd;
    padding: 10px;
}

form table tr td {
    vertical-align: middle; 
}

form table select, form table input[type="text"], form table input[type="submit"] {
    height: 30px; 
    margin-bottom: 5px; 
    margin-top: 5px; 
}

thead {
    background-color: #f2f2f2;
}

.product-table a {
    color: #007bff;
    text-decoration: none;
}

.product-table a:hover {
    text-decoration: underline;
}

.product-table a.delete-link {
    color: #ff0000; 
    font-weight: bold;
}

.center {
    text-align: center;
}

 .center a {
    color: #000; 
    font-weight: bold; 
  }

p {
    margin: 20px 0;
}

a {
    color: #007bff;
    text-decoration: none;
    font-weight: bold;
}

a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>
	<div id="container">
		<h2>상품 목록</h2>
		<form action="get_product_list" method="get">
			<table>
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
			</table>
		</form>

		<table class="product-table">
			<thead>
				<tr>
					<th>카테고리</th>
					<th>상품번호</th>
					<th>상품명</th>
					<th>가격</th>
					<th>등록일</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${productPagingList}">
        <tr>
            <td>
                <c:forEach var="cate" items="${cate}">
                    <c:if test="${cate.cateIdx eq product.cateIdx}">
                        ${cate.cateName}
                    </c:if>
                </c:forEach>
            </td>
            <td>${product.productIdx}</td>
            <td>
                <a href="get_product?productIdx=${product.productIdx}">${product.productName}</a>
            </td>
            <td>${product.productPrice}</td>
            <td>${product.productDate}</td>
            <td>
                <a href="delete_product?productIdx=${product.productIdx}" class="delete-link">삭제</a>
            </td>
        </tr>
    </c:forEach>
				<c:if test="${empty productList }">
					<tr>
						<td colspan="5" class="center">상품이 없습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<p>
			<a href="insert_product_view">상품등록</a>
		</p>
			<div class = "center">
		    <c:choose>
		        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
		        <c:when test="${paging.page<=1}">
		            <span>[이전]</span>
		        </c:when>
		        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
		        <c:otherwise>
		            <a href="get_member_product_list?page=${paging.page-1}">[이전]</a>
		        </c:otherwise>
		    </c:choose>
		
		    <%--  for(int i=startPage; i<=endPage; i++)      --%>
		    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		        <c:choose>
		            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
		            <c:when test="${i eq paging.page}">
		                <span>${i}</span>
		            </c:when>
		
		            <c:otherwise>
		                <a href="get_product_list?page=${i}">${i}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		
		    <c:choose>
		        <c:when test="${paging.page>=paging.maxPage}">
		            <span>[다음]</span>
		        </c:when>
		        <c:otherwise>
		                <a href="get_product_list?page=${paging.page+1}">[다음]</a>
		        </c:otherwise>
		    </c:choose>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>