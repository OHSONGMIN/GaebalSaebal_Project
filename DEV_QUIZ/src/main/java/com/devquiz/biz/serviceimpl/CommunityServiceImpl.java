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
}
