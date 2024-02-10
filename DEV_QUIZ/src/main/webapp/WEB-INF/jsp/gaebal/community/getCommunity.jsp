<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>커뮤니티 게시글 상세 : 오송민</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
	#container { margin: auto; }
	h1, h3, p { text-align: center; }
	
	table { width: 1000px; border-collapse: collapse; }
		
	.center { text-align: center; }
	
	.border-none, .border-none td { border: none; }
	
	.left-community { 
 		width: 180px;
		/*float: left;
		position: relative;	 */
		margin: 0px 10px 20px 10px;
	}
	
	.right-community { 
		width: 1000px;
		float: center;
		position: relative;	
		margin: 0 auto;
	}
		
	.button.float-right {
	    display: inline-block;
	    float: right;
	}
	
	nav {
    	display: flex;
    	justify-content: center;
  	}
  	
    .communityUploadImg img {
        max-width: 100%; 
        max-height: 300px; 
        width: auto; 
        height: auto; 
    	display: block;
    	margin: 0 auto;
    }
    
    .highlight {
		color: #587CB2; 
	}
	
	#boldBlue {
    	border: 1px solid #3D5A80; 
    	margin: 10px 0; 
    }
    
    #getListBtn {
        background-color: #3D5A80;
        border: none;
    }

    #getListBtn:hover {
        background-color: #587CB2;
        border: none;
    }
       
    #deleteBtn {
        background-color: #EE634D;
        border: none;
    }

    #deleteBtn:hover {
        background-color: #E73116;
        border: none;
    }
    
    #editListBtn {
        background-color: #3D5A80;
        border: none;
    }

    #editListBtn:hover {
        background-color: #587CB2;
        border: none;
    }
    
    .left-align {
    	display: inline-block;
    	float: left;
    }
    
    .right-align {
        display: inline-block;
        float: right;
    }
    
    .btn-link {
	    background: none;
	    border: none;
	    padding: 0;
	    font: inherit;
	    cursor: pointer;
	    color: #3D5A80; 
	}
	
	.btn-link:hover {
		text-decoration: underline;
		color: #3D5A80;
	}
	
	.commentContainer {
		display: flex;
        justify-content: space-between;
        align-items: center;
	}
    
    .commentBtn {
    	display: inline-block;
    }
    
    #insertCommentBtn {
    	background-color: #3D5A80;
        border: none;
    }
    
    #insertCommentBtn:hover {
    	background-color: #587CB2;
        border: none;
    }
    
    .sidenav {
      	background-color: #F5F5F5;
      	width: 250px;
      	text-align: center;
    }
    
	</style>
</head>
<body>

