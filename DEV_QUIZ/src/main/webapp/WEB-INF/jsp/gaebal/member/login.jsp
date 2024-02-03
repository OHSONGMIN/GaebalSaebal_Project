<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/include/header_bf.jspf"%>

<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
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
	margin-left: 50px;
}
</style>
</head>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
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
		margin-left: 50px;
	}
</style>
</head>
<body>
	<h2>로그인</h2>
	<hr>

	<div class="loginform">
		<form action="login" method="post">
			<div class="form-group">
				<input type="text" class="form-control" id="id"
					placeholder="아이디를 입력하세요" name="id">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" id="pwd"
					placeholder="비밀번호를 입력하세요" name="pwd">
			</div>
			<div class="idpwd">
				<ul>
					<li class="list"><a href="go_search_id">아이디찾기</a></li>
					<li class="list">|</li>
					<li class="list"><a href="go_search_pwd">비밀번호찾기</a></li>
				</ul>
			</div>
			<div class="loginbtn">
				<button type="button" class="btn1" onclick="loginCheck()">로그인</button>
				<a href="go_insert_member">
					<button type="button" class="btn2">회원가입</button>
				</a>
			</div>
		</form>
	</div>
	<script>
	function loginCheck(){
		let id = document.getElementById("id").value;
		let pwd = document.getElementById("pwd").value;
		
		$.ajax({
			type: "POST",
			url: "login",
			data: {"id": id, "pwd": pwd},
			success: function(data){
				if(data === '') {
					alert("로그인 정보를 찾을 수 없습니다");
					
				} else {
					alert("로그인성공");
					//location.href="go_main";	
					location.href="main";	
				}						
			},
			error : function(){
				console.log("데이터 x");
			}
			})		
		}	  
  </script>
	<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>