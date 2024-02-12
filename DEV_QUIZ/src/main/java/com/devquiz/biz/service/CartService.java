package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.CartVO;

public interface CartService {
	int selectCart(CartVO vo);
	void insertCart(CartVO vo);
	void deleteCart(CartVO vo);
	List<CartVO> selectCartList(CartVO vo);

}
