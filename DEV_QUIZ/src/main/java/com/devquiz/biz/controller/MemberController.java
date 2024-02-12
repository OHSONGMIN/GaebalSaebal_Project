package com.devquiz.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.PointVO;
import com.devquiz.biz.service.MailService;
import com.devquiz.biz.service.MemberService;

@Controller
// @SessionAttributes("loginMember")
@SessionAttributes(value = {"loginMember", "favoriteList"})
public class MemberController {
	
	
	private MemberService memberService;
	private MailService mailService;  
	
	public MemberController() {}
	
	@Autowired
	public MemberController(MemberService memberService, MailService mailService) {
	    this.memberService = memberService;
	    this.mailService = mailService;
	}
	
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	
	//회원가입창으로 이동 : 김지홍
	@RequestMapping("/go_insert_member")
	public String insertMemberView(MemberVO vo) {
		return "/gaebal/member/signup";
	}
	
	//메인페이지로 이동 : 김지홍
	@RequestMapping("/go_main")
	public String goMain() {
		return "devquiz_main";
	}
  
	//회원가입 구현 : 김지홍
	@RequestMapping(value = "/insert_member", method = RequestMethod.POST)
	public String insertMember(MemberVO vo) {
		System.out.println(">>>> ");
		System.out.println(vo);
		System.out.println("vo.getPwd().equals(vo.getPwdConfirm()) : " + vo.getPwd().equals(vo.getPwdConfirm()));
		System.out.println("vo.getPwd() : " + vo.getPwd() + ", vo.getPwdConfirm()) : " + vo.getPwdConfirm());
		memberService.insertMember(vo);
		return "/gaebal/member/login";
	}
  
	//아이디 중복체크 : 김지홍
	@RequestMapping("/id_check") 
	@ResponseBody
	public int idCheck(@RequestParam("id") String id) {
	//내가 입력한 아이디가  DB에 있는 아이디이면 중복된 아이디입니다.
		int result = memberService.idCheck(id);	  
		System.out.println("result: " + result);
		return result;
	}
  
