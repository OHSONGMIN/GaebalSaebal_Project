package com.devquiz.biz.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.CommunityDAO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommentVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.service.CommunityService;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService{
	@Autowired
	private CommunityDAO communityDAO;
	
	public CommunityServiceImpl() {
		System.out.println(">> CommunityServiceImpl() 객체 생성");
	}
	
	@Override //게시글 작성
	public void insertCommunity(CommunityVO vo) {
		communityDAO.insertCommunity(vo);
	}
	
	@Override //게시글 상세
	public CommunityVO getCommunity(CommunityVO vo) {
		communityDAO.increaseHit(vo); //게시글 상세 보기-> 조회수 1 증가
		return communityDAO.getCommunity(vo);
	}
	
	@Override //게시글 삭제
	public void deleteCommunity(CommunityVO vo) {
		communityDAO.deleteCommunity(vo);
	}
	
	@Override //게시글 수정
	public void updateCommunity(CommunityVO vo) {
		communityDAO.updateCommunity(vo);
	}
	
	int pageLimit = 10; // 한 페이지당 보여줄 게시글 개수
	int blockLimit = 5; // 하단에 보여줄 한 블록당 페이지 개수
	
	@Override //전체 게시글 조회(페이징 처리)
	public List<CommunityVO> getCommunityPagingList(int page) {
		/*
		한 페이지당 보여지는 글 갯수 10
		 	1page => pageStart 0
		 	2page => pageStart 10
		 	3page => pageStart 20
		*/
		//해당 페이지의 start 게시글 번호
		int pageStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		List<CommunityVO> communityPagingList = communityDAO.getCommunityPagingList(pagingParams);
		
		return communityPagingList;
	}
	
	@Override //페이징 처리
	public CommunityPageVO communityPagingParam(int page) {
		// 전체 글 갯수 조회
        int boardCount = communityDAO.communityBoardCount();
        
        // 전체 페이지 갯수 계산(33/10=3.3 -> 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        
        // 시작 페이지 값 계산(1, 6, 11, 16, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        
        // 끝 페이지 값 계산(5, 10, 15, 20, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        
        // 이전 블록의 시작 페이지
        int blockPreStartPage = (((int)(Math.ceil((double) page / blockLimit))) - 2) * blockLimit + 1;
        
        // 다음 블록의 시작 페이지
        int blockNextStartPage = ((int)(Math.ceil((double) page / blockLimit))) * blockLimit + 1;
        		
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        vo.setBlockLimit(blockLimit);
        vo.setBlockPreStartPage(blockPreStartPage);
        vo.setBlockNextStartPage(blockNextStartPage);
        
        return vo;
	}
	
	@Override //해당 카테고리 게시글만 조회(페이징 처리) 
	public List<CommunityVO> getCommunityPagingListByCate(int cateIdx, int page) {
		int pageStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		pagingParams.put("cateIdx", cateIdx);
		List<CommunityVO> communityPagingList = communityDAO.getCommunityPagingListByCate(pagingParams);
		
		return communityPagingList;
	}
	
	@Override //해당 카테고리 게시글 페이징 처리
	public CommunityPageVO communityPagingParamByCate(int cateIdx, int page) {
		// 해당 카테고리 게시글 개수 조회
        int boardCount = communityDAO.communityBoardCountByCate(cateIdx);
        
        // 해당 카테고리 게시글 개수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        
        // 이전 블록의 시작 페이지
        int blockPreStartPage = (((int)(Math.ceil((double) page / blockLimit))) - 2) * blockLimit + 1;
        
        // 다음 블록의 시작 페이지
        int blockNextStartPage = ((int)(Math.ceil((double) page / blockLimit))) * blockLimit + 1;
        
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        vo.setBlockLimit(blockLimit);
        vo.setBlockPreStartPage(blockPreStartPage);
        vo.setBlockNextStartPage(blockNextStartPage);
        
        return vo;
	}
	
	@Override //키워드 검색 게시글 조회(페이징 처리)
	public List<CommunityVO> getCommunityPagingListByKeyword(CommunityVO vo, int page) {
		int pageStart = (page - 1) * pageLimit;

	    Map<String, Object> pagingParams = new HashMap();
	    pagingParams.put("start", pageStart);
	    pagingParams.put("limit", pageLimit);
	    pagingParams.put("searchCondition", vo.getSearchCondition());
	    pagingParams.put("searchKeyword", vo.getSearchKeyword());
	    
		List<CommunityVO> communityPagingList = communityDAO.getCommunityPagingListByKeyword(pagingParams);
		return communityPagingList;
	}
	
	@Override //TITLE 검색 페이징
	public CommunityPageVO communityPagingParamByKeywordTitle(int page, String searchKeyword) {
		// 전체 글 갯수 조회
        int boardCount = communityDAO.communityBoardCountByKeywordTitle(searchKeyword);
        
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        
        // 이전 블록의 시작 페이지
        int blockPreStartPage = (((int)(Math.ceil((double) page / blockLimit))) - 2) * blockLimit + 1;
        
        // 다음 블록의 시작 페이지
        int blockNextStartPage = ((int)(Math.ceil((double) page / blockLimit))) * blockLimit + 1;
        		
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        vo.setBlockLimit(blockLimit);
        vo.setBlockPreStartPage(blockPreStartPage);
        vo.setBlockNextStartPage(blockNextStartPage);
        vo.setBoardCount(boardCount);
        
        return vo;
	}
	
	@Override //CONTENT 검색 페이징
	public CommunityPageVO communityPagingParamByKeywordContent(int page, String searchKeyword) {
		// 전체 글 갯수 조회
        int boardCount = communityDAO.communityBoardCountByKeywordContent(searchKeyword);
        
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        
        // 이전 블록의 시작 페이지
        int blockPreStartPage = (((int)(Math.ceil((double) page / blockLimit))) - 2) * blockLimit + 1;
        
        // 다음 블록의 시작 페이지
        int blockNextStartPage = ((int)(Math.ceil((double) page / blockLimit))) * blockLimit + 1;
        		
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        vo.setBlockLimit(blockLimit);
        vo.setBlockPreStartPage(blockPreStartPage);
        vo.setBlockNextStartPage(blockNextStartPage);
        vo.setBoardCount(boardCount);
        
        return vo;
	}
	
}
