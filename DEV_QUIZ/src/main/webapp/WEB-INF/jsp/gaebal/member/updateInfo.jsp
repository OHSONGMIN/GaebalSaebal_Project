<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

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
<title>회원정보 수정</title>
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
	}
	
	.mb-3 {
		margin-left: 100px;
	}
	
	.helpmsg {
		text-align: right;
		margin-right: 50px;
	}	
	
	.updateform {
	   width: 750px;
	   margin: 80px auto;
	}
	
	#helpmsg {
		text-align: right;
		margin-right: 70px;
		margin-bottom: 10px
	}
	
	.updatebtn1 {
	     background-color: #98c1d9;
		 color: white;
		 border: none;
		 width: 150px;
		 height: 35px;
		 border-radius: 5px;
		 text-align: center;
		 margin-left: 250px;
		 margin-top: 30px;
	}
	
	.updatebtn2 {
	     background-color: white;
		 color: #98c1d9;
		 border: 1px solid #98c1d9;
		 width: 150px;
		 height: 35px;
		 border-radius: 5px;
		 text-align: center;
		 margin-left: 50px;
		 margin-top: 30px;
	}
	
	
	#id, #pwd, #pwdConfirm, #name, #nickname, #email, #phone {
		width: 400px;
	}
	
	#id {
		margin-left: 100px;
	}
	
	#pwd {
		margin-left: 85px;
	}
	
	#pwdConfirm {
		margin-left: 55px;
	}
	
	#name {
		margin-left: 120px;
	}
	
	#nickname {
		margin-left: 105px
	}
	
	#email, #phone {
		margin-left: 75px;
	}
	
	.form-group {
	    display: flex;
	    align-items: center;
	    margin-bottom: 20px;
	}	

</style>

</head>
<body>
	<h2>회원정보수정</h2>
	<hr>
	<div class="updateform">
	<form action="update_member" method="post" enctype="multipart/form-data" onsubmit="return checkInfo()">
		  <div class="mb-3">
		  <div class="form-group">
		    <label for="id" class="form-label">아이디</label>
		    <input type="text" class="form-control" id="id" name="id" value="${loginMember.id}" readonly>
		   </div>  
		  </div>
		  <div class="mb-3">
		  <div class="form-group">
		    <label for="pwd" class="form-label">비밀번호</label>
		    <input type="password" class="form-control" id="pwd" name="pwd" onkeyup="checkPassword()">
		  </div>  
		  </div>
		  <div class="mb-3">
		  <div class="form-group">
		    <label for="pwdConfirm" class="form-label">비밀번호확인</label>
		    <input type="password" class="form-control" id="pwdConfirm" name="pwdConfirm" onkeyup="checkPassword()">
		  </div>
		  </div>
		  <p id="passwordMsg" class="helpmsg" style="color: red;"></p>  
		  <script>
				function checkPassword(){
				    let pwd = document.getElementById("pwd").value;
				    let pwdConfirm = document.getElementById("pwdConfirm").value; 
				    let passwordMsg = document.getElementById("passwordMsg");    
				
				    if (pwd !== pwdConfirm) {
				        passwordMsg.innerText = "비밀번호가 일치하지 않습니다.";
				    } else {
				        passwordMsg.innerText = "";
				    }
				}
		</script>		  
		  <div class="mb-3">
		  <div class="form-group">
		    <label for="nickname" class="form-label">닉네임</label>
		    <input type="text" class="form-control" id="nickname" name="nickname" value="${loginMember.nickname }">
		  </div>
		  </div>
		  <div class="mb-3">
		  <div class="form-group">
		    <label for="email" class="form-label">이메일주소</label>
		    <input type="text" id="email" class="form-control" value=${loginMember.email }>
		   </div>
		  </div>
		  <div class="mb-3">
		  <div class="form-group">
		    <label for="phone" class="form-label">핸드폰번호</label>
		    <input type="text" class="form-control" id="phone" name="phone" value="${loginMember.phone }">
		  </div>
		  	<div id="helpmsg" class="form-text">'-' 포함 없이 입력해주세요</div>
		  </div>	  
		  	<div id="submitbtn">
		  	<div class="form-group">
		  		<button type="submit" class="updatebtn1">수정</button>
		  		<button type="reset" class="updatebtn2">초기화</button>
		  	</div>
		  	</div>
		 </form>
		 </div>
		 
<script>

	function checkInfo() {
		let pwd = document.getElementById("pwd").value;
		let nickname = document.getElementById("nickname").value;
		let email = document.getElementById("email").value;
		let phone = document.getElementById("phone").value;
		
		if(pwd === "") {
			alert("비밀번호를 입력해주세요");
			return false;
		} 
		
		if(nickname === "") {
			alert("닉네임을 입력해주세요");
			return false;
		}
		
		if(email === "") {
			alert("이메일을 입력해주세요");
			return false;
		}
		
		if(phone === "") {
			alert("핸드폰번호를 입력해주세요");
			return false;
		}
		
		alert("수정이 완료되었습니다");
		return true;
	}	
</script>		 
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>	
</body>
</html>