<div id="container">
	<nav class="col-2 sidenav" style="float: left;">
		<div class="left-community">
			<br>
			<c:if test="${loginMember != null}">
				<a href="get_community_list_by_bookmark?memberIdx=${loginMember.memberIdx }" class="list-group-item list-group-item-action">내 즐겨찾기</a>
			</c:if>
			<c:forEach var="cate" items="${communityCate }">
				<a href="get_community_list_by_cate?cateIdx=${cate.cateIdx }" class="list-group-item list-group-item-action">${cate.cateName }</a>
			</c:forEach>
		</div>
	</nav>
	
	
	<div class="right-community">
		<br>
		<c:if test="${selCateName != null}">
			<span class="highlight">${selCateName } </span>카테고리
		</c:if>
		<c:if test="${selCateName == null}">
	  		<span class="highlight">${community.cateName } </span>카테고리
	  	</c:if>
	  	
	  	<hr id="boldBlue">
		<input type="hidden" name="boardIdx" value="${community.boardIdx }">
		
		<table class="table table-borderless">			
			<tr>
				<td width="10%">[  ${community.boardIdx }  ] </td>
				<td width="70%"> ${community.title }</td>
				<td width="20%" style="text-align: right;"><i class="bi bi-eye-fill"> </i> ${community.hit }</td>
			</tr>
			<tr style="background-color: #ECECEC;">
				<td colspan="2"><i class="bi bi-person-fill"> </i> ${community.nickname }</td>		
				<td style="text-align: right;">${community.regDate }</td>
			</tr>
			<tr>
				<td colspan="3" style="height: 300px;">
					<div class = "communityUploadImg">
						<c:if test="${not empty community.boardFile}">
		    				<img src="${pageContext.request.contextPath }/resources/img_product/${community.boardFile}" alt="${community.boardOri }">
						</c:if>
					</div><br>
					${community.content }
				</td>
			</tr> 
		</table>
		
		<hr>
		
		<div id="buttonContainer">
			<div class="left-align">
				<c:if test="${cateIdx != null}">
					<a class="btn btn-primary" href="get_community_list_by_cate?&page=${page }&cateIdx=${cateIdx }" id="getListBtn" role="button">
						<i class="bi bi-list"> </i>목록
					</a>
				</c:if>
				<c:if test="${cateIdx == null}">
					<a class="btn btn-primary" href="get_community_list_by_cate?page=${page }" id="getListBtn" role="button">
						<i class="bi bi-list"> </i>목록
					</a>
				</c:if>	
			</div>
			
			<div class="right-align">	
				<c:if test="${loginMember != null && loginMember.memberIdx == community.memberIdx}">
					<c:if test="${cateIdx != null}">
						<a class="btn btn-primary rightButton" href="update_community_page?boardIdx=${community.boardIdx }&page=${page }&cateIdx=${cateIdx }" id="editListBtn" role="button">
							<i class="bi bi-pencil-square"> </i>수정
						</a>
					</c:if>
					<c:if test="${cateIdx == null}">
						<a class="btn btn-primary rightButton" href="update_community_page?boardIdx=${community.boardIdx }&page=${page }" id="editListBtn" role="button">
							<i class="bi bi-pencil-square"> </i>수정
						</a>
					</c:if>
					
					<c:if test="${cateIdx != null}">
						<a class="btn btn-primary rightButton" href="delete_community?boardIdx=${community.boardIdx }&page=${page }&cateIdx=${cateIdx }" id="deleteBtn" role="button" onclick="return confirm('정말 삭제하시겠습니까?')">
							<i class="bi bi-trash-fill"> </i>삭제
						</a>
					</c:if>
					<c:if test="${cateIdx == null}">
						<a class="btn btn-primary rightButton" href="delete_community?boardIdx=${community.boardIdx }&page=${page }" id="deleteBtn" role="button" onclick="return confirm('정말 삭제하시겠습니까?')">
							<i class="bi bi-trash-fill"> </i>삭제
						</a>
					</c:if>
				</c:if>
			</div>
		</div>
				
        <br><br>
        <hr id="boldBlue">
        
        <c:forEach var="comment" items="${commentList}">
	        <div>
	        	<div class="commentContainer">
					${comment.nickname }&nbsp;&nbsp;[${comment.commDate }]
					<div class="commentBtn">
					<c:if test="${loginMember != null && loginMember.memberIdx == comment.memberIdx}">
		                <button type="button" class="btn btn-link btn-xs" id="deleteBtn_${comment.commIdx}" onclick="deleteCommContent(${comment.commIdx })">삭제</button>
		                <button type="button" class="btn btn-link btn-xs" id="updateBtn_${comment.commIdx}" onclick="updateCommContent(${comment.commIdx })">수정</button>
		            </c:if>
		            </div>
	            </div>
	            
				<div>
					<div id="oriCommContent_${comment.commIdx}" style="display:block;">${comment.commContent }</div>
		            <div class="form-group" id="updateCommContent_${comment.commIdx}" style="display:none;">
  						<textarea class="form-control" id="textareaCommContent_${comment.commIdx}" rows="3">${comment.commContent }</textarea>
					</div>
		            <hr>
				</div>
			</div>
		</c:forEach>
			
		<%-- <c:if test="${loginMember != null && loginMember.memberIdx == community.memberIdx}"> --%>
		<c:if test="${loginMember != null}">
	    <form id="commentForm" action="insert_comment" method="post">
	        <input type="hidden" name="boardIdx" value="${community.boardIdx }">
	        <input type="hidden" name="page" value="${page }">
	        
	        	<div class="form-group">
  					<!-- <label for="comment">Comment:</label>  -->
  					<textarea class="form-control" rows="5" name="commContent" id="commContent" required></textarea>
				</div>
	            <div style="text-align: right;"> 
	            	<button type="submit" class="btn btn-primary" id="insertCommentBtn" role="button">댓글 쓰기</button>   
	            </div>         
	        
	    </form>
		</c:if>
	</div>
