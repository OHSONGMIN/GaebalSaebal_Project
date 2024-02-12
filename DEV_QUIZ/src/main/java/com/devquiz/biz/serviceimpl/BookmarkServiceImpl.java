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
		
}
