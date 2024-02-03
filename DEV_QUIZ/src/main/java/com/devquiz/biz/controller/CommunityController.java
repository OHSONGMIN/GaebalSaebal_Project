package com.devquiz.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommentVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.service.CommunityService;
import com.devquiz.biz.service.MemberService;

@Controller
@SessionAttributes("community")
//@RequestMapping("/community") 을 하면 실행되지 않았던 이유 :
//class 단위로 @RequestMapping을 할 경우 class 하위 메서드에 공통된 경로를 제공하기 때문
//다시 말해 get_community라고 요청받아도 community/get_community로 인식해서 해당 메서드(get_community)를 찾을 수 없었던 것이다
public class CommunityController {

	private CommunityService communityService;
	private MemberService memberService;

	public CommunityController() {
		System.out.println("===========> CommunityController() 객체 생성");
	}

	@Autowired
	public CommunityController(CommunityService communityService, MemberService memberService) {
		System.out.println("===========> CommunityController(communityService) 객체 생성");
		System.out.println("::: CommunityController communityService : " + communityService);
		this.communityService = communityService;
		this.memberService = memberService;
	}

	//@ModelAttribute 선언된 메소드는 @RequestMapping 메소드보다 먼저 실행
	//@ModelAttribute는 컨트롤러 메소드가 실행되기 전에 모델에 데이터를 추가하거나 수정하는 데 사용된다.
	//@ModelAttribute 어노테이션이 붙은 메소드의 반환값은 자동으로 모델에 추가되며, 이후에 호출되는 컨트롤러 메소드에서 이 모델을 사용할 수 있다.
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	
	//해당 카테고리 게시글만 조회(전체 게시글 조회) : 오송민
	@GetMapping("/get_community_list_by_cate")
	public String paging(Model model,
	 					@RequestParam(value = "page", required = false, defaultValue = "1") int page,
	 					@RequestParam(value = "cateIdx", required = false, defaultValue = "-1") int cateIdx)  {
		
	    List<CommunityVO> communityPagingList; 
	    CommunityPageVO pageVO; //를 먼저 선언한다.

	    if (cateIdx > 0) {
	        // 카테고리가 지정된 경우
	        communityPagingList = communityService.getCommunityPagingListByCate(cateIdx, page); //해당 카테고리+지정한 페이지 에 알맞은 게시글을 일정한 개수만큼 가져온다.
	        pageVO = communityService.communityPagingParamByCate(cateIdx, page); //페이징 처리, communityDAO.communityBoardCountByCate(cateIdx)를 호출 -> 하단의 페이지 버튼에 이용
	        
	        String cateName = communityService.getSelCateName(cateIdx);
	        model.addAttribute("selCateName", cateName);
	        model.addAttribute("selCateIdx", cateIdx);
	    } else {
	        // 카테고리가 지정되지 않은 경우
	        communityPagingList = communityService.getCommunityPagingList(page); //지정한 페이지 에 알맞은 게시글을 일정한 개수만큼 가져온다.
	        pageVO = communityService.communityPagingParam(page); //페이징 처리, communityDAO.communityBoardCount()를 호출 -> 하단의 페이지 버튼에 이용
	    }

	    model.addAttribute("communityPagingList", communityPagingList); //해당 조건(카테고리+페이지)에 맞는 게시글 목록
	    model.addAttribute("paging", pageVO);

	    List<CategoryVO> communityCate = communityService.getCommunityCate();
	    model.addAttribute("communityCate", communityCate);
	    
	    return "gaebal/community/getCommunityListByCate";
	}
	
	
	//게시글 작성 페이지로 이동하기 : 오송민
	@RequestMapping("/insert_community_page")
	public String insertCommunityPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) { 
	//게시글 작성 페이지로 이동 후 이전 페이지로 이동했을 때 원래 위치하던 페이지로 이동하기 위해 page 값을 받아온다.
		
		List<CategoryVO> communityCate = communityService.getCommunityCate(); //커뮤니티의 모든 카레고리 목록
		model.addAttribute("communityCate", communityCate);
		model.addAttribute("page", page); //원래 위치 페이지
		return "gaebal/community/insertCommunityPage";
	}


	//게시글 상세 보기 : 오송민
	@GetMapping("/get_community")
	public String getCommunity(@RequestParam("boardIdx") int boardIdx
			, @RequestParam(value = "page", required = false, defaultValue = "1") int page 
			, @RequestParam(value = "cateIdx", required = false, defaultValue = "-1") int cateIdx 
			, Model model, HttpSession session) { 
		
		CommunityVO vo = new CommunityVO();
		vo.setBoardIdx(boardIdx); //@RequestParam으로 가져온 boardIdx를 vo에 set한다.
		
		CommunityVO community = communityService.getCommunity(vo); //boardIdx를 이용해 게시글을 가져온 뒤 community에 담는다.
		model.addAttribute("community", community);
		
		List<CommentVO> commentList = communityService.getCommentList(vo); //boardIdx를 이용해 해당 게시글의 댓글 목록을 가져온다.
		model.addAttribute("commentList", commentList);
		
		//왜 필요하지? 게시글 수정할 때 필요할 것으로 보임.. 다시 살펴보기
		List<CategoryVO> communityCate = communityService.getCommunityCate(); //커뮤니티의 모든 카테고리 목록을 가져온 뒤
		model.addAttribute("communityCate", communityCate); //model에 담는다.
		
		model.addAttribute("page", page); //현재 page를 model에 담는다.
		
		if (cateIdx > 0) { //해당 게시글의 카테고리(cateIdx)를 model에 담는다.
			model.addAttribute("cateIdx", cateIdx);
		}
		
		return "gaebal/community/getCommunity";
	
	}
	
	
}
