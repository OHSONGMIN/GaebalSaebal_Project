package com.devquiz.biz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devquiz.biz.model.LikeVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.service.LikeService;

@Controller
public class LikeController {
	private LikeService likeService;
	
	public LikeController() {
		System.out.println("=====> LikeController() 객체 생성");
	}
	
	@Autowired
	public LikeController(LikeService likeService) {
		System.out.println("=====> LikeController(likeService) 객체 생성");
		System.out.println("::: likeService : " + likeService);
		this.likeService = likeService;
	}
	
	// 상품 목록 > 찜 등록
	// public String 
	
	// 상품 상세 > 찜 등록
	@RequestMapping("/insert_like_detail")
	public String insertLikeDetail(HttpSession session, MemberVO loginMember, @RequestParam("productIdx") int productIdx, LikeVO likeVO) {
		System.out.println("@@@@@ 상품 상세 > 찜 등록 @@@@@");
		
		// 로그인된 회원 정보 받아오기
		loginMember = (MemberVO) session.getAttribute("loginMember");
		System.out.println(loginMember);
		
		// 받아온 값 확인
		System.out.println(productIdx);
		
		// likeVO에 필요한 값 넣기
		likeVO.setMemberIdx(loginMember.getMemberIdx());
		likeVO.setProductIdx(productIdx);
		
		// 중복 체크
		int result = likeService.selectLike(likeVO);
		
		System.out.println("중복 값 확인(중복 X = 0) : " + result);
		
		if(result == 0) {
			likeService.insertLike(likeVO);
		}
		
		// 상품 상세 페이지
		return "gaebal/product/getMemberProduct";
	}
	
	// 찜 목록 조회
	@RequestMapping("/like_list")
	public String goLikeListPage(HttpSession session, LikeVO likeVO, Model model) {
		System.out.println("@@@@@ 찜 목록 조회 @@@@@");
		
		// 로그인된 회원 정보 받아오기
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// likeVO에 로그인된 회원 IDX 넣기
		likeVO.setMemberIdx(loginMember.getMemberIdx());
		System.out.println(likeVO);
		
		// 로그인 회원의 찜 목록 조회
		List<LikeVO> likeList = likeService.selectLikeList(likeVO);
		
		// model에 저장
		model.addAttribute("likeList", likeList);
		
		// 찜 목록 페이지로 보내기
		return "gaebal/like/likeList";
	}
	
	// 찜 목록 삭제
	@RequestMapping("/delete_like")
	public String deleteLike(int[] likes, LikeVO likeVO, HttpSession session) {
		System.out.println("@@@@@ 찜 목록 삭제 @@@@@");
		
		// 로그인된 회원 정보 받아오기
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// 삭제할 상품 IDX를 likeVO에 넣고 삭제 처리
		for(int like : likes) {
			likeVO.setMemberIdx(loginMember.getMemberIdx());
			likeVO.setProductIdx(like);
			System.out.println(likeVO);
			likeService.deleteLike(likeVO);
		}
		
		// 찜 목록 다시 조회(새로고침)
		return "redirect:like_list";
	}
	
}
