<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"> 
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
<title>커뮤니티 카테고리별 게시글 목록 : 오송민</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
	#container { margin: auto; }
	h1, h3, p { text-align: center; }
	
	table { width: 1000px; border-collapse: collapse; }
		
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
	
	.left-community { 
 		width: 180px;
		/* float: left; */
		margin: 0px 10px 20px 10px;
		/* margin : 40px; */
		/* background-color: #F5F5F5; */
	}
	
	.right-community { 
		width: 1000px;
		float: center;
		position: relative;	
		margin: 0 auto;
	}

	table td {
	  	padding: 8px;
	  	text-align: center;
	}
		
	table td:nth-child(1) { width: 10%; }
	table td:nth-child(2) { width: 12%; }
	table td:nth-child(3) { width: 40%; text-align: left; }
	table td:nth-child(4) { width: 13%; }
	table td:nth-child(5) { width: 15%; }
	table td:nth-child(6) { width: 10%; }
	
	nav {
    	display: flex;
    	justify-content: center;
  	}
  	
  	.aBlack { 
	  color: black;
	}	
	
	.aBlack:visited {
	  color: black;
	}
	
	.theadColor {
		color: #587CB2; 
	}
	
	.highlight {
		color: #587CB2; 
	}
	
	.heart-icon {
		color: #EE634D; 
	}  	
  	
  	#writeBtn {
        background-color: #EE634D;
        border: none;
        float: right;
    }

    #writeBtn:hover {
        background-color: #E73116;
        border: none;
        float: right;
    }
    
  	#getListBtn {
        background-color: #3D5A80;
        border: none;
    }

    #getListBtn:hover {
        background-color: #587CB2;
        border: none;
    }
    
    #boldBlue {
    	border: 1px solid #3D5A80; 
    	margin: 10px 0; 
    }
  	
  	#aLinkColor {
    	color: #3D5A80; 
	}

	#activePage {
    	background-color: #3D5A80; 
   		border-color: #3D5A80;
    	color: #fff; 
	}
	
	.list-group-item.list-group-item-action.active {
	    background-color: #3D5A80;
	    color: #fff; 
	    border-color: #3D5A80; 
	}
	
	.table-hover {
		border-bottom: solid 0.5px;
		border-color: #DEE2E6;
	}
	
	.sidenav {
		background-color: #F5F5F5;
	    width: 250px;
	    text-align: center;
	    height: 1130px;
    }
	/* .sidenav {
	    background-color: #F5F5F5;
	    background-color: #3D5A80;
	    width: 220px;
	    text-align: left;
	    margin: 0px 0px 0px 110px;
    */
	
	

</style>
</head>
<script> 
	
	$(document).ready(function() {
		let selCateIdx = "${selCateIdx}";
		
		console.log("%%basic click selcateIdx%% : " + selCateIdx);

		<c:if test="${not empty selCateIdx && loginMember != null}">
			getBookmarkStatus();	
		</c:if>
	})
	
	$(document).on("click", "#bookmarkButton", function() {
        console.log("Button clicked!");
	    
	    let selCateIdx = "${selCateIdx}";
	    
	    $.ajax({
	        type: "POST",
	        url: "get_bookmark_status",
	        data: {
	            selCateIdx: selCateIdx
	        },
	        success: function(result) {
	            if (result === "1") {
	                deleteBookmark(selCateIdx);
	                
	            	$("#bookmarkOn").hide();
	                $("#bookmarkOff").show();
	            } else {
	            	addBookmark(selCateIdx);
	            	
	            	$("#bookmarkOn").show();
	                $("#bookmarkOff").hide();
	            }
	        },
	        error: function(xhr, status, error) {
	            console.log(error);
	        }
	    })
	});
	
	function getBookmarkStatus() {
		let selCateIdx = "${selCateIdx}";
		
		console.log("%%getBookmarkStatus click selcateIdx%% : " + selCateIdx);
		$.ajax({
			
			type: "POST",
			url: "get_bookmark_status",
			data: {
		        "selCateIdx": selCateIdx
			},
			success: function(result) {
				if (result === "1") {
	                // 북마크 된 상태 -> 꽉 찬 별
					$("#bookmarkOn").show();
	                $("#bookmarkOff").hide();
	            } else {
	                // 북마크 되지 않은 상태 -> 비어있는 별
	            	$("#bookmarkOn").hide();
	                $("#bookmarkOff").show();
	            }
			}
		})
	}
	function addBookmark(selCateIdx) {
		
		console.log("%%addBookmark click selcateIdx%% : " + selCateIdx);
		
		$.ajax({
			type: "POST",
			url: "add_bookmark",
			data: {
		        selCateIdx: selCateIdx
			},
			success: function(result) {
				console.log("서버 응답 addBookmark : " + result);
			},
			error: function(xhr, status, error) {
				console.log("AJAX 오류" + error);
            }
    	})	
	}
	
	function deleteBookmark(selCateIdx) {
		
		console.log("%%deleteBookmark click selcateIdx%% : " + selCateIdx);
		
		$.ajax({
			type: "POST",
			url: "delete_bookmark",
			data: {
		        selCateIdx: selCateIdx
			},
			success: function(result) {
				console.log("서버 응답 deleteBookmark : " + result);
			},
			error: function(xhr, status, error) {
				console.log("AJAX 오류" + error);
            }
    	})	
	}
</script>

