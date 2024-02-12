package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.GameVO;

public interface GameService {

// [이하영] 시작 ============================================================
	List<GameVO> adminGameCateList(GameVO vo);
	List<GameVO> adminGetGame(GameVO vo);
	GameVO adminGetGameOne(GameVO vo);
	void adminInsertGame(GameVO vo);
	void adminUpdateGame(GameVO vo);
	void adminDeleteGame(GameVO vo);
	

	
	List<GameVO> gaebalGetGameList(GameVO vo);
	
// [이하영] 끝 ============================================================
	
	
}
