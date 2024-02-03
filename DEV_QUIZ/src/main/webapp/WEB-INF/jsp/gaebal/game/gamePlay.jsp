<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>개발새발</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
	  integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/gaebal/gamePlay.css">

<script>
    let gameData; // 전역 변수로 설정
	var point = 0; // 전역 변수로 설정
	var score = 0; // 전역 변수로 설정
	var memberIdx = "${memberIdx}";
	
	$(document).ready(function () {
		readyToGame();
	    gaebalGamePlay();
	       
		var clickCount = 0;
		
	    $(".exam").on("click", "button", function () {
			clickCount++;
	       	
	    	const clickBtn = $(this);
	        const clickAnswer = clickBtn.text();

            // 색상 변경을 위한 클래스 추가 및 제거
            $(".exam button").removeClass("clicked");
            clickBtn.addClass("clicked");

	
			if (clickAnswer === gameData.quizAnswer) {
				point = gameData.savedPoint;
	            score = gameData.savedScore;
	            gaebalSavePoint(point, score);
	            clickCount = 0;
	            gaebalGamePlay();
			} else if (clickAnswer) {
				
	        }
			
	        if (clickCount >= 2) {
	        	gaebalGamePlay();
	           	clickCount = 0;
	        }
		});
	});
		
	//보기 5개 랜덤으로 섞는 함수
	function shuffleArray(array) {
		for (let i = array.length - 1; i > 0; i--) {
			const j = Math.floor(Math.random() * (i + 1));
		    [array[i], array[j]] = [array[j], array[i]];
		}
	    return array;
	}
	
	function gaebalGamePlay() {
	    let cateIdx = $("#nextBtn").val();
	
		$.ajax({
	        url: `gaebal_game_play?cateIdx=${cateIdx}`,
	        type: "GET",
	        success: function (game) {
	            let dispTag = " ";
	            let examTag = " ";
	            
	        	if (game.length === 0) {
	        		alert("게임을 준비하고 있습니다.");
	        		return goGame();
	        		
	        	} else {
		            dispTag += "<br><h3>" + game[0].quiz + "</h3>";
		            dispTag += "<p>적립 포인트 : " + game[0].savedPoint + "</p>";
		            dispTag += "<h4 id='quizAnswer'>정답을 선택해주세요.</h4></div><br>";
		            
		            $(".quiz").html(dispTag);
		
		            const exampleNumbers = [game[0].exampleNo1, game[0].exampleNo2, game[0].exampleNo3, game[0].exampleNo4, game[0].quizAnswer];
		            const ranNums = shuffleArray(exampleNumbers);
		
		            for (let i = 0; i < ranNums.length; i++) {
		                //examTag += "<div id='quizExam' class=example" + i + ">";
		                examTag += "<button class='btn btn-secondary btn-lg btn-block' id=exam" + i + ">" + ranNums[i] + "</button>";
		                //examTag += "</div>";
		            }
		
		            $(".exam").html(examTag);
		
		            // 전역 변수에 저장
		            gameData = game[0];
	        	}
	        },
	        error: function (error) {
	            alert("실패~~~");
	            console.error("Ajax 처리 실패: ", error);
	        }
	    });
	}
	
	function gaebalSavePoint(point, score) {
		let vo = point;
		let svo = score;
	
	    $.ajax({
			url: "save_point",
	    	type: "post",
	    	data: { point : vo, score : svo },
	    	success: function (data) {
	    		if (memberIdx === "0") {
	    			alert("정답입니다.");
	    		} else {
		    		alert(vo + "포인트 적립!!");
	    		}
	    	},
	    	error: function () {
	    		alert("실패~~~포인트적립실패~~~~~ㅠㅠㅠㅠ");
	    	}
		});
	}
	
	function checkMember() {
		var confirmPop = confirm("비회원 게임 실행시 포인트가 적립되지 않습니다.");
		
	    if (confirmPop == true) {
	    } else {
	    	location.href = "go_login";
	    }
	}
	
	function readyToGame() {
  		if (memberIdx === "0") {
		    checkMember();
		}  
	}
	function goGame() {
		location.href="go_game";
	}
</script>
</head>
<body>

	<div class="gameBody">
	    <div class="quiz">
	   
		</div>
		<hr>
		<div class="exam">
		
		</div>
	    	
	</div><br>
    
</body>
</html>