<body>
<div id="container">
	<nav class="col-2 sidenav" style="float: left;">
	<!-- <nav class="list-group list-group-flush" style="float: left;"> -->
	<div class="left-community">
			<br>
			<!-- <ul class="list-group list-group-flush"> -->
			<c:if test="${loginMember != null}">
				<a href="get_community_list_by_bookmark?memberIdx=${loginMember.memberIdx }" class="list-group-item list-group-item-action">즐겨찾기</a>
			</c:if>
			<c:forEach var="cate" items="${communityCate }">
				<c:if test="${selCateIdx == cate.cateIdx }">
					<a href="get_community_list_by_cate?cateIdx=${cate.cateIdx }" class="list-group-item list-group-item-action active">${cate.cateName }</a>
				</c:if>
				<c:if test="${selCateIdx != cate.cateIdx }">
					<a href="get_community_list_by_cate?cateIdx=${cate.cateIdx }" class="list-group-item list-group-item-action">${cate.cateName }</a>
				</c:if>
			</c:forEach>
			<!-- </ul> -->
			<br>
		</div>
	</nav>
	
	<div class="right-community">
		<br>
		<c:if test="${empty selCateIdx}">
			<span class="highlight">전체 카테고리</span>
        </c:if>
        
        <div>
        <c:if test="${not empty selCateIdx}">
            <c:if test="${loginMember != null}"> 
				<div style="cursor: pointer; display: inline-block;" id="bookmarkButton">
				    <span id="bookmarkOn" style="display: none;"><i class="bi bi-heart-fill heart-icon"></i></span>
				    <span id="bookmarkOff" style="display: none;"><i class="bi bi-heart heart-icon"></i></span>
				</div>
			</c:if>
			<span class="highlight"> ${selCateName }</span> 카테고리
        </c:if>
        </div>
        
        <hr id="boldBlue"><br>
        
        <table class="table table-hover">
		  	<thead class="theadColor">
		    	<tr>
			      	<td>번호</td>
			      	<td>카테고리</td>
			     	<td>제목</td>
			      	<td>작성자</td>
			      	<td>작성일</td>
			      	<td>조회수</td>
		    	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach var="community" items="${communityPagingList }">
			    	<tr>
			      		<td>${community.boardIdx }</td>
			      		<td>${community.cateName }</td>
						<td>
							<a href="get_community?boardIdx=${community.boardIdx }&page=${paging.page }&cateIdx=${selCateIdx }" class="aBlack">${community.title }&nbsp;</a>
							<!-- 구현 안되면 삭제 -->
							<c:if test="${community.boardOri != null}"><i class="bi bi-card-image"></i></c:if>
						</td>
						<td>${community.nickname }</td> <!-- 나중에 작성자 이름or아이디or닉네임 받아오도록 -->
						<td>${community.regDate }</td>
						<td>${community.hit }</td>
			    	</tr>
		    	</c:forEach>
		    	<c:if test="${empty communityPagingList }">
					<tr>
						<td colspan="6" class="center">커뮤니티 게시글이 없습니다</td>
					</tr>
				</c:if>
		  	</tbody>
		</table>
		
		<br><hr id="boldBlue">
		
		<div>
			<a class="btn btn-primary" href="get_community_list_by_cate"  id="getListBtn" role="button">
				<i class="bi bi-list"> </i>전체 목록
			</a>
			
			<c:if test="${loginMember != null}">
				<a class="btn btn-primary" href="insert_community_page?page=${paging.page }&cateIdx=${selCateIdx }" id="writeBtn" role="button">
					<i class="bi bi-pencil-square"> </i>글 쓰기
				</a>
			</c:if>	
	    </div>
	    
		<div> <!-- 검색을 위한 폼 -->
			<form id="searchCommunity" action="get_community_list_by_keyword" method="get">
				<table class="border-none">
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
		</div>
		
		<nav aria-label="pagingNav">
		  	<ul class="pagination">
		  		<c:choose>
		        <%-- 이전 버튼 누르면 이전 블록의 첫번째 페이지로 이동 --%>
		        <c:when test="${paging.page <= paging.blockLimit}">
		        	<li class="page-item disabled">
		     	 		<span class="page-link">이전</span>
		    		</li>
		        </c:when>
		        <c:otherwise>
		        	<li class="page-item">
		        		<a class="page-link" id="aLinkColor" href="get_community_list_by_cate?page=${paging.blockPreStartPage}&cateIdx=${selCateIdx}">이전</a>
			    	</li>
		        </c:otherwise>
		    	</c:choose>
		    	
		    	<%-- 번호 누르면 해당 페이지로 이동 --%>
			    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			        <c:choose>
			            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
			            <c:when test="${i eq paging.page}">
			            	<li class="page-item active">
			                	<span class="page-link" id="activePage">${i}</span>
			                </li>
			            </c:when>
			
			            <c:otherwise>
			            	<li class="page-item">
			                	<a class="page-link" id="aLinkColor" href="get_community_list_by_cate?page=${i}&cateIdx=${selCateIdx}">${i}</a>
		                	</li>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    
			    <%-- 다음 버튼 누르면 다음 블록의 첫번째 페이지로 이동 --%>
		    	<c:choose>
			        <c:when test="${paging.blockNextStartPage > paging.maxPage}">
			        	<li class="page-item disabled">
			            	<span class="page-link">다음</span>
		            	</li>
			        </c:when>
			        <c:otherwise>
			        	<li class="page-item">
			                <a class="page-link" id="aLinkColor" href="get_community_list_by_cate?page=${paging.blockNextStartPage}&cateIdx=${selCateIdx}">다음</a>
		                </li>
			        </c:otherwise>
			    </c:choose>
		  	</ul>
		</nav>
	</div>	
</div>
<br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>