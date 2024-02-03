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
<title>비밀번호 확인</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"> 
<style>

	h2 {
		text-align: center;
		margin: 30px;
	}
	
	hr {
		border: 1px solid grey;
		width: 700px;	
	}

	h3 {
		text-align: center;
		margin-top: 50px;
	}
	
	p {
		text-align: center;
	}
	
	#check {
		font-size: 13px;
		color: red;
		margin-top: 20px;
	}
	
    .form-group {
        width: 400px;
        margin: 0 auto;
    }
    
	#thisbtn {
		 margin-top: 20px;
		 margin-left: 170px;
	}
</style>
</head>
<body>
<!-- 회원정보 수정 전에 비밀번호 확인을 다시 한번 받는 페이지 입니다~! -->
	<h2>회원정보 수정</h2>
	<hr>

	<div id="passwordInsert">
		<form action="password_check" method="post">
			<h3>비밀번호 재확인</h3>
			<p>정보를 안전하게 보호하기 위해 비밀번호를 확인이 필요합니다.</p>			
			<div class="form-group">
			  <input type="password" id="pwd" name="pwd" class="form-control" placeholder="비밀번호를 입력하세요">
			  <p id="check"></p>
			  <button type="button" onclick="checkPwd()" class="btn btn-primary" id="thisbtn">입력</button>
			</div>			
		</form>	
	</div>

<script>	
	function checkPwd() {
		let pwd = document.getElementById("pwd").value;
		
		$.ajax({
			type: "POST",
			url: "password_check",
			data:{"pwd" : pwd},
			success: function(result){
				if(result === false) {
					check.innerHTML = "회원 정보가 일치하지 않습니다. 다시 확인해주세요.";
					return false;
				} else {
					window.location.href="go_update_info";
					
				}
			}
		});
	}
</script>	
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>	
</body>
</html>