	//이메일 중복체크 : 김지홍
	@RequestMapping("/email_check") 
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email) {
		int result = memberService.emailCheck(email);
		return result;
	}
  
	//로그인창으로 이동 : 김지홍
	@RequestMapping("/go_login")
	public String loginView() {
		return "/gaebal/member/login";
	}
  
	//로그인 구현 : 김지홍
	@PostMapping("/login")
	@ResponseBody
	public MemberVO loginMember(MemberVO vo, Model model) {		
		MemberVO loginMember = memberService.loginMember(vo);		
		if(loginMember != null && loginMember.getIsDel() == 0) {
			model.addAttribute("loginMember", loginMember);
			System.out.println(">>>>> 로그인성공");
			return loginMember;
		} else {
			System.out.println(loginMember);
			return null;
		}
	}
	
	//관리자 로그인
	@PostMapping("/adminLogin")
	@ResponseBody
	public MemberVO adminLogin(MemberVO vo, HttpSession session) {
		MemberVO admin = memberService.adminLogin(vo);
		System.out.println(admin.getIsAdmin());
		if (admin != null && admin.getIsAdmin() == 1) {
			session.setAttribute("admin", admin);
			System.out.println(">>>> 관리자 로그인 성공");
			return admin;
		} else {
			System.out.println(admin);
			return null;
		}	
	}	
  
	//로그아웃 : 김지홍
	@RequestMapping("/logout")
	public String logout(SessionStatus status) {
		System.out.println(">>> ");
		status.setComplete();
		return "redirect:main";
	}
	
	//관리자 로그아웃
	@RequestMapping("/admin_logout")
	public String adminLogout(HttpSession session) {
		System.out.println(">>>관리자 로그아웃");
		session.invalidate();
		return "/admin/main/admin";
	}
	
	
  
	//마이페이지로 가기 : 김지홍
	@RequestMapping("/go_mypage") 
	public String goMypage() {
		return "/gaebal/member/myPage";
	}
  
	//회원정보 수정 전 비밀번호 재확인 창으로 가기 : 김지홍
	@RequestMapping("/go_update_password")
	public String goUpdatePassword(){
		return "/gaebal/member/updatePassword"; 
	}
  
	//회원정보 수정의 비밀번호 재확인 메소드 : 김지홍
	@PostMapping("/password_check") 
	@ResponseBody
	public boolean passwordCheck(@RequestParam("pwd") String pwd, HttpSession session) {
		System.out.println("입력 비밀번호 : " + pwd);
	  	MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); 
	  	System.out.println(loginMember);
	  	System.out.println((pwd.equals(loginMember.getPwd())));
	  	return (pwd.equals(loginMember.getPwd()));
	}  
	
	// >> 비밀번호확인을 통해 없애도 됨 : 김지홍, 사용안함
	@RequestMapping("/go_update_info")
	public String goUpdateInfo(){
		return "/gaebal/member/updateInfo"; 
	}
	
	//회원정보수정 : 김지홍
	@RequestMapping(value = "/update_member", method = RequestMethod.POST)
	public String updateMember(@ModelAttribute("loginMember") MemberVO vo, HttpSession session) {	  
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); 
		vo.setId(loginMember.getId());		
		System.out.println("수정할 회원의 ID: " + vo.getId());	 		
		memberService.updateMember(vo);		
		session.setAttribute("loginMember", vo);
		System.out.println("변경된 정보 : " + loginMember.getNickname());
		return "redirect:go_mypage";
	}
  
	//업데이트에 성공하면 보여질 페이지 >> 틀리면 확인해주세요, 맞으면 넘어가기(완료페이지) : 김지홍, 사용안함
	@RequestMapping("/go_update_success")
	public String goUpdateSuccess(){
		return "/gaebal/member/updateSuccess"; 
	}
  
	//회원 탈퇴(물리) : 김지홍
	@RequestMapping(value = "/delete_member", method = RequestMethod.POST)
	public String deleteMember(@RequestParam("pwd") String pwd, HttpSession session) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginMember");
		if(pwd.equals(loginUser.getPwd())) {
			memberService.deleteMember(loginUser);
			session.removeAttribute("loginMember");
		} 
	  	return "devquiz_main";
	}
	
	//회원 탈퇴(논리) : 김지홍
	@RequestMapping(value = "/delete_member_soft", method = RequestMethod.POST)
	public String DeleteMemberSoft(@RequestParam("pwd") String pwd,HttpSession session, SessionStatus status) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");		
		if(pwd.equals(loginMember.getPwd())) {
			memberService.deleteMemberSoft(loginMember);
			session.removeAttribute("loginMember");
			status.setComplete();
			return "redirect:go_main";
		} else {
			System.out.println(">>>>> 탈퇴 실패>>>>");
			return "redirect:go_delete";		
		}		
	}
		
	//탈퇴 확인 이동창으로 이동 : 김지홍
	@RequestMapping("/go_delete") 
	public String goDelete() {
		return"/gaebal/member/deleteMember";
	}
  
	//아이디찾기창으로 이동 : 김지홍	  
	@RequestMapping("/go_search_id")
	public String goSearchId() {
		return "/gaebal/member/searchId";
	}
  
	//아이디찾기 : 김지홍
	@RequestMapping(value = "/search_id", method = RequestMethod.POST)
	@ResponseBody
	public String searchId(String name, String email) {
		String searchId = memberService.searchId(name, email);		 
		return searchId != null ? searchId : "";		 
	}
  
	//비밀번호 찾기 창으로 이동 : 김지홍
	@RequestMapping("/go_search_pwd")
	public String goSearchPwd() {
		return "/gaebal/member/searchPwd";
	}
  
	//비밀번호 찾은 창을 이동 : 김지홍
	@RequestMapping(value = "/go_search_pwd_success", method = RequestMethod.POST)
	public String goSearchPwdSuccess() {
		return "/gaebal/member/searchPwdSuccess";
	}
  
	//비밀번호 찾기
	//비밀번호 찾기 관련 실제 존재 회원인지 찾기  후 있으면 비번 바꾸기 >>>> 김지홍 1124
	@RequestMapping("/search_mem_pwd")
	@ResponseBody
	public String searchMemPwd(@RequestParam("id") String id, @RequestParam("email") String email) throws Exception {
		MemberVO vo = memberService.searchMem(id, email);		
		System.out.println(vo);
		String updatePwd = "0";		
		for (int i = 1; i <= 9; i++) {
			updatePwd += (int)(Math.random() * 10);
		}		
		System.out.println(updatePwd);		
		System.out.println(vo);
		
		if(vo != null) {
			vo.setPwd(updatePwd);
			System.out.println(vo);
			memberService.pwdUpdate(vo.getEmail(),updatePwd);		
			mailService.sendMail(vo);
			
			return "success";		
		} else {
			System.out.println("실패했다는소리에요");
			return "error";
		}	
	
	}
	
	// 지홍
	// 11월 28일 추가
	@ModelAttribute("myFavorite")
	@ResponseBody
	public List<CategoryVO> myFavorite(Model model, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		int memberIdx = 0;
		if (loginMember != null) {
			memberIdx = loginMember.getMemberIdx();
			List<CategoryVO> favoriteList = memberService.myFavorite(memberIdx);
			model.addAttribute("favoriteList", favoriteList);
			System.out.println("나의 즐겨찾기: " + favoriteList);
			return favoriteList;
		} else {
			System.out.println("즐겨찾기 내용 없음");
			return null;
		}
	}
		
	//내가쓴글모아보기
	@RequestMapping("/go_my_write")
	public String myWrite(HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		int memberIdx = loginMember.getMemberIdx();
		List<CommunityVO> myWrite = memberService.myWrite(memberIdx);
		System.out.println("조회된 글 수: " + myWrite.size());
		model.addAttribute("myWrite", myWrite);
		System.out.println("내가 쓴 글 모아보기 시작~~~!!~~~~!!~~~!");
		System.out.println("memberIdx를 알려주세요 : " + memberIdx);
		return "/gaebal/member/myWrite";
	}
	
	
	//포인트 적립내역보기
	@RequestMapping("/go_my_point")
	public String myPoints(HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		int memberIdx = loginMember.getMemberIdx();
		List<PointVO> myPoints = memberService.myPoints(memberIdx);
		model.addAttribute("myPoints", myPoints);
		return "/gaebal/member/myPoint";
		
	}
	
	
	
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	
	
