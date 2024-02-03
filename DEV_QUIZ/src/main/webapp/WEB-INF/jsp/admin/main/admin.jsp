<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:if test="${empty admin}">
	    <%@ include file="/WEB-INF/jsp/include/header_admin.jspf" %>
	</c:if>
	<c:if test="${not empty admin}">
	    <%@ include file="/WEB-INF/jsp/include/header_admin_af.jspf" %>
	</c:if>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
		margin-bottom: 30px;
	}
	.loginform {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    justify-content: center;
	}
	#id, #pwd {
		width: 400px;
		
	}
	.idpwd {
		margin-left: 220px;
	}
	.list {
		list-style-type: none;
		display: inline-block;
		font-size: 12px;
	}
	.btn1 {
	     background-color: #98c1d9;
		 color: white;
		 border: none;
		 height: 45px;
		 width: 150px;
		 border-radius: 5px;
		 font-size: 20px;
		 text-align: center;
	}
	.btn2 {
	    color: #98c1d9;
	    background-color: white;
	    border-color: #98c1d9;
	    height: 45px;
		width: 150px;
		border-radius: 5px;
		font-size: 20px;
		text-align: center;
	}
	.loginbtn {
		margin-left: 50px;
	}
</style>
</head>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"> 
<style>
	h2 {
		text-align: center;
		margin: 30px;
	}
	
	hr {
		border: 1px solid grey;
		width: 700px;	
		margin-bottom: 30px;
	}
	
	.loginform {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    justify-content: center;
	}
	
	#id, #pwd {
		width: 400px;
		
	}
	
	.idpwd {
		margin-left: 220px;
	}
	
	.list {
		list-style-type: none;
		display: inline-block;
		font-size: 12px;
	}
	
	.btn1 {
        background-color: #98c1d9;
        color: white;
        border: none;
        height: 45px;
        width: 150px;
        border-radius: 5px;
        font-size: 20px;
	}
	
	.btn2 {
	    color: #98c1d9;
	    background-color: white;
	    border-color: #98c1d9;
	    height: 45px;
		width: 150px;
		border-radius: 5px;
		font-size: 20px;
	}
	
	.loginbtn {
        display: flex;
        justify-content: center;
		margin-left: 50px;
	}
</style>
</head>
<body>
	<h2>관리자 로그인</h2>
	<hr>	
	<div class="loginform">
	<form action="adminLogin" method="post">	
		<div class="form-group">
	    	<input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요" name="id">
	    </div>
	  	<div class="form-group">
	    	<input type="password" class="form-control" id="pwd" placeholder="비밀번호를 입력하세요" name="pwd">
	  	</div>
	  	<div class="loginbtn">
		  	<button type="button" class="btn1" onclick="adminLoginCheck()" >로그인</button>
	 	</div>
	</form>
	</div>
<script>
	function adminLoginCheck() {
		let id = document.getElementById("id").value;
		let pwd = document.getElementById("pwd").value;
		
		$.ajax({
			type: "POST",
			url: "adminLogin",
			data: {"id": id, "pwd": pwd},
			success: function(data){
				if(data === '') {
					alert("관리자 정보를 찾을 수 없습니다");
					
				} else {
					alert("로그인성공");
					location.href="admin_main";	
				}						
			},
			error : function(){
				console.log("데이터 x");
			}
		})		
	}	
</script>	
<%@ include file="/WEB-INF/jsp/include/admin-footer.jspf"%>		
</body>
</html>