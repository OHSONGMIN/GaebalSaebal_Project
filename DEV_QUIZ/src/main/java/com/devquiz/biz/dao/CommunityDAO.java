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
	
	//해당 카테고리 게시글만 조회(페이징 처리) : 오송민
	public List<CommunityVO> getCommunityPagingListByCate(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 getCommunityPagingListByCate() 실행");
		return mybatis.selectList("communityDAO.getCommunityPagingListByCate", pagingParams);
	}
	
	//해당 카테고리 게시글 개수 조회 : 오송민
	public int communityBoardCountByCate(int cateIdx) {
		System.out.println("===> MyBatis JDBC로 communityBoardCountByCate() 실행");
		return mybatis.selectOne("communityDAO.communityBoardCountByCate", cateIdx);
	}
	
	//게시판 카테고리 조회 : 오송민
	public List<CategoryVO> getCommunityCate() {
		System.out.println("===> MyBatis JDBC로 getCommunityCate() 실행");
		return mybatis.selectList("communityDAO.getCommunityCate");
	}
	
	//조회수 1 증가 : 오송민
	public void increaseHit(CommunityVO vo) {
		System.out.println("===> MyBatis JDBC로 increaseHit() 실행");
		mybatis.update("communityDAO.increaseHit", vo);
	}
	
	//검색 키워드 게시글 조회 : 오송민
	public List<CommunityVO> getCommunityPagingListByKeyword(Map<String, Object> pagingParams) {
		System.out.println("===> MyBatis JDBC로 getSearchCommunityList() 실행");
		
		String searchCondition = (String) pagingParams.get("searchCondition");
		String searchKeyword = (String) pagingParams.get("searchKeyword");
		
		// 검색조건 값이 없을 때 기본값 설정
		if (searchCondition == null) {
			pagingParams.put("searchCondition", "TITLE");
		}
		if (searchKeyword == null) { 
			pagingParams.put("searchKeyword", "");
		}
		
		String sql = "";
		if ("TITLE".equals((String) pagingParams.get("searchCondition"))) {
			sql = "communityDAO.getSearchCommunityList_T";
		} else if ("CONTENT".equals((String) pagingParams.get("searchCondition"))) {
			sql = "communityDAO.getSearchCommunityList_C";
		}
		
		return mybatis.selectList(sql, pagingParams);
	}
	
	//키워드 검색 게시글 개수 조회(TITLE)
	public int communityBoardCountByKeywordTitle(String searchKeyword) {
		System.out.println("===> MyBatis JDBC로 communityBoardCountByKeywordTitle() 실행");
		return mybatis.selectOne("communityDAO.communityBoardCountByKeywordTitle", searchKeyword);
	}
	
	//키워드 검색 게시글 개수 조회(CONTENT)
	public int communityBoardCountByKeywordContent(String searchKeyword) {
		System.out.println("===> MyBatis JDBC로 communityBoardCountByKeywordContent() 실행");
		return mybatis.selectOne("communityDAO.communityBoardCountByKeywordContent", searchKeyword);
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
	
	//selCateIdx에 맞는 cateName가져오기 : 오송민
	public String getSelCateName(int cateIdx) {
		System.out.println("===> MyBatis JDBC로 getSelCateName() 실행");
		return mybatis.selectOne("communityDAO.getSelCateName", cateIdx);
	}
}
