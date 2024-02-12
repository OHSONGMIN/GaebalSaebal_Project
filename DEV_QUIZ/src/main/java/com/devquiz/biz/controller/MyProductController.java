package com.devquiz.biz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.MyProductVO;
import com.devquiz.biz.service.MyProductService;

@Controller
public class MyProductController {
	private MyProductService myProductService;
	
	public MyProductController() {
		System.out.println("=====> MyProductController() 객체 생성");
	}
	
	@Autowired
	public MyProductController(MyProductService myProductService) {
		System.out.println("=====> MyProductController(myProductService) 객체 생성");
		System.out.println("::: myProductService : " + myProductService);
		this.myProductService = myProductService;
	}
	
	// 마이페이지 조회
	@RequestMapping("/myproduct_list")
	public String MyProductList(HttpSession session, MemberVO loginMember, MyProductVO mpVO, Model model) {
		System.out.println("@@@@@ 내 상품 조회 @@@@@");
		
		loginMember = (MemberVO) session.getAttribute("loginMember");
		
		mpVO.setMemberIdx(loginMember.getMemberIdx());
		List<MyProductVO> myProductList = myProductService.selectMyProduct(mpVO);
		
		model.addAttribute("myProductList", myProductList);
		
		return "gaebal/myPage/MyProduct";
	}

}
