package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.OrderVO;

@Repository
public class OrderDAO {
	private SqlSessionTemplate mybatis;
	
	public OrderDAO() {
		System.out.println(">>> OrderDAO() 객체 생성");
	}
	
	@Autowired
	public OrderDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> OrderDAO(mybatis) 객체 생성");
		this.mybatis = mybatis;
	}
	
	// 결제
	public void insertOrder(OrderVO vo) {
		System.out.println("===> mybatis insertOrder() 실행");
		mybatis.insert("order.insertOrder", vo);
	}
	
	// orderIdx 필요해
	public int selectOrderIdx(OrderVO vo) {
		System.out.println("===> mybatis selectOrderIdx() 실행");
		return mybatis.selectOne("order.selectOrderIdx", vo.getMemberIdx());
	}
	
	// 주문 조회
	public List<OrderVO> selectOrder(OrderVO vo) {
		System.out.println("===> mybatis selectOrder() 실행");
		return mybatis.selectList("order.selectOrder", vo.getMemberIdx());
	}
	
	// 결제 취소
	public void updateOrder(OrderVO vo) {
		System.out.println("===> mybatis updateOrder() 실행");
		mybatis.update("order.updateOrder", vo);
	}

}
