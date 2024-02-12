package com.devquiz.biz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devquiz.biz.model.CartVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.MyProductVO;
import com.devquiz.biz.model.OrderDetailVO;
import com.devquiz.biz.model.OrderVO;
import com.devquiz.biz.model.PointVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.service.CartService;
import com.devquiz.biz.service.MemberService;
import com.devquiz.biz.service.MyProductService;
import com.devquiz.biz.service.OrderService;
import com.devquiz.biz.service.OrderdetailService;
import com.devquiz.biz.service.PointService;
import com.devquiz.biz.service.ProductService;

@Controller
public class OrderController {
	private OrderService orderService;
	private ProductService productService;
	private OrderdetailService orderdetailService;
	private MyProductService myProductService;
	private CartService cartService;
	private PointService pointService;
	private MemberService memberService;
	
	public OrderController() {
		System.out.println("=====> OrderController() 객체 생성");
	}
	
	@Autowired
	public OrderController(OrderService orderService, ProductService productService, OrderdetailService orderdetailService, MyProductService myProductService
			, CartService cartService, PointService pointService, MemberService memberService) {
		System.out.println("=====> OrderController(orderService, productService) 객체 생성");
		System.out.println("::: orderService : " + orderService);
		System.out.println("::: productService : " + productService);
		System.out.println("::: orderdetailService : " + orderdetailService);
		System.out.println("::: myProductService : " + myProductService);
		System.out.println("::: cartService : " + cartService);
		System.out.println("::: pointService : " + pointService);
		System.out.println("::: memberService : " + memberService);
		this.orderService = orderService;
		this.productService = productService;
		this.orderdetailService = orderdetailService;
		this.myProductService = myProductService;
		this.cartService = cartService;
		this.pointService = pointService;
		this.memberService = memberService;
	}
	
	// 결제창
	@RequestMapping("/go_order_page")
	public String goOrderPage(int[] carts, ProductVO productVO, Model model) {
		System.out.println("@@@@@ 결제창으로 연결 @@@@@");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		for(int cart : carts) {
			productVO.setProductIdx(cart);
			ProductVO vo = productService.getProduct(productVO);
			productList.add(vo);
		}
		model.addAttribute("productList", productList);
		
		return "gaebal/order/order";
	}
	
	// 결제
	@RequestMapping("/insert_order")
	public String insertOrder(int[] orders, int sumPrice, HttpSession session, OrderVO orderVO, OrderDetailVO odVO, MyProductVO mpVO, Model model, PointVO pVO, CartVO cVO) {
		System.out.println("@@@@@ 결제 @@@@@");
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if(sumPrice > loginMember.getPoint()) {
			model.addAttribute("failMsg", "보유한 적립금이 적습니다.");
			// 얼마나 부족한지 보여주기?
			return "gaebal/order/orderFail";
		} else {
			for(int order : orders) {
				mpVO.setMemberIdx(loginMember.getMemberIdx());
				mpVO.setProductIdx(order);
				int result = myProductService.selectMyProductOk(mpVO);
				if (result == 1) {
					model.addAttribute("failMsg", "이미 보유하고 있는 상품입니다.");
					// 뭐 보유하고 있는지도 보여주기?
					// 보유하고 있는 상품 장바구니에서도 삭제?
					return "gaebal/order/orderFail";
				}
			}
			orderVO.setMemberIdx(loginMember.getMemberIdx());
			orderVO.setSumPrice(sumPrice);
			orderService.insertOrder(orderVO);
			int orderIdx = orderService.selectOrderIdx(orderVO);
			
			for(int order : orders) {
				odVO.setMemberIdx(loginMember.getMemberIdx());
				odVO.setOrderIdx(orderIdx);
				odVO.setProductIdx(order);
				orderdetailService.insertOrderDetail(odVO);
			}
			mpVO.setMemberIdx(loginMember.getMemberIdx());
			for(int order : orders) {
				mpVO.setMemberIdx(loginMember.getMemberIdx());
				mpVO.setProductIdx(order);
				myProductService.insertMyProduct(mpVO);
			}
			pVO.setMemberIdx(loginMember.getMemberIdx());
			pVO.setPoint(-sumPrice);
			System.out.println(pVO);
			pointService.buyProductPoint(pVO);
			
			for(int order : orders) {
				cVO.setMemberIdx(loginMember.getMemberIdx());
				cVO.setProductIdx(order);
				cartService.deleteCart(cVO);
			}
			
			// 세션에 저장된 로그인 회원의 포인트 갱신을 어떻게 해야할까?
			
			model.addAttribute("orderIdx", orderIdx);
			return "gaebal/order/orderSucess";
		}
	}
	
