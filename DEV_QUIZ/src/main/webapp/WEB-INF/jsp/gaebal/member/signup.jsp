<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file ="/WEB-INF/jsp/include/header_bf.jspf" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
   
   .signupform {
      width: 750px;
      margin: 80px auto;
      margin-right: 530px;
   }
   
   #helpmsg {
      text-align: right;
      margin-right: 70px;
      margin-bottom: 10px
   }
   
   .helpmsg {
      text-align: right;
      margin-right: 80px;
   }
   
   .signbtn {
        background-color: #98c1d9;
       color: white;
       border: none;
       height: 35px;
       border-radius: 5px;
   }
   
   .signbtn2 {
        background-color: #98c1d9;
       color: white;
       border: none;
       height: 35px;
       width: 300px;
       font-size: 20px;
       border-radius: 5px;
       margin-left: 180px;
   }
   
   .signbtn2:hover {
        background-color: white;
        color: #98c1d9;
        border: 1px solid #98c1d9;
   }
   
   .form-group {
       display: flex;
       align-items: center;
       margin-bottom: 20px;
   }

   #id, #pwd, #pwdConfirm, #name, #nickname, #phone {
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
   
   #phone1 {
      margin-left: 75px;
   }
   
   #double {
      margin-left: 30px;
   }

   .custom-checkbox {
        display: flex;
        align-items: center;
        margin-right: 250px;
    }

    .custom-checkbox .form-check-input {
        margin-right: 10px;
    }
    
    .container {
       width: 300px;
    }
   
   .phone {
      width: 125px;
   }
   .modalbtn {
      margin-left: 150px;
      background-color: #98c1d9;
      color: white;
      border: none;
      height: 35px;
      border-radius: 5px;
   }
   
</style>

<script>
   function idCheck() {
      var id = document.getElementById("id").value;
      var checkmsg = document.getElementById("checkmsg");
      
      $.ajax({
         type: "POST",
         url: "id_check",
         data: { id: id },
         success : function(result) {
            if(result === 0) {
               checkmsg.innerHTML = "사용 가능한 아이디입니다.";
            } else {
               checkmsg.innerHTML = "존재하는 아이디입니다. 아이디를 다시 입력해주세요";
            }
            console.log(result);
         }
      })      
   }
         
</script>
<script>      
       
      function emailCheck() {
         var email_id = document.getElementById("email_id").value;
        var domain = document.getElementById("domain").value;
        var email = email_id + "@" + domain;
        document.getElementById("email").value = email;
        
        console.log(email);
      var checkmsg2 = document.getElementById("checkmsg2");
      
      
      $.ajax({
         type: "POST",
         url: "email_check",
         data: { "email": email },
         success : function(result) {
            if(result === 0) {
               checkmsg2.innerHTML = "사용 가능한 이메일입니다.";
            } else {
               checkmsg2.innerHTML = "존재하는 이메일입니다. 다시 입력해주세요";
            }
            console.log(result);
            console.log(email);
         }
      });      
   }
   
   
   
</script>
<script>
   function checkPassword(){
      let pwd = document.getElementById("pwd").value;
      let pwdConfirm = document.getElementById("pwdConfirm").value; 
      let passwordMsg = document.getElementById("passwordMsg");   
      
      let data = {
              password: pwd,
              confirmPassword: pwdConfirm
       };
         
      $.ajax({      
         type: "POST",
         data: data,
         success: function (result) {          
              if (pwd !== pwdConfirm) {
                  passwordMsg.innerHTML = "비밀번호가 일치하지 않습니다.";
              } else {
                  passwordMsg.innerHTML = "";
              }
       },
       error: function (error) {
           console.error("Ajax 요청 중 에러 발생:", error);
       }
      });
   }
</script>


<script>
   function checkForm() {      
        var id = document.getElementById("id").value;
        var pwd = document.getElementById("pwd").value;
        var name = document.getElementById("name").value;
        var nickname = document.getElementById("nickname").value;
        var email = document.getElementById("email").value;
        
      var phone1 = document.getElementById("phone1").value;
        var phone2 = document.getElementById("phone2").value;
        var phone3 = document.getElementById("phone3").value;
        var phone = phone1 + phone2 + phone3;
        document.getElementById("phone").value = phone;   
        
        
        var checkBox = document.getElementById("check");
      
      if (id === "") {
         alert("아이디를 입력하세요");
         return false;
      }
      
      if(pwd === "") {
         alert("비밀번호를 입력하세요");
         return false;
      }
      
      if(name === "") {
         alert("이름을 입력하세요");
         return false;
      }
      
      if(nickname === "") {
         alert("닉네임을 입력하세요");
         return false;
      }
      
      if(email === "") {
         alert("이메일을 입력하세요");
         return false;
      }
      
      if(phone === "" && phone.length !== 10) {
         alert("올바른 핸드폰 번호를 입력하세요");
         return false;
      }
      
      if (!checkBox.checked) {
           alert('약관에 동의해야 가입할 수 있습니다.');
           return false;
       }      
      
        let checkmsg = document.getElementById("checkmsg");
        if (checkmsg.innerHTML !== "사용 가능한 아이디입니다.") {
            alert("아이디 중복체크를 진행해주세요");
            return false;
        }
        
        let passwordMsg = document.getElementById("passwordMsg");
        if (passwordMsg.innerHTML === "비밀번호가 일치하지 않습니다.") {
            alert("비밀번호를  일치시켜주세요");
            return false;
        }
        
        let checkmsg2 = document.getElementById("checkmsg2");
        if (checkmsg2.innerHTML !== "사용 가능한 이메일입니다.") {
           alert("이메일 중복체크를 진행해주세요");
           return false;       
          }
        
        
      alert("**회원가입이 완료되었습니다** \r\n 로그인페이지로 이동합니다.");
      return true;
   }

