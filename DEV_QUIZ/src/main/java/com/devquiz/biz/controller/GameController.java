package com.devquiz.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.GameVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.ScoreVO;
import com.devquiz.biz.service.GameService;
import com.devquiz.biz.service.MainService;

@Controller
public class GameController {
	private GameService gameService;
	private MainService mainService;

	public GameController() {
		System.out.println("@# @# @# GameController 실행! @# @# @#");
	}

	@Autowired
	public GameController(GameService gameService, MainService mainService) {
		System.out.println("@# @# @# GameController(gameService)! @# @# @#");
		this.gameService = gameService;
		this.mainService = mainService; // 카테고리 테이블 데이터 사용을 위해 mainService를 주입
	}

// (시작) ================================================================================ [geaebal] ================================================================================(시작)
	
	// {GAME 이하영(1)} [gaebal] : 게임 메인 화면으로 이동
	@GetMapping("/go_game")
	public String gaebalGoGame(CategoryVO cvo, Model model) {
		List<CategoryVO> quizCate = mainService.gaebalGetGameCate(cvo);
		model.addAttribute("quizCate", quizCate);

		return "gaebal/game/gameMain";
	}
	
	// {GAME 이하영(2)} [gaebal] : 게임 카테고리 조회 기능 + 해당 카테고리 게임 문제 페이지로 이동
	@GetMapping("/game_play")
	public String gaebalGamePlay(@RequestParam("cateIdx") int cateIdx, HttpSession session,
								 Model model, MemberVO mvo, GameVO vo) {
		mvo = (MemberVO)session.getAttribute("loginMember");
    	if (mvo == null) {
    		model.addAttribute("memberIdx", 0);
		}
    	
    	List<GameVO> quizList = gameService.gaebalGetGameList(vo);
		model.addAttribute("game", quizList);
		model.addAttribute("cateIdx", cateIdx);
		
		return "gaebal/game/gamePlay";
	}
	
	// {GAME 이하영(3)} [gaebal] : 화면에서 게임 카테고리를 받아와 해당 게임 문제를 조회하는 기능 (ajax)
    @RequestMapping("/gaebal_game_play")
    @ResponseBody
    public List<GameVO> gaebalAjaxGamePlay(@RequestParam("cateIdx") int cateIdx, GameVO vo) {
    	List<GameVO> gameList = gameService.gaebalGetGameList(vo);
    	
    	return gameList;
    }
	
	// ==================== [gaebal] ====================
	// [이하영] [gaebal] : 게임 실행 후 포인트를 저장
    @RequestMapping("/save_point") 
    public String gaebalSavePoint(@RequestParam("point") int savedPoint, @RequestParam("score") int savedScore,
    							  HttpSession session, ScoreVO svo, MemberVO mvo) {
    	mvo = (MemberVO)session.getAttribute("loginMember");
    	if (mvo != null) {
    		svo.setMemberIdx(mvo.memberIdx);
    		
		} else {
			svo.setMemberIdx(0);
			System.out.println("비회원으로 게임을 실행합니다.");
		}
    	mainService.gaebalInsertPoint(svo);
    	
    	return "gaebal/game/gameOver"; //수정해야함....~
    }
    
// (끝) ================================================================================ [geaebal] ================================================================================(끝)

	
	
// (시작) ================================================================================ [admin] ================================================================================ (시작)

    // {GAME 이하영(4)} [admin] : 게임 관리 화면으로 이동
	@GetMapping("/go_game_manage_page")
	public String adminGoGameManage(CategoryVO cvo, GameVO gvo, Model model) {
		List<CategoryVO> cateList = mainService.gaebalGetGameCate(cvo);
		model.addAttribute("cate", cateList);
		List<GameVO> gameList = gameService.adminGameCateList(gvo);
		model.addAttribute("game", gameList);
		
		return "admin/game/gameManage";
	}
	
