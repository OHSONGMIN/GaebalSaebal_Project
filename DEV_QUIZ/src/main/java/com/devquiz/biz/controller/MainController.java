package com.devquiz.biz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.service.CommunityService;
import com.devquiz.biz.service.MainService;

@Controller
public class MainController {
	
	private CommunityService communityService;
	private MainService mainService;
	
	public MainController() { }
	
	@Autowired
	public MainController(MainService mainService, CommunityService communityService) {
		this.mainService = mainService;
		this.communityService = communityService;
	}
	// [이하영] : 메인화면으로 이동.
	
   @GetMapping("/main")
   public String devquizMain(Model model, HttpSession session) {
      List<CommunityVO> hotCommunityList = communityService.getHotCommunityList();
      List<BoardVO> noticeList = mainService.getNotice();
      session.setAttribute("hotCommunityList", hotCommunityList);
      session.setAttribute("noticeList", noticeList);
      return "devquiz_main";
   }
	
	
	// ==================== [admin] ====================
	
	// [이하영] [admin]: 관리자 로그인 화면으로 이동. (관리자 계정 로그인부터 해야하기 때문에 로그인 화면이 첫번째 화면)
	@GetMapping("/admin")
	public String admin() {
		return "admin/main/admin";
	}
	
	// [이하영] [admin]: 관리자 메인 화면으로 이동. (관리자 두번째 페이지)
	@GetMapping("/admin_main")
	public String adminMain() {
		return "admin/main/adminMain";
	}
	
	// [이하영] [admin] : 카테고리 관리 페이지로 이동.
	@GetMapping("/go_cate_manage_page") 
	public String goCateManagePage(CategoryVO cvo, Model model) {
		List<CategoryVO> cateList = mainService.adminGetCateAll(cvo);
		model.addAttribute("cate", cateList);
		
		return "admin/main/cate_insert";
	}
	
	// [이하영] [admin] : 카테고리 테이블에 데이터를 추가.
	@RequestMapping("/cate_insert")
	@ResponseBody
	public List<CategoryVO> cateInsert(@RequestParam("cateType") String cateType, @RequestParam("cateName") String cateName,
									   Map<String, String> map, CategoryVO cvo) {
		map.put("cateType", cateType);
		map.put("cateName", cateName);
		
		mainService.adminInsertCate(map);
		
		List<CategoryVO> cList = mainService.adminGetCateAll(cvo);
		
		return cList; 
	}
	
	// [이하영] [gaebal] : 헤더에 회원 포인트를 실시간으로.....! 1128추가
	@RequestMapping("/get_mem_point")
	@ResponseBody
	public MemberVO getMemPoint(@RequestParam("memberIdx") int memberIdx, HttpSession session,
								MemberVO mvo) {
		mvo	= (MemberVO)session.getAttribute("loginMember");
		MemberVO getPoint = new MemberVO();
				
		if (mvo != null) {
			mvo.setMemberIdx(memberIdx);
			getPoint = mainService.gaebalGetMemPoint(mvo);
			
			// 11/30 민주 추가
			session.setAttribute("loginMember", getPoint);
			//
			
			return getPoint;
		}
		
		return getPoint;
	}

	
	
	

}
