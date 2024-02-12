package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.LikeDAO;
import com.devquiz.biz.model.LikeVO;
import com.devquiz.biz.service.LikeService;

@Service("likeService")
public class LikeServiceImpl implements LikeService {
	@Autowired
	private LikeDAO likeDAO;
	
	public LikeServiceImpl() {
		System.out.println(">>>> LikeServiceImpl() 객체 생성");
	}

	@Override
	public int selectLike(LikeVO vo) {
		return likeDAO.selectLike(vo);
	}

	@Override
	public void insertLike(LikeVO vo) {
		likeDAO.insertLike(vo);
		
	}

	@Override
	public void deleteLike(LikeVO vo) {
		likeDAO.deleteLike(vo);
		
	}

	@Override
	public List<LikeVO> selectLikeList(LikeVO vo) {
		return likeDAO.selectLikeList(vo);
	}

}