//예림 시작    //예림 시작    //예림 시작	//예림 시작    //예림 시작    //예림 시작    //예림 시작    //예림 시작    //예림 시작
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("아이디", "ID");
		conditionMap.put("이름", "NAME");
		System.out.println(":: conditionMap : " + conditionMap);
		return conditionMap;
	}

	@RequestMapping("/get_member")
	public String admingetMember(int memberIdx, Model model) {
		System.out.println(">>> 회원 상세 보여주기");
		System.out.println("memberIdx : " + memberIdx);

		MemberVO member = memberService.admingetMember(memberIdx);
		int cnt = memberService.admingetIdCnt(memberIdx);
		int point = memberService.admingetPointCnt(memberIdx);
		int order = memberService.adminorderCnt(memberIdx);
		System.out.println("member : " + member);
		
		model.addAttribute("member", member);
		model.addAttribute("cnt", cnt);
		model.addAttribute("point", point);
		model.addAttribute("order", order);
		
		return "admin/member/getMember";
	}
	/*
	@RequestMapping("/get_member_list")
	public String admingetMemberList(MemberVO vo, Model model) {
		System.out.println(">>> 회원 전체 목록 보여주기");
		System.out.println("vo : " + vo);
		
		List<MemberVO> memberList = memberService.admingetMemberList(vo);
		
		model.addAttribute("memberList", memberList); 
		
		return "admin/member/getMemberList";
	}
	*/
	
	@RequestMapping("/update_memeber")
	public String adminupdateMemeber(@ModelAttribute("member") MemberVO vo) {
		System.out.println(">>> 회원정보 수정");
		System.out.println("vo : " + vo);
		
		memberService.adminupdateMember(vo);
		
		return "redirect:get_member_list";
	}
	
	@RequestMapping("/delete_member")
	public String admindeleteMember(MemberVO vo, SessionStatus sessionStatus) {
		System.out.println(">>> 회원 탈퇴");
		System.out.println("vo : " + vo);
		
		memberService.admindeleteMember(vo);
		sessionStatus.setComplete();
		
		return "redirect:get_member_list";
	}
	
	@RequestMapping("/get_board_ld")
	public String admingetBoardList(int memberIdx, Model model) {
		System.out.println(">>> 해당 회원 게시글 목록 보여주기");
		System.out.println("memberIdx : " + memberIdx);
		
		List<BoardVO> boardIdList = memberService.admingetId(memberIdx);
		
		model.addAttribute("boardIdList", boardIdList); 
		
		return "admin/member/getBoardIdList";
	}
	
	@RequestMapping("/update_admin")
	public String adminupdateAdmin(@ModelAttribute("member") MemberVO vo) {
		System.out.println(">>> 관리자 권한 부여");
		System.out.println("vo : " + vo);
		
		memberService.adminupdateAdmin(vo);
		
		return "redirect:get_member_list";
	
	}
	
	// (시작) ==================== [한예림 23.11.28] ==================== (시작)	
	@RequestMapping("/get_member_list")
	public String paging(Model model,
				@RequestParam(value = "page", required = false, defaultValue = "1") int page)  {

		System.out.println("page~~~~~~~~~~~~~~~~~~~~~~~ = " + page);


		List<MemberVO> MemberPagingList = memberService.adminMemberPaging(page);
		CommunityPageVO pageVO = memberService.adminMemberPagingParam(page);

		System.out.println("pageVO : " + pageVO);
		model.addAttribute("MemberPagingList", MemberPagingList);
		model.addAttribute("paging", pageVO);


		return "admin/member/getMemberList";

	}
	// (끝) ==================== [한예림 23.11.28] ==================== (끝)
//예림 끝	//예림 끝	//예림 끝	//예림 끝	//예림 끝	//예림 끝	//예림 끝	//예림 끝	//예림 끝	//예림 끝	
	
}
