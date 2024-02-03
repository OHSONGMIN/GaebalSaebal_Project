<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ include file ="/WEB-INF/jsp/include/header_bf.jspf" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"> 
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
	h2 {
		text-align: center;
		margin: 30px;
	}
	
	hr {
		border: 1px solid grey;
		width: 700px;	
		margin-bottom: 50px;
	}

	.idform {
		width: 400px;
		margin: 0 auto;
	}
	
	.modal {
		text-align: center;
	}
	
	h4 {
		font-size: 25px;
	}
	
	.close {
		font-size: 20px;
	}
	
	.btn1 {
	     background-color: #98c1d9;
		 color: white;
		 border: none;
		 height: 40px;
		 width: 120px;
		 border-radius: 5px;
		 font-size: 15px;
		 margin-left: 70px;
		 margin-top: 20px;
	}
	
	.btn2 {
	    color: #98c1d9;
	    background-color: white;
	    border-color: #98c1d9;
	    height: 40px;
		width: 120px;
		border-radius: 5px;
		font-size: 15px;
		margin-left: 20px;
		margin-top: 20px;
	}
	
	
</style>

</head>
<body>
	<h2>아이디 찾기</h2>
	<hr>
	<form action="search_id" method="post">
	<div class="idform">
		<div class="mb-3">
		    <label for="name" class="form-label">이름</label>
		    <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요">
		</div>
	  	<div class="mb-3">
		    <label for="email" class="form-label">이메일</label>
		    <input type="email" class="form-control" id="email" name="email"  placeholder="이메일을 입력하세요">
		 </div>  
		 <div class="mb-3">
		 	<button type="button" class="btn1" data-toggle="modal" data-target="#myModal" onclick="searchIdView()">
			   	아이디찾기
			</button>
			<button type="button" class="btn2" onclick="goSearchPwd()">비밀번호찾기</button>
		 </div>
	</div>
	</form>	
	 <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">아이디 찾기 결과입니다.</h4>
                    <button type="button" class="close" data-dismiss="modal">확인</button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <p id="modalResult"></p>
                </div>
            </div>
        </div>
    </div>
    
    <script>
		function searchIdView() {
		let name = document.getElementById("name").value;
		let email = document.getElementById("email").value;
		
		$.ajax({
			type: "POST",
			url : "search_id",
			data: { "name": name, "email": email },
			success : function(searchId){
				 if (searchId != null && searchId.trim() !== "") {
		                // ID를 찾은 경우
					 	$("#modalResult").html("회원님의 아이디는 <br><strong style='font-size: 25px;'>" + searchId + "</strong><br/>입니다.");
		                $("#myModal").modal("show");
		            } else {
		                // ID를 찾지 못한 경우
		            	$("#modalResult").html("아이디를 찾을 수 없습니다");
		            	$("#myModal").modal("show");
		            }
		        }			
			})
		}		
</script>
<script>
	function goSearchPwd(){
		location.href="go_search_pwd";
	}

</script>

<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>	
</body>
</html>