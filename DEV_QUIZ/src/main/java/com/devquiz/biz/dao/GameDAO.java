package com.devquiz.biz.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.GameVO;

@Repository
public class GameDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
// (이하영 시작) ============================================================
	public List<GameVO> adminGameCateList(GameVO vo) {
		return mybatis.selectList("game.adminGameCateList", vo);
	}
	public List<GameVO> adminGetGame(GameVO vo) {
		return mybatis.selectList("game.adminGetGame", vo);
	}
	public GameVO adminGetGameOne(GameVO vo) {
		return mybatis.selectOne("game.adminGetGameOne", vo);
	}
	public void adminInsertGame(GameVO vo) {
		mybatis.insert("game.adminInsertGame", vo);
	}
	public void adminUpdateGame(GameVO vo) {
		mybatis.update("game.adminGameUpdate", vo);
	}
	public void adminDeleteGame(GameVO vo) {
		mybatis.delete("game.adminGameDelete", vo);
	}


	public List<GameVO> gaebalGetGameList(GameVO vo) {
		return mybatis.selectList("game.gaebalGetGameList", vo);
	}
	
// (이하영 끝) ============================================================	
}
