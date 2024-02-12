package com.devquiz.biz.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.service.BoardService;
import com.devquiz.biz.dao.BoardDAO;
import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CommunityPageVO;

// @Service : @Component 상속 확장 어노테이션
//		비즈니스 로직 처리 서비스 영역에 사용
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired //타입이 일치하는 객체(인스턴스) 주입
	private BoardDAO boardDAO;

	public BoardServiceImpl() {
		System.out.println(">> BoardServiceImpl() 객체 생성");
	}

//예림시작
	@Override
	public void admininsertBoard(BoardVO vo) {
		boardDAO.admininsertBoard(vo);
	}

	@Override
	public void adminupdateBoard(BoardVO vo) {
		boardDAO.adminupdateBoard(vo);
	}

	@Override
	public void admindeleteBoard(BoardVO vo) {
		boardDAO.admindeleteBoard(vo);
	}

	@Override
	public BoardVO admingetBoard(BoardVO vo) {
		return boardDAO.admingetBoard(vo);
	}

	@Override
	public List<BoardVO> admingetBoardList(BoardVO vo) {
		return boardDAO.admingetBoardList(vo);
	}
	
	@Override
	public List<BoardVO> admingetNoticeBoardList(BoardVO vo) {
		return boardDAO.admingetNoticeBoardList(vo);
	}	
	
	@Override
	public String admingetWriter(BoardVO vo) {
		return boardDAO.admingetWriter(vo);
	}
	
	@Override
	public String admingetCategory(BoardVO vo) {
		return boardDAO.admingetCategory(vo);
	}
	
	@Override
	public List<BoardVO> admingetList(int memberIdx) {
		return boardDAO.admingetList(memberIdx);
	}

	int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
	
	@Override //전체 게시글 페이징
	public List<BoardVO> adminBoardPaging(int page) {
		int pageStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		List<BoardVO> BoardPagingList = boardDAO.adminBoardPaging(pagingParams);
		
		return BoardPagingList;
	}

	@Override //공지사항만 페이징
	public List<BoardVO> adminNoticePaging(int page) {
		int pageStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap<String, Integer>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		List<BoardVO> NoticePagingList = boardDAO.adminNoticePaging(pagingParams);
		
		return NoticePagingList;
	}

	@Override
	public CommunityPageVO adminBoardPagingParam(int page) {
		// 전체 글 갯수 조회
        int boardCount = boardDAO.adminBoardCount();
        // 전체 페이지 갯수 계산(22/10=2.2=> 3)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 11, 21, 31, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(10, 20, 30, 40, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        return vo;
	}

	@Override
	public CommunityPageVO adminNoticePagingParam(int page) {
		// 전체 글 갯수 조회
        int noticeCount = boardDAO.adminNoticeCount();
        System.out.println("noticeCount : " + noticeCount);
        // 전체 페이지 갯수 계산(22/10=2.2=> 3)
        int maxPage = (int) (Math.ceil((double) noticeCount / pageLimit));
        // 시작 페이지 값 계산(1, 11, 21, 31, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(10, 20, 30, 40, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        return vo;
	}

//예림 끝


}
