package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.CartVO;

@Repository
public class CartDAO {
	private SqlSessionTemplate mybatis;
	
	public CartDAO() {
		System.out.println(">>> CartDAO() 객체 생성");
	}
	
	@Autowired
	public CartDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> CartDAO(mybatis) 객체 생성");
		this.mybatis = mybatis;
	}
	
	// 장바구니 등록 여부 조회
	public int selectCart(CartVO vo) {
		System.out.println("===> mybatis selectCart() 실행");
		return mybatis.selectOne("cart.selectCart", vo);
	}
	
	// 장바구니 등록
	public void insertCart(CartVO vo) {
		System.out.println("===> mybatis insertCart() 실행");
		mybatis.insert("cart.insertCart", vo);
	}
	
	// 장바구니 삭제
	public void deleteCart(CartVO vo) {
		System.out.println("===> mybatis deleteCart() 실행");
		mybatis.delete("cart.deleteCart", vo);
	}
	
	// 장바구니 리스트 조회
	public List<CartVO> selectCartList(CartVO vo) {
		System.out.println("===> mybatis selectCartList() 실행");
		return mybatis.selectList("cart.selectCartList", vo.getMemberIdx());
	}
	
	

}
