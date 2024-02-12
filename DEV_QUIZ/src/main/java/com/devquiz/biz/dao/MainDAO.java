package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.ScoreVO;

@Repository
public class MainDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// [이하영] 관리자가 카테고리 목록을 추가하기 위한 메소드
	public void adminInsertCate(Map<String, String> map) {
		System.out.println("===> MyBatis - insertCate() 실행");
		mybatis.insert("main.adminInsertCate", map);
	}
	
	// [이하영] 관리자가 카테고리 목록을 조회하기 위한 메소드
	public List<CategoryVO> adminGetCateAll(CategoryVO vo) {
		return mybatis.selectList("main.adminGetCateAll", vo);
	}
	
	// [이하영] 회원 테이블의 포인트와 점수를 추가하기 위한 메소드
	public void gaebalInsertPoint(ScoreVO vo) {
		mybatis.insert("main.gaebalInsertPoint", vo);	
	}
	
	// [이하영] 회원이 게임 카테고리를 선택(조회)하기 위한 메소드
	public List<CategoryVO> gaebalGetGameCate(CategoryVO vo) {
		return mybatis.selectList("main.gaebalGetGameCate", vo);
	}
	
	// [이하영] 회원이 문의 카테고리를 선택(조회)하기 위한 메소드
	public List<CategoryVO> gaebalGetQuestionCate(CategoryVO vo) {
		return mybatis.selectList("main.gaebalGetQuestionCate", vo);
	}
	
	// [이하영] 회원의 포인트를 실시간으로 조회하기 위한 메소드
	public MemberVO gaebalGetMemPoint(MemberVO vo) {
		return mybatis.selectOne("main.getMemPoint", vo);
	}
	
	// [박효정] 회원이 상품 카테고리를 선택(조회)하기 위한 메소드
	public List<CategoryVO> gaebalGetProductCate(CategoryVO vo) {
		return mybatis.selectList("main.gaebalGetProductCate", vo);
	}
	//김지홍 : 공지사항 불러오기
	public List<BoardVO> getNotice(){
		return mybatis.selectList("main.getNotice");
	}
	
}
