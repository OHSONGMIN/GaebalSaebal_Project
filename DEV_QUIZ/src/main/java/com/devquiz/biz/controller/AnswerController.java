package com.devquiz.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devquiz.biz.model.AnswerVO;
import com.devquiz.biz.service.AnswerService;

@Controller
@SessionAttributes("question")
public class AnswerController {
	private AnswerService answerService;
	
	public AnswerController() {
		System.out.println("===========> AnswerController() 객체 생성");
	}
	
	@Autowired
	public AnswerController(AnswerService answerService) {
		System.out.println("===========> AnswerController(answerService) 객체 생성");
		System.out.println("::: AnswerService answerService : " + answerService);
		this.answerService = answerService;
	}
//예림 23.11.24	
	@RequestMapping("/update_answer")
	@ResponseBody
	public String adminupdateAnswer(AnswerVO vo, RedirectAttributes rttr) {
		System.out.println(">>> 답변 수정");
		System.out.println("vo : " + vo);
		
		vo.setAnswerIdx(vo.getAnswerIdx());
		vo.setContent(vo.getContent());
		answerService.adminupdateAnswer(vo);
		rttr.addAttribute("questionIdx", vo.getQuestionIdx());
		
		return "redirect:get_question";
	}
	
	@RequestMapping("/delete_answer")
	public String admindeleteAnswer(AnswerVO vo, SessionStatus sessionStatus, RedirectAttributes rttr) {
		System.out.println(">>> 답변 삭제");
		System.out.println("vo : " + vo);
	
		answerService.admindeleteAnswer(vo);
		sessionStatus.setComplete();
		rttr.addAttribute("questionIdx", vo.getQuestionIdx());
		
		return "redirect:get_question";
	}
	/*
	@RequestMapping("/delete_answer")
	public String admindeleteAnswer(AnswerVO vo, SessionStatus sessionStatus, RedirectAttributes rttr) {
		System.out.println(">>> 답변 삭제");
		System.out.println("vo : " + vo);
		
		answerService.admindeleteAnswer(vo);
		rttr.addAttribute("questionIdx", vo.getQuestionIdx());
		sessionStatus.setComplete();
		
		return "redirect:get_question";
	}*/
//예림 끝
}
