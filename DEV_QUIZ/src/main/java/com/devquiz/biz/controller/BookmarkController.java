package com.devquiz.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.devquiz.biz.model.BookmarkVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.service.BookmarkService;
import com.devquiz.biz.service.CommunityService;


@Controller
@SessionAttributes("bookmark")
//@RequestMapping("/bookmark")
public class BookmarkController {

	
	private BookmarkService bookmarkService;
	
	private CommunityService communityService;
	
	public BookmarkController() {
		System.out.println("===========> BookmarkController() 객체 생성");
	}
	
	@Autowired
	public BookmarkController(BookmarkService bookmarkService, CommunityService communityService) {
		System.out.println("===========> BookmarkController(BookmarkService) 객체 생성");
		System.out.println("===========> BookmarkController(CommunityService) 객체 생성");
		System.out.println("::: BookmarkController BookmarkService : " + bookmarkService);
		System.out.println("::: BookmarkController CommunityService : " + communityService);
		this.bookmarkService = bookmarkService;
		this.communityService = communityService;
	}
	
	// @ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	
	//즐겨찾기(북마크) 여부 확인
	@RequestMapping("/get_bookmark_status")
	@ResponseBody
	public String getBookmarkStatus(@RequestParam("selCateIdx") String selCateIdx, BookmarkVO vo, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		vo.setMemberIdx(loginMember.getMemberIdx());
		vo.setCateIdx(Integer.parseInt(selCateIdx));
		
		System.out.println(">>> 북마크 조회"); 
		System.out.println("북마크 조회 전 vo : " + vo);
		
		int intValue = bookmarkService.getBookmarkStatus(vo);
		String result = "" + intValue;
		
		System.out.println("북마크 조회 후 : " + vo + ", 북마크 result : " + result);
		
		return result; //북마크 된 상태이면 1, 북마크 되지 않은 상태이면 0를 반환
	}
	
	
	//즐겨찾기(북마크) 등록 
	@RequestMapping("/add_bookmark") 
	@ResponseBody
	public String addBookmark(@RequestParam("selCateIdx") String selCateIdx, BookmarkVO vo, HttpSession session, RedirectAttributes redirectAttributes) { 
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		vo.setMemberIdx(loginMember.getMemberIdx());
		vo.setCateIdx(Integer.parseInt(selCateIdx));
		
		System.out.println(">>> 북마크 등록");
		System.out.println("북마크 등록 전 vo : " + vo);
		
		bookmarkService.addBookmark(vo); //memberIdx, selCateIdx 전달
		
		return "success"; //String "success"를 반환
	}
	
	
	//즐겨찾기(북마크) 삭제
	@RequestMapping("/delete_bookmark") //RedirectAttributes 필요없는지 확인하기!!
	@ResponseBody
	public String deleteBookmark(@RequestParam("selCateIdx") String selCateIdx, BookmarkVO vo, HttpSession session, RedirectAttributes redirectAttributes) { 
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		vo.setMemberIdx(loginMember.getMemberIdx());
		vo.setCateIdx(Integer.parseInt(selCateIdx));

		System.out.println(">>> 북마크 삭제");
		System.out.println("북마크 삭제 전 vo : " + vo);
		
		bookmarkService.deleteBookmark(vo); //memberIdx, selCateIdx 전달
		
		return "success";	
	}
	
	
	//즐겨찾기 카테고리 게시글만 조회 : 오송민
	@GetMapping("/get_community_list_by_bookmark")
	public String paging(Model model,
	 					@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "memberIdx", required = false) Integer memberIdx)  {
		System.out.println("page = " + page);
		
	    List<CommunityVO> communityPagingList; //페이징 된 즐겨찾기 게시글 목록
	    CommunityPageVO pageVO; //페이징 처리를 위한 데이터(하단의 페이지 버튼)

        System.out.println("page for bookmark = " + page);
        communityPagingList = bookmarkService.getCommunityPagingListByBookmark(memberIdx, page);
        pageVO = bookmarkService.communityPagingParamByBookmark(memberIdx, page);

	    model.addAttribute("communityPagingList", communityPagingList);
	    model.addAttribute("paging", pageVO);
	    
	    List<CategoryVO> communityCate = communityService.getCommunityCate();
	    model.addAttribute("communityCate", communityCate);
	    
	    return "gaebal/community/getCommunityListByBookmark";
	}
	
}
