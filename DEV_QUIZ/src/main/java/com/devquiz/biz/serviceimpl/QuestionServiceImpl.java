package com.devquiz.biz.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devquiz.biz.service.QuestionService;
import com.devquiz.biz.dao.QuestionDAO;
import com.devquiz.biz.model.AnswerVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.model.QuestionVO;

// @Service : @Component 상속 확장 어노테이션
//		비즈니스 로직 처리 서비스 영역에 사용
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
	@Autowired //타입이 일치하는 객체(인스턴스) 주입
	private QuestionDAO questionDAO;
	
	// (이하영 시작) ============================================================
	@Override
	public void gaebalQuestionInsert(QuestionVO vo) {
		questionDAO.gaebalQuestionInsert(vo);;
	}

	@Override
	public void gaebalQuestionUpdate(QuestionVO vo) {
		questionDAO.gaebalQuestionUpdate(vo);
	}

	@Override
	public void gaebalQuestionDelete(QuestionVO vo) {
		questionDAO.gaebalQuestionDelete(vo);
	}

	@Override
	public List<QuestionVO> gaebalQuestionAllList(QuestionVO vo) {
		return questionDAO.gaebalQuestionAllList(vo);
	}

	@Override
	public List<QuestionVO> gaebalQuestionList(QuestionVO vo) {
		return questionDAO.gaebalQuestionList(vo);
	}

	@Override
	public QuestionVO gaebalQuestion(QuestionVO vo) {
		return questionDAO.gaebalQuestion(vo);
	}
	
	@Override
	public List<ProductVO> gaebalGetProdsName(ProductVO vo) {
		return questionDAO.gaebalGetProdsName(vo);
	}
	
	@Override
	public List<QuestionVO> gaebalGetProdsMainCate(QuestionVO vo) {
		return questionDAO.gaebalGetProdsMainCate(vo);
	}
	
	@Override
	public List<QuestionVO> gaebalGetProdsCate(QuestionVO vo) {
		return questionDAO.gaebalGetProdsCate(vo);
	}
// (이하영 끝) ============================================================
	
//예림 시작
	@Override
	public List<QuestionVO> admingetQuestionList(QuestionVO vo) {
		return questionDAO.admingetQuestionList(vo);
	}
	@Override
	public QuestionVO admingetQuestion(int questionIdx) {
		return questionDAO.admingetQuestion(questionIdx);
	}
	
	@Override
	public List<AnswerVO> admingetAnswer(int questionIdx) {
		return questionDAO.admingetAnswer(questionIdx);
	}
	@Override
	public int adminanswerCnt(int questionIdx) {
		return questionDAO.adminanswerCnt(questionIdx);
	}
	@Override
	public void adminanswerinsert(AnswerVO vo) {
		questionDAO.adminanswerinsert(vo);
	}
//예림 끝
		
//예림 11.28
	int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
	
	@Override
	public List<QuestionVO> adminQuestionPaging(int page) {
		int pageStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		List<QuestionVO> QuestionPagingList = questionDAO.adminQuestionPaging(pagingParams);
		
		return QuestionPagingList;
	}

	@Override
	public CommunityPageVO adminQuestionPagingParam(int page) {
		// 전체 글 갯수 조회
        int questionCount = questionDAO.adminQuestionCount();
        // 전체 페이지 갯수 계산(22/10=2.2=> 3)
        int maxPage = (int) (Math.ceil((double) questionCount / pageLimit));
        // 시작 페이지 값 계산(1, 11, 21, 31, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(10, 20, 30, 40, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        return vo;
	}

	//예림 끝	
	

}	
