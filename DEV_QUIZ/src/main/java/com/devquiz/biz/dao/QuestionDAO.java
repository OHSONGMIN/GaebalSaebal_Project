package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.AnswerVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.model.QuestionVO;

@Repository
public class QuestionDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public QuestionDAO() {
		System.out.println("QuestionDAO() 객체 생성됨 됨 됨 됨 됨 됨 됨 됨 됨 됨 ");
	}
	// (이하영 시작) ============================================================	
	// [이하영] [gaebal] : 회원이 문의 게시판에 글을 입력할 때 사용하는 메소드.
	public void gaebalQuestionInsert(QuestionVO vo) {
		System.out.println("gaebalQuestionInsert(QuestionVO vo) 갈겨지는 중");
		mybatis.insert("question.gaebalQuestionInsert", vo);;
	}
	// [이하영] [gaebal] : 회원이 문의 게시판의 글 내용을 수정할 때 사용하는 메소드.
	public void gaebalQuestionUpdate(QuestionVO vo) {
		mybatis.update("question.gaebalQuestionUpdate", vo);
	}
	// [이하영] [gaebal] : 회원이 문의 게시판의 글을 삭제할 때 사용하는 메소드.
	public void gaebalQuestionDelete(QuestionVO vo) {
		mybatis.delete("question.gaebalQuestionDelete", vo);
	}
	// [이하영] [gaebal] : 문의 게시판 전체 목록 조회
	public List<QuestionVO> gaebalQuestionAllList(QuestionVO vo) {
		return mybatis.selectList("question.gaebalQuestionAllList", vo);
	}
	// [이하영] [gaebal] : 문의 게시판에서 검색해서 목록 조회
	public List<QuestionVO> gaebalQuestionList(QuestionVO vo) {
		
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("NICKNAME");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		String sql = "";
		
		if ("NICKNAME".equals(vo.getSearchCondition())) {
			sql = "question.gaebalQuestionNickname";
		} else if ("QUESTION_TITLE".equals(vo.getSearchCondition())) {
			sql = "question.gaebalQuestionTitle";
		}
		return mybatis.selectList(sql, vo.getSearchKeyword());
	}
	// [이하영] [gaebal] : 문의글 상세보기
	public QuestionVO gaebalQuestion(QuestionVO vo) {
		return mybatis.selectOne("question.gaebalQuestion", vo);
	}
	// [이하영] [gaebal] : 문의글 작성시, 카테고리 대분류.
	public List<QuestionVO> gaebalGetProdsMainCate(QuestionVO vo) {
		return mybatis.selectList("question.gaebalGetprodsMainCate", vo);
	}
	// [이하영] [gaebal] : 문의글 작성시, 상품 대분류
	public List<QuestionVO> gaebalGetProdsCate(QuestionVO vo) {
		return mybatis.selectList("question.gaebalGetProdsCate", vo);
	}
	// [이하영] [gaebal] : 문의글 작성시, 상품 이름
	public List<ProductVO> gaebalGetProdsName(ProductVO vo) {
		return mybatis.selectList("question.gaebalGetProdsName", vo);
	}
// (이하영 끝) ============================================================	
	
	
//예림 시작
	//문의글 목록 조회
	public List<QuestionVO> admingetQuestionList(QuestionVO vo) {
		System.out.println("===> MyBatis JDBC로 admingetQuestionList() 실행");
		return mybatis.selectList("question.admingetQuestionList", vo);
	}
		
	//문의글 조회
	public QuestionVO admingetQuestion(int questionIdx) {
		System.out.println("===> MyBatis JDBC로 admingetQuestion() 실행");
		return mybatis.selectOne("question.admingetQuestion", questionIdx);
	}
	//문의 답변 조회
	public List<AnswerVO> admingetAnswer(int questionIdx) {
		System.out.println("===> MyBatis JDBC로 admingetAnswer() 실행");
		return mybatis.selectList("question.admingetAnswer", questionIdx);
	}
	//문의 답변 수
	public int adminanswerCnt(int questionIdx) {
		System.out.println("===> MyBatis JDBC로 adminanswerCnt() 실행");
		return mybatis.selectOne("question.adminanswerCnt", questionIdx);
	}
		
	public void adminanswerinsert(AnswerVO vo) {
		System.out.println("===> MyBatis JDBC로 adminanswerinsert() 실행");
		mybatis.insert("question.adminanswerinsert", vo);
	}
//예림 끝
	
//예림 수정 23.11.28
	//문의 조회(페이징 처리)
	public List<QuestionVO> adminQuestionPaging(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 adminQuestionPaging() 실행");
		return mybatis.selectList("question.adminQuestionPaging", pagingParams);
	}
	
	//문의 개수 조회
	public int adminQuestionCount() {
		System.out.println("===> MyBatis JDBC로 adminQuestionCount() 실행");
		return mybatis.selectOne("question.adminQuestionCount");
	}
//예림 끝	
	
}
