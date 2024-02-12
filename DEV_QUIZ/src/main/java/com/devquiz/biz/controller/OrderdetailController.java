package com.devquiz.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devquiz.biz.model.OrderDetailVO;
import com.devquiz.biz.service.OrderdetailService;

@Controller
public class OrderdetailController {
	@Autowired
    private OrderdetailService orderdetailService;

//예림 시작 23.11.27
	@RequestMapping("/get_order")
	public String adminorderList(OrderDetailVO vo, Model model) {
		System.out.println(">>> 해당 회원  주문 목록 보여주기");
		System.out.println("vo : " + vo);
			
		List<OrderDetailVO> orderList = orderdetailService.adminorderList(vo);
		//회원별 주문 건수
		int orderCnt = orderdetailService.adminorderCnt(vo);
		//주문별 상품 건수
		int orderDetailCnt = orderdetailService.adminorderDetailCnt(vo);
		System.out.println("orderDetailCnt : " + orderDetailCnt);
		System.out.println("orderCnt : " + orderCnt);
		model.addAttribute("orderList", orderList); 
		model.addAttribute("orderCnt", orderCnt); 
		model.addAttribute("orderDetailCnt", orderDetailCnt); 
		
		return "admin/member/getOrderList";
	}
		
	@RequestMapping("/order_detail")
	public String adminorderDetail(OrderDetailVO vo, Model model) {
		System.out.println(">>> 해당 주문번호 상세 목록 보여주기");
		System.out.println("vo : " + vo);
			
		List<OrderDetailVO> orderDetailList = orderdetailService.adminorderDetail(vo);
			model.addAttribute("orderDetailList", orderDetailList); 
		
		return "admin/member/getOrderDetail";
	}
//예림 끝
}
