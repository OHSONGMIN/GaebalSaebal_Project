package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.LikeVO;

@Repository
public class LikeDAO {
	// @Autowired
	private SqlSessionTemplate mybatis;
	
	public LikeDAO() {
		System.out.println(">>> LikeDAO() 객체 생성");
	}
	
	@Autowired
	public LikeDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> LikeDAO(SqlSessionTemplate mybatis) 객체 생성");
		this.mybatis = mybatis;
	}
	
	// 찜 등록 여부 조회
	public int selectLike(LikeVO vo) {
		System.out.println("===> mybatis selectLike() 실행");
		return mybatis.selectOne("like.selectLike", vo);
	}
	
	// 찜 등록
	public void insertLike(LikeVO vo) {
		System.out.println("===> mybatis insertLike() 실행");
		mybatis.insert("like.insertLike", vo);
	}
	
	// 찜 삭제
	public void deleteLike(LikeVO vo) {
		System.out.println("===> mybatis deleteLike() 실행");
		mybatis.delete("like.deleteLike", vo);
	}
	
	// 찜 리스트 조회
	public List<LikeVO> selectLikeList(LikeVO vo) {
		System.out.println("===> mybatis selectLikeList() 실행");
		return mybatis.selectList("like.selectLikeList", vo.getMemberIdx());
	}
	
}
