package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.BookmarkVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;

public interface BookmarkService {

	void addBookmark(BookmarkVO vo); //북마크 등록
	int getBookmarkStatus(BookmarkVO vo); //북마크 여부 조회
	void deleteBookmark(BookmarkVO vo); //북마크 취소
	List<CommunityVO> getCommunityPagingListByBookmark(int memberIdx, int page); //북마크 게시글만 조회
	CommunityPageVO communityPagingParamByBookmark(int memberIdx, int page); //페이징 처리
	
}
