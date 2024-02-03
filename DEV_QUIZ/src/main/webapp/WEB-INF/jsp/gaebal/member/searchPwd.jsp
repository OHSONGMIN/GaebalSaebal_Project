<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ include file ="/WEB-INF/jsp/include/header_bf.jspf" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"> 
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
	
	.pwdform {
		width: 400px;
		margin: 0 auto;
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
	<h2>비밀번호 찾기</h2>
	<hr>
	
	<form action="search_mem_pwd" method="post">
	<div class="pwdform">
		<div class="mb-3">
		    <label for="id" class="form-label">아이디</label>
		    <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요">
		</div>
	  	<div class="mb-3">
		    <label for="email" class="form-label">이메일</label>
		    <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력하세요">
		 </div>  
		 <div class="mb-3">
		 	<button type="button" class="btn1" onclick="goSearchPwdSuccess()">
			   	비밀번호 찾기
			  </button>		

		 	<button type="button" class="btn2" onclick="goSearchId()">아이디찾기</button>
		 </div>
	</div>
	</form>	
 	<div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">비밀번호 찾기 결과입니다</h4>
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
	function goSearchId(){		
		window.location.href = "go_search_id";
	}
</script>	
<script>
	function goSearchPwdSuccess(){
		var id = document.getElementById("id").value;
		var email = document.getElementById("email").value;
		
		$.ajax({
			type: "POST",
			url : "search_mem_pwd",
			data : { "id": id, "email" : email},
			success : function(result){
				if(result === "success") {
					$("#modalResult").html("임시비밀번호를 입력하신 이메일 <br><strong style='font-size: 25px;'>" + email + "</strong><br/>로 발송하였습니다.");
	                $("#myModal").modal("show");
			} else {
				$("#modalResult").html("회원정보를 찾을 수 없습니다.");
            	$("#myModal").modal("show");
				}
			}, error:  function(xhr, status, error) {
				console.log(xhr);
				console.log(status);
				console.log(error);
				
			}
		})		
	}		
</script>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>	
</body>
</html>