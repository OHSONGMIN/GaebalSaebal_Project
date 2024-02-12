package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.OrderDetailVO;
import com.devquiz.biz.model.ProductVO;

@Repository
public class OrderdetailDAO {

	// @Autowired
	private SqlSessionTemplate mybatis;

	public OrderdetailDAO() {
		System.out.println(">>> OrderdetailDAO() 객체 생성");
	}

	@Autowired
	public OrderdetailDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> OrderdetailDAO(SqlSessionTemplate) 객체 생성");
		this.mybatis = mybatis;
	}
//예림 시작
	public List<OrderDetailVO> adminorderList(OrderDetailVO vo) {
		System.out.println("===> MyBatis JDBC로 adminorderList() 실행");
		return mybatis.selectList("orderdetailDAO.adminorderList", vo);
	}
	
	public int adminorderCnt(OrderDetailVO vo) {
		System.out.println("===> MyBatis JDBC로 adminorderCnt() 실행");
		return mybatis.selectOne("orderdetailDAO.adminorderCnt", vo);
	}
	
	public int adminorderDetailCnt(OrderDetailVO vo) {
		System.out.println("===> MyBatis JDBC로 adminorderDetailCnt() 실행");
		return mybatis.selectOne("orderdetailDAO.adminorderDetailCnt", vo);
	}
	
	public List<OrderDetailVO> adminorderDetail(OrderDetailVO vo) {
		System.out.println("===> MyBatis JDBC로 adminorderDetail() 실행");
		return mybatis.selectList("orderdetailDAO.adminorderDetail", vo);
	}
//예림 끝
	
	
	// 민주 시작
	public void insertOrderDetail(OrderDetailVO vo) {
		System.out.println("===> mybaits insertOrderDetail() 실행");
		mybatis.insert("orderdetailDAO.insertOrderDetail", vo);
	}

	public void updateOrderDetail(OrderDetailVO vo) {
		System.out.println("===> mybaits updateOrderDetail() 실행");
		mybatis.insert("orderdetailDAO.updateOrderDetail", vo);
	}

	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO vo) {
		System.out.println("===> mybaits selectOrderDetail() 실행");
		return mybatis.selectList("orderdetailDAO.selectOrderDetail", vo);
	}

	public List<OrderDetailVO> selectProducIdx(OrderDetailVO vo) {
		System.out.println("===> mybaits selctProducIdx() 실행");
		return mybatis.selectList("orderdetailDAO.selectProducIdx", vo);
	}
	// 민주 끝

	
}
