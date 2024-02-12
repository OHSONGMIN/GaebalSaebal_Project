package com.devquiz.biz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devquiz.biz.model.CartVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.service.CartService;

@Controller
public class CartController {
	private CartService cartService;
	
	public CartController() {
		System.out.println("=====> CartController() 객체 생성");
	}
	
	@Autowired
	public CartController(CartService cartService) {
		System.out.println("=====> CartController(cartService) 객체 생성");
		System.out.println("::: cartService : " + cartService);
		this.cartService = cartService;
	}
	
	// 상품 목록 > 장바구니 등록
	// public String
	
	// 상품 상세 > 장바구니 등록
	@RequestMapping("/insert_cart_detail")
	public String insertCartProductDetail(HttpSession session, MemberVO loginMember, @RequestParam("productIdx") int productIdx, CartVO cartVO) {
		System.out.println("@@@@@ 장바구니 등록 @@@@@");
		
		// 로그인된 회원 정보 받아오기
		loginMember = (MemberVO) session.getAttribute("loginMember");
		System.out.println("loginMember" + loginMember);
		
		// 받아온 값 확인
		System.out.println("productIdx" + productIdx);
		
		// cartVO에 값 넣기
		cartVO.setMemberIdx(loginMember.getMemberIdx());
		cartVO.setProductIdx(productIdx);
		
		// 중복 체크
		int result = cartService.selectCart(cartVO);
		
		System.out.println("중복 값 확인(중복 X = 0) : "  + result);
		
		if(result == 0) { // 중복 없으면 장바구니 담기
			cartService.insertCart(cartVO);
		}
		
		// 상품 상세 페이지
		return "gaebal/product/getMemberProduct";
	}
	
	// 찜 > 장바구니 등록
	@RequestMapping("/insert_cart_like")
	public String insertCartLike(HttpSession session, int[] likes, CartVO cartVO) {
		System.out.println("@@@@@ 찜에서 장바구니 등록 @@@@@");
		
		// 로그인된 회원 정보 받아오기
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// 등록할 상품 IDX 받아서 진행
		for(int like : likes) {
			// 로그인 회원 IDX와 등록할 상품 IDX cartVO에 넣기
			cartVO.setMemberIdx(loginMember.getMemberIdx());
			cartVO.setProductIdx(like);
			
			System.out.println(cartVO);
			System.out.println(like);
			
			// 중복 체크
			int result = cartService.selectCart(cartVO);
			
			if(result == 0) { // 중복 없으면 장바구니에 담기
				cartService.insertCart(cartVO);
			}
		}
		
		return "redirect:like_list";
	}
	
	// 장바구니 목록 조회
	@RequestMapping("/cart_list")
	public String goCartListPage(HttpSession session, CartVO cartVO, Model model) {
		System.out.println("@@@@@ 장바구니 목록 조회 @@@@@");
		// 로그인된 회원 정보 받아오기
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// cartVO에 로그인된 회원 IDX 지정
		cartVO.setMemberIdx(loginMember.getMemberIdx());
		
		// 로그인 회원의 장바구니 목록 조회
		List<CartVO> cartList = cartService.selectCartList(cartVO);
		
		// model에 저장
		model.addAttribute("cartList", cartList);
		
		// 장바구니 목록 페이지로 보내기
		return "gaebal/cart/cartList";
	}
	
	// 장바구니 목록 삭제
	@RequestMapping("/delete_cart")
	public String deleteCart(HttpSession session, int[] carts, CartVO cartVO) {
		System.out.println("@@@@@ 장바구니 목록 삭제 @@@@@");
		
		// 로그인된 회원 정보 받아오기
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// 삭제할 상품 IDX를 cartVO에 넣고 삭제 처리
		for(int cart : carts) {
			cartVO.setMemberIdx(loginMember.getMemberIdx());
			cartVO.setProductIdx(cart);
			System.out.println(cartVO);
			cartService.deleteCart(cartVO);
		}
		
		// 장바구니 목록 다시 조회(새로고침)
		return "redirect:cart_list";
	}

}
