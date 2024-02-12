
package com.devquiz.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devquiz.biz.model.AnswerVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.model.QuestionVO;
import com.devquiz.biz.service.MainService;
import com.devquiz.biz.service.QuestionService;

@Controller
public class QuestionController {
	
	private QuestionService questionService;
	private MainService mainService;
	
	public QuestionController() { }
	
	@Autowired
	public QuestionController(QuestionService questionService, MainService mainService) {
		System.out.println("갈깁니다 QuestionController(QuestionService questionService) 갈깁니다");
		this.questionService = questionService;
		this.mainService = mainService;
	}
	
	// [이하영] [gaebal] : 문의 게시판으로 이동.
	@GetMapping("/go_question") 
	public String gaebalQuestion(QuestionVO qvo, Model model) {
		List<QuestionVO> questionList = questionService.gaebalQuestionAllList(qvo);
		model.addAttribute("question", questionList);
		
		return "gaebal/question/question";
	}
	
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap();
		conditionMap.put("작성자", "NICKNAME");
		conditionMap.put("제목", "QUESTION_TITLE");
		return conditionMap;
	}
	
	@RequestMapping("/go_serach_question_list")
	public String getMemberProductList(QuestionVO qvo, Model model) {
		
		List<QuestionVO> questionList = questionService.gaebalQuestionList(qvo);
		model.addAttribute("question", questionList);

		return "gaebal/question/questionSearch";
	}
	
	
	// [이하영] [gaebal] : 문의 상세보기.
	@GetMapping("/question_detail") 
	public String gaebalQuestionDetail(HttpSession session, MemberVO mvo,
									   QuestionVO qvo, Model model) {
		mvo = (MemberVO)session.getAttribute("loginMember");
		
		QuestionVO questionDetail = questionService.gaebalQuestion(qvo);
		model.addAttribute("question", questionDetail);
		model.addAttribute("loginMember", mvo);
		
		return "gaebal/question/questionDetail";
	}
	
	// [이하영] [gaebal] : 선택 카테고리가 상품이면 상품 대분류를 전달
    @RequestMapping("/get_prods_main_cate")
    @ResponseBody
    public List<QuestionVO> gaebalGetProdsMainCate(@RequestParam("cateName") String cateName, 
    							  QuestionVO qvo) {
        List<QuestionVO> prodsMainCateList = new ArrayList<QuestionVO>();
    	
    	try {
    		if ("상품".equals(cateName)) {
    			// 상품 대분류와 상품 목록을 전달해준다 (?)
    			prodsMainCateList = questionService.gaebalGetProdsMainCate(qvo);
    		} 
		} catch (Exception e) {
			System.out.println(e);
		}
    	return prodsMainCateList;
    }
	
    // [이하영] [gaebal] : 선택 카테고리가 상품이면 상품명을 전달
    // cateName이 상품이면, productIdx를 전달해준다.
    @RequestMapping("/get_prods")
    @ResponseBody
    public List<ProductVO> gaebalGetProds(@RequestParam("prodsMainCate") String prodsMainCate,
    									  @RequestParam("prodsIdx") int prodsIdx, ProductVO pvo) {
    	List<ProductVO> prodsNameList = new ArrayList();
    	pvo.setCateIdx(prodsIdx);
    	
    	try {
    		prodsNameList = questionService.gaebalGetProdsName(pvo);
		} catch (Exception e) {
			System.out.println(e);
		}
    	return prodsNameList;
    }
	
	// [이하영] [gaebal] : 문의 게시글 작성 페이지로 이동.
	@RequestMapping("/have_a_question")
	public String gaebalQuestionInsert(CategoryVO cvo, MemberVO mvo, Model model, HttpSession session) {
		mvo = (MemberVO)session.getAttribute("loginMember");
		if (mvo == null) {
			return "redirect:go_login";
		}
		
		List<CategoryVO> cateList = mainService.gaebalGetQuestionCate(cvo);
		model.addAttribute("cate", cateList);
		
		return "gaebal/question/questionInsert";
	}
	
	// [이하영] [gaebal] : 문의 등록 버튼을 누르면 작성됨.
    @RequestMapping("/question_write") 
    public String gaebalQuestionWrite(@RequestParam("productIdx") int productIdx, 
    								  HttpSession session, QuestionVO qvo, MemberVO mvo)
    					throws IllegalStateException, IOException {
    	mvo = (MemberVO)session.getAttribute("loginMember");
    	if (mvo == null) {
    		return "redirect:go_login";
    	}
    	
    	qvo.setMemberIdx(mvo.memberIdx);
    	
    	MultipartFile uploadFile = qvo.getUploadFile();
    	
    	if (uploadFile != null && !uploadFile.isEmpty()) {
			// 업로드된 이미지 파일의 원본 파일명
			String filename = uploadFile.getOriginalFilename();
			System.out.println(">>>문의 원본파일명 : " + filename);

			if (filename != null && !filename.isEmpty()) {
				qvo.setQuestionOri(filename);
			} else {
				// 파일명이 없는 경우에 대한 처리
				qvo.setQuestionOri("UnknownFilename");
			}

			if (uploadFile == null) {
				System.out.println(">>>문의: uploadFile 파라미터가 전달되지 않은 경우");
			} else if (uploadFile.isEmpty()) {
				System.out.println(">>>문의: 전달받은 파일 데이터가 없는 경우");
			} else { // 업로드 파일이 존재하는 경우
				System.out.println(">>>문의: uploadFile.isEmpty() : " + uploadFile.isEmpty());
				// 원본파일명 구하기

				UUID uuid = UUID.randomUUID();

//				String projectPath = System.getProperty("user.dir") + "/src/main/webapp/resources/image/";

				String savedFilename = uuid + "_" + uploadFile.getOriginalFilename();
				System.out.println(">>>문의: 저장파일명 : " + savedFilename);

				// 물리적 파일 복사
				String destPathFile = "C:/MyStudy/temp/" + savedFilename;
				uploadFile.transferTo(new File(destPathFile));

				// 원본 파일명을 vo에 설정
				qvo.setQuestionOri(filename); // 수정된 부분

				// 저장 파일명을 vo에 설정
				qvo.setQuestionFile(savedFilename);

				// 이미지 미리보기를 위한 파일 경로 설정
				String imagePath = "/temp/" + savedFilename;
				qvo.setImagePath(imagePath);

			}
    	}
    	questionService.gaebalQuestionInsert(qvo);
    	
    	return "redirect:go_question"; // 문의 게시판 처음 페이지로 보내벌여
    }
    
    // [이하영] 문의글 수정하러 이동
    @GetMapping("/set_question")
    public String gaebalSetQuestion(CategoryVO cvo, QuestionVO qvo, Model model) {
    	QuestionVO questionSet = questionService.gaebalQuestion(qvo);
		model.addAttribute("question", questionSet);
		
  		List<CategoryVO> cateList = mainService.gaebalGetQuestionCate(cvo);
		model.addAttribute("cate", cateList);
		
    	return "gaebal/question/questionUpdate";
    }
    
    // [이하영] 문의글 수정됨
    @RequestMapping("/update_question")
    public String gaebalQuestionUpdate(@RequestParam("questionIdx") int qIdx, QuestionVO qvo) {
    	questionService.gaebalQuestionUpdate(qvo);
    	
    	return "redirect:go_question";
    }
    
    // [이하영] 문의글 삭제됨
    @RequestMapping("/del_question")
    public String gaebalQuestionDel(@RequestParam("questionIdx") int questionIdx,
    								HttpSession session, MemberVO mvo,
    								SessionStatus sessionStatus, QuestionVO qvo) {
		mvo = (MemberVO)session.getAttribute("loginMember");
		if (mvo == null) {
			return "redirect:go_login";
		}
    	
    	questionService.gaebalQuestionDelete(qvo);
    	sessionStatus.setComplete();
    	return "redirect:go_question";
    }
 // (이하영 끝) ==================== (이하영 끝) ==================== (이하영 끝) ==================== (이하영 끝) ====================
    
    
