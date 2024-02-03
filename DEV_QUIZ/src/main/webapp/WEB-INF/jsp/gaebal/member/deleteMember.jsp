<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<title>회원탈퇴</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
h2 {
	text-align: center;
	margin: 30px;
}

h4 {
	text-align: center;
	margin-bottom: 20px;
}

hr {
	border: 1px solid grey;
	width: 700px;
	margin-bottom: 30px;
}

p {
	text-align: center;
}

#deletemsg {
	color: red;
	font-size: 13px;
	margin-top: 10px;
}

.form-group {
	width: 400px;
	margin: 0 auto;
}

#thisbtn {
	margin-top: 10px;
	margin-left: 170px;
}
</style>
</head>
<body>
	<h2>회원 탈퇴</h2>
	<hr>
	<!-- 탈퇴를 위해서 비밀번호 재입력 필요 -->
	<div id="passwordInsert">
		<form action="delete_member_soft" method="post">
			<h4>비밀번호 재입력</h4>
			<p>회원 탈퇴 확인을 위하여 비밀번호 재입력이 필요합니다</p>
			<div class="form-group">
				<input type="password" id="pwd" name="pwd" class="form-control"
					placeholder="비밀번호를 입력하세요">
				<p id="deletemsg"></p>
				<button type="button" class="btn btn-primary" id="thisbtn"
					onclick="check()">탈퇴</button>
			</div>
		</form>
	</div>
	<script>
	function check() {
		let pwd = document.getElementById("pwd").value;		
		let loginMember = {
		            pwd: "${loginMember.pwd}"
		 };

		let pwd2 = loginMember.pwd;
		$.ajax({
			type: "POST",
			url : "delete_member_soft",
			data: { "pwd": pwd },
			success: function(result) {
			    if (pwd !== pwd2) {
			        deletemsg.innerHTML = "입력한 정보가 일치하지 않습니다.";
			        return false;
			    } else {
			    	alert("회원 탈퇴 처리가 완료되었습니다. \r\n 이용해주셔서 감사합니다");
			    	location.href="go_main"
			    	return true;
			    }
			}
		})     
	}

</script>

	<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>
</body>
</html>