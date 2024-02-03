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
	
}
