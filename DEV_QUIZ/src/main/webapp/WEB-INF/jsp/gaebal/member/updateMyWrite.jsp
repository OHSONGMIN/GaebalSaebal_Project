<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resources/css/style.css">
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
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
   src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.js"></script>
<style>
#container {
   margin: auto;
}

h1, h3, p {
   text-align: center;
}

h2 {
   text-align: center;
   margin: 30px;
}

hr {
   border: 1px solid grey;
   width: 50%;
   margin-bottom: 30px;
}

table {
   width: 1000px;
   border-collapse: collapse;
}

.center {
   text-align: center;
}

.border-none, .border-none td {
   border: none;
}

.left-community {
   width: 200px;
   float: left;
   position: relative;
   margin: 0 auto;
}

.right-community {
   width: 1200px;
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

body {
   margin: 10px
}

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
   clip: rect(0, 0, 0, 0);
   border: 0;
}

.filebox label {
   display: inline-block;
   padding: .5em 1.75em;
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
}

.filebox.bs3-primary label {
   color: #fff;
   background-color: #3D5A80;
   border-color: #3D5A80;
}

#insertButton {
   background-color: #3D5A80;
   border: none;
}

#insertButton:hover {
   background-color: #587CB2;
   border: none;
}

#cancelButton {
   background-color: #EE634D;
   border: none;
}

#cancelButton:hover {
   background-color: #E73116;
   border: none;
}

#resetButton {
   background-color: #3D5A80;
   border: none;
}

#resetButton:hover {
   background-color: #587CB2;
   border: none;
}

.btnclass {
   margin: 20px 500px;
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

   <h2>내 글 수정하기</h2>
   <hr>


   <div id="container">
      <div class="right-community">
            <form action="update_my_write" method="post"
               enctype="multipart/form-data" onsubmit="prepareForm()">
               <div class="insertBackground">
               <input type="hidden" name="boardIdx" value="${community.boardIdx }">

               <div class="form-row">
                  <div class="col-md-2 mb-3">
                     <label for="cateIdx" class="mr-sm-2"></label> <select
                        class="form-control mr-sm-2" name="cateIdx">
                        <c:forEach var="cate" items="${communityCate }">
                           <option value="${cate.cateIdx }">${cate.cateName }</option>
                        </c:forEach>
                     </select>
                  </div>
                  <div class="col-md-10 mb-3">
                     <label for="title" class="mr-sm-2"></label> <input type="text"
                        name="title" class="form-control mr-sm-2"
                        value="${community.title }" required>
                  </div>
               </div>

               <div class="form-row">
                  <div class="col-md-12 mb-3">
                     <textarea class="form-control" rows="15" name="content"
                        id="content" required>${community.content }</textarea>
                  </div>
               </div>

               <div class="filebox bs3-primary">

                  <label for="uploadFile">업로드</label> <input type="file"
                     id="uploadFile" class="upload-hidden" accept="image/*"
                     name="uploadFile"> <input class="upload-name"
                     value="파일선택" disabled="disabled">
               </div>
               </div>
            
         <div class="btnclass">
            <span>
               <button type="submit" id="insertButton" class="btn btn-primary"
                  role="button">
                  <i class="bi bi-pencil-square"> </i>수정
               </button>
               <button type="button" id="cancelButton" onclick="goBack()"
                  class="btn btn-primary" role="button">취소</button>
            </span>
         </div>
         </form>
         <!-- <p>
         <a href="get_community_list_by_cate?">게시글 목록 보기</a>
      </p> -->
      </div>
      <br> <br> <br> <br> <br>
   </div>
</body>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</html>