<br><br><br><br><br><br><br><br><br><br><br><br>

<script>
function updateCommContent(commIdx) {
    console.log("updateBtn이 클릭되었습니다");

    var updateBtn = document.getElementById("updateBtn_" + commIdx);
    var deleteBtn = document.getElementById("deleteBtn_" + commIdx);

    if (updateBtn.innerText === "수정") {
        updateBtn.innerText = "완료";
        deleteBtn.innerText = "취소";

        var oriCommContent = $('#oriCommContent_' + commIdx);
        var updateCommContent = $('#updateCommContent_' + commIdx);
        var textareaCommContent = $('#textareaCommContent_' + commIdx);

        oriCommContent.hide();
        updateCommContent.show();

        textareaCommContent.val(oriCommContent.text());
        
    } else if (updateBtn.innerText === "완료") {
        var oriCommContent = $('#oriCommContent_' + commIdx);
        var updateCommContent = $('#updateCommContent_' + commIdx);
        var textareaCommContent = $('#textareaCommContent_' + commIdx);

        var commContent = textareaCommContent.val();

        $.ajax({
            type: "POST",
            url: "update_comment",
            data: {
                "commIdx": commIdx,
                "commContent": commContent
            },
            success: function (result) {
                console.log("서버 응답 : " + result);

                oriCommContent.text(commContent);
                
                updateCommContent.hide();

                oriCommContent.show();
            },
            error: function(xhr, status, error) {
            	console.log("AJAX 오류" + error);
            }
        });
        updateBtn.innerText = "수정";
        deleteBtn.innerText = "삭제";
    }
}
function deleteCommContent(commIdx) {
	console.log("deleteBtn이 클릭되었습니다");
	
	var updateBtn = document.getElementById("updateBtn_" + commIdx);
    var deleteBtn = document.getElementById("deleteBtn_" + commIdx);
    
    if (deleteBtn.innerText === "삭제") {

    	let answer = confirm("해당 댓글을 정말 삭제하시겠습니까?");
    	
    	if(answer) {
    		
	    	$.ajax({
	             type: "POST",
	             url: "delete_comment",
	             data: {
	                 "commIdx": commIdx
	             },
	             success: function (result) {
	                 console.log("서버 응답 : " + result);
	                 
	                 $('#oriCommContent_' + commIdx).parent().parent().remove();
	             },
	             error: function(xhr, status, error) {
	            	 console.log("AJAX 오류" + error);
	             }
	        });
    	 
    	} else {
    		
    		console.log("삭제가 취소되었습니다.");
    	}	
        
    } else if (deleteBtn.innerText === "취소") {
    	
    	var oriCommContent = $('#oriCommContent_' + commIdx);
        var updateCommContent = $('#updateCommContent_' + commIdx);
        var textareaCommContent = $('#textareaCommContent_' + commIdx);
    	
        updateCommContent.hide();
        oriCommContent.show();

        updateBtn.innerText = "수정";
        deleteBtn.innerText = "삭제";
        
    }
	
}

</script>

</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>