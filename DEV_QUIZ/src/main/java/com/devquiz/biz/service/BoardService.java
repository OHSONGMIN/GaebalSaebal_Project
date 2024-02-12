package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CommunityPageVO;

public interface BoardService {
//	//CRUD 기능 구현 메소드 정의
//	void insertBoard(BoardVO vo);
//	void updateBoard(BoardVO vo);
//	void deleteBoard(BoardVO vo);
//	BoardVO getBoard(BoardVO vo);
//	String getWriter(BoardVO vo);
//	String getCategory(BoardVO vo);
//	List<BoardVO> getBoardList(BoardVO vo);
//	List<BoardVO> getNoticeBoardList(BoardVO vo);	
//	
	
//예림시작
	void admininsertBoard(BoardVO vo);
	void adminupdateBoard(BoardVO vo);
	void admindeleteBoard(BoardVO vo);
	BoardVO admingetBoard(BoardVO vo);
	String admingetWriter(BoardVO vo);
	String admingetCategory(BoardVO vo);
	List<BoardVO> admingetBoardList(BoardVO vo);
	List<BoardVO> admingetNoticeBoardList(BoardVO vo);	
	List<BoardVO> admingetList(int memberIdx);	
	
	List<BoardVO> adminBoardPaging(int page); //커뮤니티 게시글 조회(페이징처리)
	List<BoardVO> adminNoticePaging(int page); //공지사항만 조회(페이징처리)
	CommunityPageVO adminBoardPagingParam(int page);
	CommunityPageVO adminNoticePagingParam(int page);
//예림끝
	
}
