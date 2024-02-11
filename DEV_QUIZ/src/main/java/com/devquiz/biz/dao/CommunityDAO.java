package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommentVO;
import com.devquiz.biz.model.CommunityVO;

@Repository
public class CommunityDAO {
	
	/* Mybatis와 Spring 연동 */
	/* @Autowired 어노테이션을 통해 SqlSessionTemplate 객체를 주입, SqlSessionTemplate 객체는 MyBatis를 사용하여 데이터베이스에 대한 SQL 세션을 관리 */
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public CommunityDAO() {
		System.out.println(">>> CommunityDAO() 객체 생성");
	}
	
	public CommunityDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> CommunityDAO() 객체 생성");
	}
	
	//게시글 작성 : 오송민
	public void insertCommunity(CommunityVO vo) {
		System.out.println("===> MyBatis JDBC로 insertCommunity() 실행");
		mybatis.insert("communityDAO.insertCommunity", vo);
	}
	
	//게시글 1개 조회 : 오송민
	public CommunityVO getCommunity(CommunityVO vo) {
		System.out.println("===> MyBatis JDBC로 getCommunity() 실행");
		return mybatis.selectOne("communityDAO.getCommunity", vo);
	}

	//전체 게시글 조회(페이징 처리) : 오송민
	public List<CommunityVO> getCommunityPagingList(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 getCommunityPagingList() 실행");
		return mybatis.selectList("communityDAO.getCommunityPagingList", pagingParams);
	}
		
	//전체 게시글 개수 조회 : 오송민
	public int communityBoardCount() {
		System.out.println("===> MyBatis JDBC로 communityBoardCount() 실행");
		return mybatis.selectOne("communityDAO.communityBoardCount");
	}
	
	//조회수 1 증가 : 오송민
	public void increaseHit(CommunityVO vo) {
		System.out.println("===> MyBatis JDBC로 increaseHit() 실행");
		mybatis.update("communityDAO.increaseHit", vo);
	}
	
	//게시글 삭제 : 오송민
	public void deleteCommunity(CommunityVO vo) {
		System.out.println("===> MyBatis JDBC로 deleteCommunity() 실행");
		mybatis.delete("communityDAO.deleteCommunity", vo);
	}
	
	//게시글 수정 : 오송민
	public void updateCommunity(CommunityVO vo) {
		System.out.println("===> MyBatis JDBC로 updateCommunity() 실행");
		mybatis.update("communityDAO.updateCommunity", vo);
	}
}
