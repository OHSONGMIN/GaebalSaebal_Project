    let gameData; // 전역 변수로 설정
	var point = 0; // 전역 변수로 설정
	var score = 0; // 전역 변수로 설정

	$(document).ready(function () {
        gaebalGamePlay();
        $("#nextBtn").on("click", gaebalGamePlay);
        $("#testBtn").on("click", function() {
        	gaebalSavePoint(gameData.savedPoint); // savePoint 함수 호출 시 gameData.savedPoint 전달
        });
        
        var clickCount = 0;

        $("#nextBtn").on("click", function () {
            if (clickCount < 2) {
                clickCount++;
                gaebalGamePlay();
            } else {
                $("#nextBtn").off("click");
                alert("문제 끗");
            }
        });

        $(".quiz").on("click", "button", function () {
            const clickBtn = $(this);
            const clickAnswer = clickBtn.text();

            if (clickAnswer === gameData.answer) {
                point = gameData.savedPoint;
                score = gameData.savedScore;
                console.log("정답! 획득 포인트: " + point);
                console.log("정답! 획득 점수: " + score);
                
                gaebalSavePoint(point, score);
                
                gaebalGamePlay();
            } else {
                console.log("오답!");
            }
        });
    });

    function shuffleArray(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
        return array;
    }

    function gaebalGamePlay() {
        let cateIdx = $("#nextBtn").val();
    
        fetch(`gaebal_game_play?cateIdx=${cateIdx}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(game => {
            let dispTag = " ";
            dispTag += "<h3>" + game[0].quiz + "</h3>";
            dispTag += "<p>적립 포인트 : " + game[0].savedPoint + "</p>";
            dispTag += "<h4 id='answer'>정답을 선택해주세요호잇.</h4>";
            
            const exampleNumbers = [game[0].exampleNo1, game[0].exampleNo2, game[0].exampleNo3, game[0].exampleNo4, game[0].answer];
            const ranNums = shuffleArray(exampleNumbers);
            
            for (let i = 0; i < ranNums.length; i++) {
                dispTag += "<div class=example" + i + ">";
                dispTag += "<button id=exam" + i + ">" + ranNums[i] + "</button>";
                dispTag += "</div>";
            }
    
            $(".quiz").html(dispTag);
    
            // 전역 변수에 저장
            gameData = game[0];
        })
        .catch(error => {
            alert("실패~~~");
            console.error("Fetch 처리 실패: ", error);
        });
    }

    function gaebalSavePoint(point, score) {
  		
  		let vo = point;
    	let svo = score;
    	console.log("이거다::" + vo);
    	console.log("요거다::" + svo);

    	 $.ajax({
    	        url: "save_point",
    	        type: "post",
    	        data: { point : vo, score : svo },
    	        success: function (data) {
    	            alert("성공~~~");
    	            console.log(data);
    	        },
    	        error: function () {
    	            alert("실패~~~");
    	        }
    	    });
    }