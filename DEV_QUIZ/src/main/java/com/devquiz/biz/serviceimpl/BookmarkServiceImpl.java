package com.devquiz.biz.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.BookmarkDAO;
import com.devquiz.biz.model.BookmarkVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.service.BookmarkService;

@Service("bookmarkService")
public class BookmarkServiceImpl implements BookmarkService{
		@Autowired
		private BookmarkDAO bookmarkDAO;
		
		public BookmarkServiceImpl() {
			System.out.println(">> BookmarkServiceImpl() 객체 생성");
		}
		
		@Override //북마크 등록
		public void addBookmark(BookmarkVO vo) {
			bookmarkDAO.addBookmark(vo);
		}
		
		@Override //북마크 여부 조회
		public int getBookmarkStatus(BookmarkVO vo) {
			return bookmarkDAO.getBookmarkStatus(vo);
		}
		
		@Override //북마크 취소
		public void deleteBookmark(BookmarkVO vo) {
			bookmarkDAO.deleteBookmark(vo);
		}
		
		int pageLimit = 10; // 한 페이지당 보여줄 게시글 개수
		int blockLimit = 5; // 하단에 보여줄 한 블록당 페이지 개수
		
		@Override //북마크 게시글만 조회(페이징 처리)
		public List<CommunityVO> getCommunityPagingListByBookmark(int memberIdx, int page) {
			int pageStart = (page - 1) * pageLimit;
			Map<String, Integer> pagingParams = new HashMap();
			pagingParams.put("start", pageStart);
			pagingParams.put("limit", pageLimit);
			pagingParams.put("memberIdx", memberIdx);
			List<CommunityVO> communityPagingList = bookmarkDAO.getCommunityPagingListByBookmark(pagingParams);
			
			return communityPagingList;
		}
		
		@Override //북마크 게시글 페이징 처리
		public CommunityPageVO communityPagingParamByBookmark(int memberIdx, int page) {
			//북마크 게시글 개수 조회
			int boardCount = bookmarkDAO.communityBoardCountByBookmark(memberIdx);
			
			//북마크 게시글 개수 계산
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
}