	// 주문 조회
	@RequestMapping("/select_order")
	public String selectOrder(HttpSession session, MemberVO loginMember, OrderVO oVO, Model model) {
		System.out.println("@@@@@ 주문 조회 @@@@@");
		loginMember = (MemberVO) session.getAttribute("loginMember");
		oVO.setMemberIdx(loginMember.getMemberIdx());
		List<OrderVO> orderList = orderService.selectOrder(oVO);
		model.addAttribute("orderList", orderList);
		return "gaebal/order/myPageOrder";
	}
	
	// 주문 조회(상세)
	@RequestMapping("/select_order_detail")
	public String selectOrderDetail(HttpSession session, MemberVO loginMember, @RequestParam("value") int value, OrderDetailVO odVO, Model model) {
		System.out.println("@@@@@ 주문 조회(상세) @@@@@");
		loginMember = (MemberVO) session.getAttribute("loginMember");
		odVO.setMemberIdx(loginMember.getMemberIdx());
		odVO.setOrderIdx(value);
		List<OrderDetailVO> OrderDetailList = orderdetailService.selectOrderDetail(odVO);
		model.addAttribute("OrderDetailList", OrderDetailList);
		model.addAttribute("orderIdx", value);
		return "gaebal/order/myPageOrderDetail";
	}
	
	// 주문 취소
	@RequestMapping("/update_order")
	public String updateOrder(HttpSession session, MemberVO loginMember, @RequestParam("orderIdx") int orderIdx, OrderVO oVO, OrderDetailVO odVO, MyProductVO mpVO, PointVO pVO) {
		System.out.println("@@@@@ 결제 취소 시작 @@@@@");
		
		System.out.println("@@@@@ 결제 취소 1 - 로그인 정보 불러오기 @@@@@");
		loginMember = (MemberVO) session.getAttribute("loginMember");
		System.out.println("loginMember : " + loginMember);
		
		System.out.println("@@@@@ 결제 취소 2 - 주문 정보 del = 1 설정 @@@@@");
		oVO.setMemberIdx(loginMember.getMemberIdx());
		oVO.setOrderIdx(orderIdx);
		orderService.updateOrder(oVO);
		
		System.out.println("@@@@@ 결제 취소 3 - 주문 상세 정보 del = 1 설정 @@@@@");
		odVO.setMemberIdx(loginMember.getMemberIdx());
		odVO.setOrderIdx(orderIdx);
		orderdetailService.updateOrderDetail(odVO);
		
		System.out.println("@@@@@ 결제 취소 4 - My_Products 테이블에서 상품 삭제 @@@@@");
		List<OrderDetailVO> productIdxList = orderdetailService.selectOrderDetail(odVO);
		int totalPrice = 0;
		System.out.println("totalPrice 1 : " + totalPrice);
		for(int i = 0; i < productIdxList.size(); i++) {
			OrderDetailVO vo = productIdxList.get(i);
			System.out.println("vo : " + vo);
			
			mpVO.setMemberIdx(loginMember.getMemberIdx());
			mpVO.setProductIdx(vo.getProductIdx());
			
			totalPrice = totalPrice + vo.getProductPrice();
			System.out.println("totalPrice 2 : " + totalPrice);
			
			myProductService.deleteMyProduct(mpVO);
		}
		System.out.println("totalPrice 3 : " + totalPrice);
		
		pVO.setMemberIdx(loginMember.getMemberIdx());
		pVO.setPoint(totalPrice);
		pointService.buyProductPoint(pVO);
		
		// 세션에 저장된 로그인 회원의 포인트 갱신을 어떻게 해야할까?
		
		System.out.println("@@@@@ 결제 취소 끝 @@@@@");
		return "redirect:select_order";
	}

	


}
