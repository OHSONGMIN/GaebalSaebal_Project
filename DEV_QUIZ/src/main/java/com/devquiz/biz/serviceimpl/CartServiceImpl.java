package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.CartDAO;
import com.devquiz.biz.model.CartVO;
import com.devquiz.biz.service.CartService;

@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDAO cartDAO;
	
	public CartServiceImpl() {
		System.out.println(">>>> CartServiceImpl() 객체 생성");
	}

	@Override
	public int selectCart(CartVO vo) {
		return cartDAO.selectCart(vo);
	}

	@Override
	public void insertCart(CartVO vo) {
		cartDAO.insertCart(vo);
		
	}

	@Override
	public void deleteCart(CartVO vo) {
		cartDAO.deleteCart(vo);
		
	}

	@Override
	public List<CartVO> selectCartList(CartVO vo) {
		return cartDAO.selectCartList(vo);
	}

}
