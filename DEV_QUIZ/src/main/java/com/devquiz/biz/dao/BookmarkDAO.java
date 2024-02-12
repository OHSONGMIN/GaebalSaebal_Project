package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.BookmarkVO;
import com.devquiz.biz.model.CommunityVO;

@Repository
public class BookmarkDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public BookmarkDAO() {
		System.out.println(">>> BookmarkDAO() 객체 생성");
	}
	
	public BookmarkDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> BookmarkDAO() 객체 생성");
	}

	//북마크 등록 : 오송민
	public void addBookmark(BookmarkVO vo) {
		System.out.println("===> MyBatis JDBC로 addBookmark() 실행");
		mybatis.insert("bookmarkDAO.addBookmark", vo);
	}
	
	//북마크 여부 확인 : 오송민
	public int getBookmarkStatus(BookmarkVO vo) {
		System.out.println("===> MyBatis JDBC로 getBookmarkStatus() 실행");
		return mybatis.selectOne("bookmarkDAO.getBookmarkStatus", vo);
	}
	
	//북마크 삭제 : 오송민
	public void deleteBookmark(BookmarkVO vo) {
		System.out.println("===> MyBatis JDBC로 deleteBookmark() 실행");
		mybatis.delete("bookmarkDAO.deleteBookmark", vo);
	}
	
	//북마크 게시글만 조회(페이징 처리) : 오송민
	public List<CommunityVO> getCommunityPagingListByBookmark(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 getCommunityPagingListByBookmark() 실행");
		return mybatis.selectList("bookmarkDAO.getCommunityPagingListByBookmark", pagingParams);
	}
	
	//북마크 게시글 개수 조회 : 오송민
	public int communityBoardCountByBookmark(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 communityBoardCountByBookmark() 실행");
		return mybatis.selectOne("bookmarkDAO.communityBoardCountByBookmark", memberIdx);
	}
}
