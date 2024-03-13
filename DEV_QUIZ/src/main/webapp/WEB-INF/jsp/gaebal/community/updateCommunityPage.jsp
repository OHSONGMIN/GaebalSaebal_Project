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
<title>커뮤니티 게시글 수정 : 오송민</title>
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
	
	table, th, td {
		border: 1px solid black;
		margin: 0 auto;
	}
	
	.form-control-file border {
		width: 300px;
	}
	
	nav {
    	display: flex;
    	justify-content: center;
  	}
  	
  	.insertBackground {
		background-color: #F5F5F5;
		padding: 20px 50px;	
	}
	
	#boldBlue {
    	border: 1px solid #3D5A80; 
    	margin: 10px 0; 
    }
    
    /* body {margin: 10px} */
    
	.where {
		  display: block;
		  margin: 25px 15px;
		  font-size: 11px;
		  color: #000;
		  text-decoration: none;
		  font-family: verdana;
		  font-style: italic;
	} 
	
	.filebox input[type="file"] {
		  position: absolute;
		  width: 1px;
		  height: 1px;
		  padding: 0;
		  margin: -1px;
		  overflow: hidden;
		  clip:rect(0,0,0,0);
		  border: 0;
	}
	
	.filebox label {
		  display: inline-block;
		  padding: .5em  1.75em;
		  color: #999;
		  font-size: inherit;
		  line-height: normal;
		  vertical-align: middle;
		  background-color: #fdfdfd;
		  cursor: pointer;
		  border: 1px solid #ebebeb;
		  border-bottom-color: #e2e2e2;
		  border-radius: .25em;
	}
	
	/* named upload */
	.filebox .upload-name {
		  display: inline-block;
		  padding: .5em .75em; 
		  font-size: inherit;
		  font-family: inherit;
		  line-height: normal;
		  vertical-align: middle;
		  background-color: #FFFFFF;
		  border: 1px solid #CED4DA;
		  /*border-bottom-color: #e2e2e2;*/
		  border-radius: .25em;
		  -webkit-appearance: none;
		  -moz-appearance: none;
		  appearance: none;
		  margin-bottom: 5;
	}
	
	.filebox.bs3-primary label {
	  	color: #fff;
    	background-color: #3D5A80;
    	border-color: #3D5A80;
    	margin-right: 10;
    	margin-left: 0;
    	margin-top: 3;
	}
	
	#cancelButton {
    	background-color: #EE634D;
        border: none;
    }
    
    #cancelButton:hover {
    	background-color: #E73116;
        border: none;
    }
    
    #deleteImgButton {
    	background-color: #9E9E9E;
        border: none;
    }
    
    #deleteImgButton:hover {
    	background-color: #7A7A7A;
        border: none;
    }
    
    /* .filebox {
    	display: flex;
        justify-content: space-between;
        align-items: center;
    } */
	
	#cancelButton {
		background-color: #EE634D;
        border: none;
        width: 495px;
	}
	
	#cancelButton:hover {
		background-color: #E73116;
        border: none;
	}
	
	#updateButton {
    	background-color: #3D5A80;
        border: none;
        margin-left: 0;
		margin-rignt: 0;
		width: 495px;
    }
    
    #updateButton:hover {
    	background-color: #587CB2;
        border: none;
    }
    
    .sidenav {
      	background-color: #F5F5F5;
      	width: 250px;
      	text-align: center;
      	height: 1000px;
    }
    
</style>
</head>

<script>
	//폼 제출 전에 줄 바꿈 문자를 "<br>"로 교체
	function prepareForm() {
	    var contentTextarea = document.getElementById("content");
	    contentTextarea.value = contentTextarea.value.replace(/\n/g, "<br>");
	}
	
    //이전 페이지로 돌아가는 함수
    function goBack() {
        window.history.back();
    }
    
    //이미지 삭제하는 함수
    function deleteImg() {
    	let boardIdx = "${community.boardIdx}";
    	console.log(boardIdx);
    	
    	//let page = "${page}" //수정
    	//console.log(page); //수정
    	
    	// 여기에서 "${community.boardOri}" 를 null 값으로 바꾸도록   	
    	
    	$.ajax({
            type: "POST",
            url: "delete_community_img",
            data: {
                "boardIdx": boardIdx
                //"page": page //수정
            },
            success: function (result) {
                console.log("서버 응답 : " + result);
				
                $("#deleteImgButton").hide();
                $("#uploadFileInfo").text("현재 업로드 파일 : 없음  ");
                
                //${community.boardOri} 이 값을 null로 set하는 기능
                
                
            },
            error: function(xhr, status, error) {
            	console.log("AJAX 오류" + error);
            }
        });
    	
    }
    
    //업로드 변경하면 변경된 파일 이름 출력
    $(document).ready(function(){
   	  	var fileTarget = $('.filebox .upload-hidden');

   	    fileTarget.on('change', function(){
   	        if(window.FileReader){
   	            var filename = $(this)[0].files[0].name;
   	        } else {
   	            var filename = $(this).val().split('/').pop().split('\\').pop();
   	        }

   	        $(this).siblings('.upload-name').val(filename);
   	    });
   	}); 
  
