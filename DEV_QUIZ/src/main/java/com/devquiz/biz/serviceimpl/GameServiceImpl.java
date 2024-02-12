package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.GameDAO;
import com.devquiz.biz.model.GameVO;
import com.devquiz.biz.service.GameService;

@Service("gameService")
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameDAO gameDAO;
// (이하영 시작) ============================================================
	@Override
	public List<GameVO> adminGameCateList(GameVO vo) {
		return gameDAO.adminGameCateList(vo);
	}
	@Override
	public List<GameVO> adminGetGame(GameVO vo) {
		return gameDAO.adminGetGame(vo);
	}
	@Override
	public GameVO adminGetGameOne(GameVO vo) {
		return gameDAO.adminGetGameOne(vo);
	}
	@Override
	public void adminInsertGame(GameVO vo) {
		gameDAO.adminInsertGame(vo);
	}
	@Override
	public void adminUpdateGame(GameVO vo) {
		gameDAO.adminUpdateGame(vo);
	}
	@Override
	public void adminDeleteGame(GameVO vo) {
		gameDAO.adminDeleteGame(vo);
		
	}

	@Override
	public List<GameVO> gaebalGetGameList(GameVO vo) {
		return gameDAO.gaebalGetGameList(vo);
	}
// (이하영 끝) ============================================================

}
