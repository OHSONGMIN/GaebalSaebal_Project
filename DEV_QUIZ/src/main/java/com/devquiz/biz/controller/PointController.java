package com.devquiz.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.PointVO;
import com.devquiz.biz.service.PointService;

@Controller
@SessionAttributes("member")
public class PointController {
	private PointService pointService;
	
	public PointController() {
		System.out.println("===========> PointController() 객체 생성");
	}
	
	@Autowired
	public PointController(PointService pointService) {
		System.out.println("===========> PointService(pointService) 객체 생성");
		System.out.println("::: PointService pointService : " + pointService);
		this.pointService = pointService;
	}
	
//예림 시작	
	@RequestMapping("/get_point")
	public String admingetPoint(@ModelAttribute("memberIdx") int memberIdx, Model model) {
		System.out.println(">>> 해당 회원 포인트 목록 보여주기");
		System.out.println("memberIdx : " + memberIdx);
		List<PointVO> pointList = pointService.admingetPoint(memberIdx);
		model.addAttribute("pointList", pointList); 
		
		return "admin/member/getPointList";
	}
	
	@RequestMapping("/delete_point")
	public String admindeleteBoard(PointVO vo, SessionStatus sessionStatus, RedirectAttributes rttr){
		System.out.println(">>> 포인트 삭제");
		System.out.println("vo : " + vo);
		
		pointService.admindeletePoint(vo);
		sessionStatus.setComplete();
		rttr.addAttribute("memberIdx", vo.getMemberIdx());
		
		return "redirect:get_point";
	}
	
//예림 끝	
	

}
