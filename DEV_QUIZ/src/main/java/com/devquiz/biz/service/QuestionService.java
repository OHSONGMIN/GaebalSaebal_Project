package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.AnswerVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.model.QuestionVO;

public interface QuestionService {
	// (이하영 시작) ============================================================
	void gaebalQuestionInsert(QuestionVO vo);

	void gaebalQuestionUpdate(QuestionVO vo);

	void gaebalQuestionDelete(QuestionVO vo);

	List<QuestionVO> gaebalQuestionAllList(QuestionVO vo);

	List<QuestionVO> gaebalQuestionList(QuestionVO vo);

	QuestionVO gaebalQuestion(QuestionVO vo);

	List<QuestionVO> gaebalGetProdsMainCate(QuestionVO vo);

	List<ProductVO> gaebalGetProdsName(ProductVO vo);

	List<QuestionVO> gaebalGetProdsCate(QuestionVO vo);

	// (이하영 끝) ============================================================

//예림 시작
	List<QuestionVO> admingetQuestionList(QuestionVO vo);

	QuestionVO admingetQuestion(int questionIdx);

	List<AnswerVO> admingetAnswer(int questionIdx);

	int adminanswerCnt(int questionIdx);

	void adminanswerinsert(AnswerVO vo);

	// 예림 추가 11.28
	List<QuestionVO> adminQuestionPaging(int page);

	CommunityPageVO adminQuestionPagingParam(int page);
	// 예림 끝
//예림 끝

}