</script>

</head>
<body>

<h2>회원가입</h2>
<hr>

 <div class="back">
 <div class="signupform">
   <form action="insert_member" method="post" enctype="multipart/form-data" onsubmit="return checkForm()"> 
        <div class="mb-3">
        <div class="form-group">
          <label for="id" class="form-label">아이디</label>
          <input type="text" class="form-control" id="id" name="id">
          <button type="button" class="signbtn" id="double" onclick="idCheck()">중복확인</button>
        </div>    
        </div>
        <p id="checkmsg" class="helpmsg"></p>
        <div class="mb-3">
        <div class="form-group">
          <label for="password1" class="form-label">비밀번호</label>
          <input type="password" class="form-control" id="pwd" name="pwd" onkeyup="checkPassword()">
         </div> 
        </div>
        <div class="mb-3">
        <div class="form-group">
          <label for="password2" class="form-label">비밀번호확인</label>
          <input type="password" class="form-control" id="pwdConfirm" name="pwdConfirm" onkeyup="checkPassword()">
        </div> 
        </div>
        <p id="passwordMsg" class="helpmsg" style="color: red;"></p>
        <div class="mb-3">
        <div class="form-group">
          <label for="name" class="form-label">이름</label>
          <input type="text" class="form-control" id="name" name="name"> 
        </div>  
        </div>
        <div class="mb-3">
        <div class="form-group">
          <label for="nickname" class="form-label">닉네임</label>
          <input type="text" class="form-control" id="nickname" name="nickname">
        </div>  
        </div>                
        <div class="mb-3">
        <div class="form-group">
          <label for="email" class="form-label">이메일주소</label>
          <input type="text" id="email_id" class="form-control" style="width:270px; margin-left:75px;">
          <span>&nbsp;&nbsp;@&nbsp;&nbsp;</span>      
          <select id="domain">
             <option value="naver.com">naver.com</option>
             <option value="hanmail.net">hanmail.net</option>
             <option value="gmail.com">gmail.com</option>
             <option value="nate.com">nate.com</option>
          </select>
          <input type="hidden" id="email" name="email">
        <button type="button" class="signbtn" id="double" onclick="emailCheck()">중복확인</button>        
        </div>  
        </div>
        <p id="checkmsg2" class="helpmsg"></p>
        <div class="mb-3">
        <div class="form-group">
          <label for="phone" class="form-label">핸드폰번호</label>
         <input type="text" id="phone1" class="form-control phone">
         <span class="dash">&nbsp;-&nbsp;</span>
         <input type="text" id="phone2" class="form-control phone">
         <span class="dash">&nbsp;-&nbsp;</span>
         <input type="text" id="phone3" class="form-control phone">   
         <input type="hidden" id="phone" name="phone">      
        </div>       
       
        </div>
        <div class="mb-3 form-check">
        <div class="form-group">
        <div class="container">
           <!-- Button to Open the Modal -->
           <button type="button" class="modalbtn" data-toggle="modal" data-target="#myModal">
               약관확인하기
           </button>         
           <!-- The Modal -->
           <div class="modal" id="myModal">
             <div class="modal-dialog">
               <div class="modal-content">               
                 <!-- Modal Header -->
                 <div class="modal-header">
                   <h4 class="modal-title">가입 약관</h4>
                   <button type="button" class="close" data-dismiss="modal">닫기</button>
                 </div>                  
                 <!-- Modal body -->
                 <div class="modal-body">
                      <%@ include file ="/WEB-INF/jsp/include/popup3.jsp" %>  
                 </div>                        
               </div>
             </div>
           </div>
           </div>              
          <div class="custom-checkbox form-check">
             <input type="checkbox" class="form-check-input" id="check">
             <label class="form-check-label" for="check" >약관에 동의합니다.</label>
         </div>          
        </div>
        <button type="submit" class="signbtn2">가입</button>
   </div>     
</form>
</div>

</div>
<%@ include file="/WEB-INF/jsp/include/include-footer.jspf"%>   
</body>
</html>