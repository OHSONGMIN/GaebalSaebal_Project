package com.devquiz.biz.service;


import java.util.List;

import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommentVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;

public interface CommunityService {

	//CRUD 기능 구현 메소드 정의
	//List<CommunityVO> getCommunityList(CommunityVO vo); //전체 게시글 조회(페이징X)
	CommunityVO getCommunity(CommunityVO vo);
	void deleteCommunity(CommunityVO vo);
	void updateCommunity(CommunityVO vo);
	void insertCommunity(CommunityVO vo);
	List<CommentVO> getCommentList(CommunityVO vo);
	void insertComment(CommentVO vo);
	void deleteComment(CommentVO vo);
	void updateComment(CommentVO vo);
	List<CommunityVO> getCommunityPagingList(int page); //전체 게시글 조회(페이징처리)
	List<CommunityVO> getCommunityPagingListByCate(int cateIdx, int page); //해당 카테고리 게시글만 조회
	CommunityPageVO communityPagingParam(int page);
	CommunityPageVO communityPagingParamByCate(int cateIdx, int page);
	List<CategoryVO> getCommunityCate();
	String getSelCateName(int cateIdx);
	void deleteCommunityImg(int boardIdx);
	List<CommunityVO> getHotCommunityList();
	List<CommunityVO> getCommunityPagingListByKeyword(CommunityVO vo, int page); //키워드 검색 게시글 조회
	CommunityPageVO communityPagingParamByKeywordTitle(int page, String searchKeyword); //TITLE 검색 페이징
	CommunityPageVO communityPagingParamByKeywordContent(int page, String searchKeyword); //CONTENT 검색 페이징
	
}