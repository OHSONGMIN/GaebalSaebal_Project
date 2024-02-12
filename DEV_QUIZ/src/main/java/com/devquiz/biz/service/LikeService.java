package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.LikeVO;

public interface LikeService {
	int selectLike(LikeVO vo);
	void insertLike(LikeVO vo);
	void deleteLike(LikeVO vo);
	List<LikeVO> selectLikeList(LikeVO vo);

}
