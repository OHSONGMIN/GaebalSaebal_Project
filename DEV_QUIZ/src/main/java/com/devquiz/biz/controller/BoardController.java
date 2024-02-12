package com.devquiz.biz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devquiz.biz.service.BoardService;
import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CommunityPageVO;

@Controller
@SessionAttributes("board")
//@RequestMapping("/board")
//-to. 예림씨 / 예림씨~왜인지 Requestmapping을 하면 페이지간 이동이 안되어서 우선 주석처리 합니다..!! //하영
public class BoardController {
	private BoardService boardService;
	
	public BoardController() {
		System.out.println("===========> BoardController() 객체 생성");
	}
	
	@Autowired
	public BoardController(BoardService boardService) {
		System.out.println("===========> BoardController(boardService) 객체 생성");
		System.out.println("::: BoardService boardService : " + boardService);
		this.boardService = boardService;
	}
	//예림시작
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	@RequestMapping("/get_board")
	public String admingetBoard(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 상세 보여주기");
		System.out.println("vo : " + vo);

		BoardVO board = boardService.admingetBoard(vo);
		String nickname = boardService.admingetWriter(vo);
		String category = boardService.admingetCategory(vo);
		System.out.println("board : " + board);
		System.out.println("nickname : " + nickname);
		System.out.println("category : " + category);
		
		model.addAttribute("board", board); 
		model.addAttribute("nickname", nickname);
		model.addAttribute("category", category);
		
		return "admin/board/getBoard";
	}
	
	@RequestMapping("/get_board_id")
	public String admingetBoardId(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 상세 보여주기");
		System.out.println("vo : " + vo);

		BoardVO board = boardService.admingetBoard(vo);
		String category = boardService.admingetCategory(vo);
		String nickname = boardService.admingetWriter(vo);
		System.out.println("board : " + board);
		System.out.println("category : " + category);
		
		model.addAttribute("board", board); 
		model.addAttribute("category", category);
		model.addAttribute("nickname", nickname);
		
		return "admin/member/getBoardId";
	}
	
	@RequestMapping("/get_notice_board")
	public String admingetNoticeBoard(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 상세 보여주기");
		System.out.println("vo : " + vo);

		BoardVO board = boardService.admingetBoard(vo);
		System.out.println("board : " + board);
		
		model.addAttribute("board", board); 
		
		return "admin/board/getNoticeBoard";
	}
	
	//게시글 전체 목록 조회(페이징 처리)
	@GetMapping("/get_board_list")
	public String paging(Model model,
	 					@RequestParam(value = "page", required = false, defaultValue = "1") int page)  {
		
		System.out.println("page~~~~~~~~~~~~~~~~~~~~~~~ = " + page);
		
		
		List<BoardVO> BoardPagingList = boardService.adminBoardPaging(page);
		CommunityPageVO pageVO = boardService.adminBoardPagingParam(page);
	    
		System.out.println("pageVO : " + pageVO);
		model.addAttribute("BoardPagingList", BoardPagingList);
		model.addAttribute("paging", pageVO);
		
		
		return "admin/board/getBoardList";

	}
	
	//공지사항만 조회
	@GetMapping("/get_notice_board_list")
	public String npaging(Model model,
	 					@RequestParam(value = "page", required = false, defaultValue = "1") int page)  {
		System.out.println("page = " + page);
		
		List<BoardVO> NoticePagingList = boardService.adminNoticePaging(page);
		CommunityPageVO pageVO = boardService.adminNoticePagingParam(page);

		System.out.println("pageVO : " + pageVO);
		model.addAttribute("NoticePagingList", NoticePagingList);
		model.addAttribute("paging", pageVO);
		
		
		return "admin/board/getNoticeBoardList";

	}
	/*
	@RequestMapping("/get_board_list")
	public String admingetBoardList(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 전체 목록 보여주기");
		System.out.println("vo : " + vo);
		
		List<BoardVO> boardList = boardService.admingetBoardList(vo);
		
		model.addAttribute("boardList", boardList); 
		
		return "admin/board/getBoardList";
	}
	
	@RequestMapping("/get_notice_board_list")
	public String admingetNoticeBoardList(BoardVO vo, Model model) {
		System.out.println(">>> 게시글 전체 목록 보여주기");
		System.out.println("vo : " + vo);
		
		List<BoardVO> NoticeboardList = boardService.admingetNoticeBoardList(vo);
		
		model.addAttribute("NoticeboardList", NoticeboardList); 
		
		return "admin/board/getNoticeBoardList";
	}
	*/
	@RequestMapping("/insert_board_view")
	public String admininsertBoardView() {
		return "admin/board/insertBoard";
	}
	
	@RequestMapping("/insert_board")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println(">>> 게시글 입력");
		System.out.println("vo : " + vo);
		
		boardService.admininsertBoard(vo);
		
		return "redirect:get_notice_board_list";
	}
	
	@RequestMapping("/update_board")
	public String adminupdateBoard(@ModelAttribute("board") BoardVO vo) {
		System.out.println(">>> 게시글 수정");
		System.out.println("vo : " + vo);
		
		boardService.adminupdateBoard(vo);
		
		return "redirect:get_notice_board_list";
	}
	
	@RequestMapping("/delete_board")
	public String admindeleteBoard(BoardVO vo, SessionStatus sessionStatus) {
		System.out.println(">>> 게시글 삭제");
		System.out.println("vo : " + vo);
		
		boardService.admindeleteBoard(vo);
		sessionStatus.setComplete();
		
		return "redirect:get_board_list";
	}
	
	@RequestMapping("/delete_notice_board")
	public String admindeleteNoticeBoard(BoardVO vo, SessionStatus sessionStatus) {
		System.out.println(">>> 게시글 삭제");
		System.out.println("vo : " + vo);
		
		boardService.admindeleteBoard(vo);
		sessionStatus.setComplete();
		
		return "redirect:get_notice_board_list";
	}
	
	@RequestMapping("/get_list")
	public String admingetList(@ModelAttribute("memberIdx") int memberIdx, Model model) {
		System.out.println(">>> 회원 게시글 목록 보여주기");
		System.out.println("memberIdx : " + memberIdx);
		
		List<BoardVO> boardIdList = boardService.admingetList(memberIdx);
		model.addAttribute("boardIdList", boardIdList); 
		
		return "admin/member/getBoardIdList";
	}
	
	@RequestMapping("/delete_board_id")
	public String admindeleteBoardId(BoardVO vo, SessionStatus sessionStatus, RedirectAttributes rttr) {
		System.out.println(">>> 게시글 삭제");
		System.out.println("vo : " + vo);
		
		boardService.admindeleteBoard(vo);
		sessionStatus.setComplete();
		rttr.addAttribute("memberIdx", vo.getMemberIdx());
		
		return "redirect:get_list";
	}
//예림끝
}