</script>


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
		<hr id= "boldBlue">

		<form action="update_community" method="post" 
				enctype="multipart/form-data" onsubmit="prepareForm()">
			<input type="hidden" name="boardIdx" value="${community.boardIdx }">
			
			<!--수정 후엔 그냥 전체 카테고리의 1페이지로 가도록 하자--> <!--
			<input type="hidden" name="page" value="${page }">
			<c:if test="${cateIdx != null}">
				<input type="hidden" name="cateIdx" value="${cateIdx }">
			</c:if> -->
			
			<div class="insertBackground">
				<div class="form-row">
	    			<div class="col-md-2 mb-3">
	    				<label for="cateIdx" class="mr-sm-2"></label>
	    				<select class="form-control mr-sm-2" name="cateIdx">
						    <c:forEach var="cate" items="${communityCate}">
						        <c:choose>
						            <c:when test="${community.cateIdx eq cate.cateIdx}">
						                <option value="${community.cateIdx }" selected>${community.cateName }</option>
						            </c:when>
						            <c:otherwise>
						                <option value="${cate.cateIdx}">${cate.cateName}</option>
						            </c:otherwise>
						        </c:choose>
						    </c:forEach>
				  		</select>
	    			</div>
	    			<div class="col-md-10 mb-3">
			  			<label for="title" class="mr-sm-2"></label>
			  			<input type="text" name="title" value="${community.title }" class="form-control mr-sm-2" placeholder="제목을 입력하세요" required>
			  		</div>
	    		</div>
	    		
	    		<div class="form-row">
		  			<div class="col-md-12 mb-3">
		  				<textarea class="form-control" rows="15" name="content" id="content" placeholder="내용을 입력하세요" required>${community.content }</textarea>
		  			</div>
		  		</div>
		  		
		  		<div class="filebox bs3-primary">
		  			<div>
			  			<c:set var="imageDeleted" value="${empty community.boardOri}" />
			  			
			  			<label for="uploadFile"><i class="bi bi-file-earmark-plus"> </i>업로드 변경</label>
						<input type="file" id="uploadFile" class="upload-hidden" accept="image/*" name="uploadFile">
						
						<input class="upload-name" value="파일 선택" disabled="disabled">
					</div>
					
					<div id="uploadFileInfo">
						
							<c:if test="${imageDeleted }"><span style="color: #3D5A80;">현재 업로드 파일 : 없음</span>
							</c:if>
							<c:if test="${not imageDeleted }"><span style="color: #3D5A80;">현재 업로드 파일 : ${community.boardOri}</span>
								<button type="button" id="deleteImgButton" onclick="deleteImg()" class="btn btn-primary btn-sm" role="button">파일 삭제</button>
							</c:if>
					</div>
				</div>
			</div>
			<hr id= "boldBlue">
			
			<div style="display: flex; justify-content: space-between;">
				<button type="submit" id="updateButton" class="btn btn-primary btn-lg" role="button"><i class="bi bi-pencil-square"> </i>수정</button>
				<c:if test="${cateIdx != null}">
					<button type="button" id="cancelButton" onclick="location.href='get_community?boardIdx=${community.boardIdx}&page=${page }&cateIdx=${cateIdx }'" class="btn btn-primary btn-lg"><i class="bi bi-x-square"> </i>취소</button>
				</c:if>
				<c:if test="${cateIdx == null}">
					<button type="button" id="cancelButton" onclick="location.href='get_community?boardIdx=${community.boardIdx}&page=${page }'" class="btn btn-primary btn-lg"><i class="bi bi-x-square"> </i>취소</button>
				</c:if>
			</div>
		</form>	
	</div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>