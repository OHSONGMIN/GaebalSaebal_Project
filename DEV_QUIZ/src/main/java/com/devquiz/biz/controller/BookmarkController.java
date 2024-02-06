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
	
}
