package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.BoardVO;

@Repository
public class BoardDAO {
	//@Autowired
	private SqlSessionTemplate mybatis;
	
	public BoardDAO() {
		System.out.println(">>> BoardDAO() 객체 생성");
	}
	@Autowired
	public BoardDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> BoardDAO(SqlSessionTemplate) 객체 생성");
		this.mybatis = mybatis;
	}
	
//예림시작	
	//작성자 닉네임 조회
	public String admingetWriter(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 admingetwriter() 실행");
		return mybatis.selectOne("boardDAO.admingetWriter", vo);
	}
	
	//카테고리 조회
	public String admingetCategory(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로admin getCategory() 실행");
		return mybatis.selectOne("boardDAO.admingetCategory", vo);
	}
	
	//글입력
	public void admininsertBoard(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 admininsertBoard() 실행");
		mybatis.insert("boardDAO.admininsertBoard", vo);
	}

	//글수정
	public void adminupdateBoard(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 adminupdateBoard() 실행");
		mybatis.update("boardDAO.adminupdateBoard", vo);
	}

	//글삭제
	public void admindeleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 admindeleteBoard() 실행");
		mybatis.delete("boardDAO.admindeleteBoard", vo);
	}

	//게시글 1개 조회
	public BoardVO admingetBoard(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 admingetBoard() 실행");
		return mybatis.selectOne("boardDAO.admingetBoard", vo);
	}

	//커뮤니티 게시글 조회
	public List<BoardVO> admingetBoardList() {
		System.out.println("===> MyBatis JDBC로 admingetBoardList() 실행");
		return null;
	}
	
	//커뮤니티 게시글 조회
	public List<BoardVO> admingetBoardList(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 admingetBoardList() 실행");
		// 검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		String sql = "";
		if ("TITLE".equals(vo.getSearchCondition())) {
			sql = "boardDAO.admingetBoardList_T";
		} else if ("CONTENT".equals(vo.getSearchCondition())) {
			sql = "boardDAO.admingetBoardList_C";
		}
		
		return mybatis.selectList(sql, vo.getSearchKeyword());
	}

	//공지사항 게시글 조회
	public List<BoardVO> admingetNoticeBoardList(BoardVO vo) {
		System.out.println("===> MyBatis JDBC로 admingetNoticeBoardList() 실행");
		// 검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		String sql = "";
		if ("TITLE".equals(vo.getSearchCondition())) {
			sql = "boardDAO.admingetNoticeBoardList_T";
		} else if ("CONTENT".equals(vo.getSearchCondition())) {
			sql = "boardDAO.admingetNoticeBoardList_C";
		}
		
		return mybatis.selectList(sql, vo.getSearchKeyword());
	}
	
	//아이디로 게시글 내역 조회
	public List<BoardVO> admingetList(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 admingetList() 실행");
		return mybatis.selectList("boardDAO.admingetList", memberIdx);
	}
	
	//커뮤니티 게시글 조회(페이징 처리)
	public List<BoardVO> adminBoardPaging(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 adminBoardPaging() 실행");
		return mybatis.selectList("boardDAO.adminBoardPaging", pagingParams);
	}
	
	//공지사항만 조회(페이징 처리)
	public List<BoardVO> adminNoticePaging(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 adminNoticePaging() 실행");
		return mybatis.selectList("boardDAO.adminNoticePaging", pagingParams);
	}
	
	//게시글 개수 조회
	public int adminBoardCount() {
		System.out.println("===> MyBatis JDBC로 adminBoardCount() 실행");
		return mybatis.selectOne("boardDAO.adminBoardCount");
	}
	
	//공지사항 개수 조회
	public int adminNoticeCount() {
		System.out.println("===> MyBatis JDBC로 adminNoticeCount() 실행");
		return mybatis.selectOne("boardDAO.adminNoticeCount");
	}
	
}