	// {GAME 이하영(5)} [admin] : 게임 추가
	@RequestMapping("/game_insert")
	public String adminGameInsert(GameVO gvo) throws IllegalStateException, IOException {
		/*
		MultipartFile uploadFile = gvo.getUploadFile();
    	
    	if (uploadFile != null && !uploadFile.isEmpty()) {
			// 업로드된 이미지 파일의 원본 파일명
			String filename = uploadFile.getOriginalFilename();
			System.out.println(">>>문의 원본파일명 : " + filename);

			if (filename != null && !filename.isEmpty()) {
				gvo.setQuizOri(filename);
			} else {
				// 파일명이 없는 경우에 대한 처리
				gvo.setQuizOri("UnknownFilename");
			}

			if (uploadFile == null) {
				System.out.println(">>>게임: uploadFile 파라미터가 전달되지 않은 경우");
			} else if (uploadFile.isEmpty()) {
				System.out.println(">>>게임: 전달받은 파일 데이터가 없는 경우");
			} else { // 업로드 파일이 존재하는 경우
				System.out.println(">>>게임: uploadFile.isEmpty() : " + uploadFile.isEmpty());
				// 원본파일명 구하기

				UUID uuid = UUID.randomUUID();

//				String projectPath = System.getProperty("user.dir") + "/src/main/webapp/resources/image/";

				String savedFilename = uuid + "_" + uploadFile.getOriginalFilename();
				System.out.println(">>>문의: 저장파일명 : " + savedFilename);

				// 물리적 파일 복사
				String destPathFile = "C:/MyStudy/temp/" + savedFilename;
				uploadFile.transferTo(new File(destPathFile));

				// 원본 파일명을 vo에 설정
				gvo.setQuizOri(filename); // 수정된 부분

				// 저장 파일명을 vo에 설정
				gvo.setQuizFile(savedFilename);

				// 이미지 미리보기를 위한 파일 경로 설정
				String imagePath = "/temp/" + savedFilename;
				gvo.setImagePath(imagePath);

			}
    	}
		*/
		gameService.adminInsertGame(gvo);
		
		return "redirect:go_game_manage_page";
	}

	// {GAME 이하영(6)} [admin] : 게임 목록 조회 페이지로 이동
	@RequestMapping("/go_game_list")
	public String goGameList(Model model, CategoryVO cvo) {
		List<CategoryVO> cateList = mainService.gaebalGetGameCate(cvo);
		model.addAttribute("cate", cateList);
		
		return "admin/game/gameList";
	}

	// {GAME 이하영(7)} [admin] : 게임 목록 조회 기능 (ajax)
	@RequestMapping("/admin_get_game")
	@ResponseBody
	public List<GameVO> adminGetGame(@RequestParam("cateIdx") int gameCateIdx, GameVO gvo) {
		List<GameVO> gameList = gameService.adminGetGame(gvo);
		
		return gameList;
	}
//	
//	@ModelAttribute("gameMap")
//	public Map<String, String> searchConditionMap() {
//		System.out.println("====> gameMap 실행");
//		Map<String, String> gameMap = new HashMap();
//		gameMap.put("퀴즈 번호", "QUIZ_IDX");
//		gameMap.put("퀴즈", "QUIZ_TITLE");
//		return gameMap;
//	}
//	
//	

	
	
	// {GAME 이하영(8)} [admin] : 게임 문제 수정하는 페이지로 이동
	@RequestMapping("/admin_set_game")
	public String adminSetGame(@RequestParam("quizIdx") int quizIdx, Model model, GameVO gvo, CategoryVO cvo) {
		GameVO selectedGame = gameService.adminGetGameOne(gvo);
		model.addAttribute("game", selectedGame);
		
		List<CategoryVO> cateList = mainService.gaebalGetGameCate(cvo);
		model.addAttribute("cate", cateList);
		
		return "admin/game/gameUpdate";
	}
	
	// {GAME 이하영(9)} [admin] : 게임 문제 수정 기능
	@RequestMapping("/admin_update_game")
	public String adminUpdateGame(@RequestParam("quizIdx") int quizIdx, GameVO gvo) {
		gameService.adminUpdateGame(gvo);
		
		return "redirect:go_game_list";
	}
	
	// {GAME 이하영(10)} [admin] : 게임 문제 삭제 기능
	@RequestMapping("/admin_del_game")
	public String adminDeleteGame(@RequestParam("quizIdx") int quizIdx, GameVO gvo) {
		gameService.adminDeleteGame(gvo);
		
		return "redirect:go_game_list";
	}
	
// (끝) ================================================================================ [admin] ================================================================================ (끝)	
	
}