//예림 시작
	   /*
    @RequestMapping("/get_question_list")
  	public String admingetQuestionList(QuestionVO vo, Model model) {
  		System.out.println(">>> 문의 전체 목록 보여주기");
  		System.out.println("vo : " + vo);
  		
  		List<QuestionVO> qeustionList = questionService.admingetQuestionList(vo);
  		
  		model.addAttribute("qeustionList", qeustionList); 
  		
  		return "admin/question/getQuestionList";
  	}
  	*/
	    
  	@RequestMapping("/get_question")
  	public String admingetBoard(@ModelAttribute("questionIdx") int questionIdx, Model model) {
  		System.out.println(">>> 문의글 상세 보여주기");
  		System.out.println("questionIdx : " + questionIdx);

  		QuestionVO question = questionService.admingetQuestion(questionIdx);
  		List<AnswerVO> answerList = questionService.admingetAnswer(questionIdx);
  		int answerCnt = questionService.adminanswerCnt(questionIdx);
  		
  		model.addAttribute("question", question); 
  		model.addAttribute("answerList", answerList); 
  		model.addAttribute("answerCnt", answerCnt);
  		System.out.println("question : " + question);
  		System.out.println("answerCnt : " + answerCnt);
  		return "admin/question/getQuestion";
  	}
  	
  	@RequestMapping("/insert_answer_view")
  	public String insertAnswerView() {
  		return "admin/question/getQuestion";
  	}

  	@RequestMapping("/insert_answer")
  	public String insertAnswer(AnswerVO vo, RedirectAttributes rttr) throws IllegalStateException, IOException {
  		System.out.println(">>> 답변 입력");
  		System.out.println("vo : " + vo);
  		
  		questionService.adminanswerinsert(vo);
  		rttr.addAttribute("questionIdx", vo.getQuestionIdx());
  		
  		return "redirect:get_question";
  	}
//예림 끝	
  	
	@GetMapping("/get_question_list")
	public String paging(Model model,
	 					@RequestParam(value = "page", required = false, defaultValue = "1") int page)  {
		
		System.out.println("page~~~~~~~~~~~~~~~~~~~~~~~ = " + page);
		
		
		List<QuestionVO> qeustionList = questionService.adminQuestionPaging(page);
		CommunityPageVO pageVO = questionService.adminQuestionPagingParam(page);
	    
		System.out.println("pageVO : " + pageVO);
		model.addAttribute("qeustionList", qeustionList);
		model.addAttribute("paging", pageVO);
		
		
		return "admin/question/getQuestionList";

	}
    
    
	
}
