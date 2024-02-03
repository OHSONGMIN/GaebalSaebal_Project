<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty loginMember}">
    <%@ include file="/WEB-INF/jsp/include/header_bf.jspf"%>
</c:if>
<c:if test="${not empty loginMember}">
    <%@ include file="/WEB-INF/jsp/include/header_af.jspf"%>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/css/style.css">
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
    }

    #container {
        width: 80%;
        margin: 0 auto;
        margin-top: 20px;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h2 {
        color: #333;
        margin: 0 auto;
        font-size: 1.5em;
        text-align: center;
        margin-bottom: 20px;
    }

    #searchForm {
        text-align: center;
        margin-bottom: 20px;
        display: flex;
        justify-content: center; /* 수평 가운데 정렬을 위해 추가 */
    }

    #searchForm table {
        width: auto;
        margin: 0;
        display: inline-block; /* 가운데 정렬을 위해 추가 */
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

    form table select, form table input[type="text"], form table input[type="submit"]
    {
        height: 30px;
        margin-bottom: 5px;
        margin-top: 5px;
    }

    .product-card {
        flex: 0 0 calc(33.33% - 20px);
        margin: 10px;
        box-sizing: border-box;
        text-align: center;
    }

    .product-card img {
        width: 200px;
        height: 250px;
        object-fit: cover;
        border: 1px solid #ddd;
        border-radius: 8px;
    }

    .product-card h3 {
        margin-top: 10px;
        font-size: 16px;
    }

    .product-card h3 a {
        font-weight: bold;
    }

    @media screen and (max-width: 1200px) {
      .product-card {
        flex: 0 0 calc(33.33% - 20px);
    }
    .product-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }
}
@media screen and (max-width: 800px) {
    .product-card {
        flex: 0 0 calc(33.33% - 20px);
    }
    
    .product-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        
    }
}

 .center {
        text-align: center;
        margin-top: 20px;
    }

    .paging-button {
        display: inline-block;
        padding: 8px 12px;
        margin: 4px;
        border: 1px solid #ddd;
        background-color: #f9f9f9;
        color: #333;
        text-decoration: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .paging-button:hover {
        background-color: #ddd;
    }

    .current {
        background-color: #007BFF;
        color: #fff;
        border: 1px solid #007BFF;
    }

    .disabled {
        pointer-events: none;
        background-color: #ddd;
        color: #666;
        border: 1px solid #ddd;
    }
     .center {
        text-align: center;
        margin-top: 20px;
    }

    .paging-button {
        display: inline-block;
        padding: 8px 12px;
        margin: 4px;
        border: 1px solid #ddd;
        background-color: #f9f9f9;
        color: #333;
        text-decoration: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .paging-button:hover {
        background-color: #ddd;
    }

    .current {
        background-color: #007BFF;
        color: #fff;
        border: 1px solid #007BFF;
    }

    .disabled {
        pointer-events: none;
        background-color: #ddd;
        color: #666;
        border: 1px solid #ddd;
    }
</style>
</head>
<body>
<div id="container">
    <h2>상품 목록</h2>
    <form id="searchForm" action="get_member_product_list" method="get">
        <table>
            <tr>
                <td>
                    <select name="searchCondition">
                        <c:forEach var="option" items="${conditionMap}">
                            <option value="${option.value}">${option.key}</option>
                        </c:forEach>
                    </select>
                    <input type="text" name="searchKeyword">
                    <input type="submit" value="검색">
                </td>
            </tr>
        </table>
    </form>

    <div class="product-list" style="display: flex; flex-wrap: wrap; justify-content: space-around;">
    <c:forEach var="product" items="${productPagingList}">
        <div class="product-card">
            <a href="<%=request.getContextPath()%>/get_member_product?productIdx=${product.productIdx}"></a>
            <img src="${pageContext.request.contextPath }/resources/img_product/${product.productFile}" alt="${product.productName}">
            <a href="<%=request.getContextPath()%>/get_member_product?productIdx=${product.productIdx}" style="color: #004080;">${product.productName}</a>
        </div>
    </c:forEach>
</div>

    <c:if test="${empty productList}">
        <p style="text-align: center;">상품이 없습니다.</p>
    </c:if>
    
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
		               <a href="get_member_product_list?page=${i}">${i}</a>
		            </c:otherwise>
		        </c:choose>
		    </c:forEach>
		
		    <c:choose>
		        <c:when test="${paging.page>=paging.maxPage}">
		            <span>[다음]</span>
		        </c:when>
		        <c:otherwise>
		                <a href="get_member_product_list?page=${paging.page+1}">[다음]</a>
		        </c:otherwise>
		    </c:choose>
		</div>
</div>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>
