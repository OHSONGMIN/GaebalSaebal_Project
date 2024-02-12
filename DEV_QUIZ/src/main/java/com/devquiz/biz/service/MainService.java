package com.devquiz.biz.service;

import java.util.List;
import java.util.Map;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.ScoreVO;

public interface MainService {	

	void adminInsertCate(Map<String, String> paramMap);
	List<CategoryVO> adminGetCateAll(CategoryVO vo);
	List<CategoryVO> gaebalGetGameCate(CategoryVO vo);
	List<CategoryVO> gaebalGetQuestionCate(CategoryVO vo);
	List<CategoryVO> gaebalGetProductCate(CategoryVO vo);
	
	//List<CategoryVO> gaebalGetBoardCate(CategoryVO vo);
	
	void gaebalInsertPoint(ScoreVO vo);
	
	MemberVO gaebalGetMemPoint(MemberVO vo);
	
	List<BoardVO> getNotice();


	
}
