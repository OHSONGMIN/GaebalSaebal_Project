package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.OrderdetailDAO;
import com.devquiz.biz.dao.ProductDAO;
import com.devquiz.biz.model.OrderDetailVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.service.OrderdetailService;
import com.devquiz.biz.service.ProductService;

@Service("orderdetailService")
public class OrderdetailServiceImpl implements OrderdetailService {
	
	@Autowired 
	private OrderdetailDAO orderdetailDAO;

	public OrderdetailServiceImpl() {
		System.out.println(">> OrderdetailServiceImpl() 객체 생성");
	}
//예림 시작
	public List<OrderDetailVO> adminorderList(OrderDetailVO vo) {
		return orderdetailDAO.adminorderList(vo);
	}
	@Override
	public int adminorderCnt(OrderDetailVO vo) {
		return orderdetailDAO.adminorderCnt(vo);
	}
	@Override
	public int adminorderDetailCnt(OrderDetailVO vo) {
		return orderdetailDAO.adminorderDetailCnt(vo);
	}
	@Override
	public List<OrderDetailVO> adminorderDetail(OrderDetailVO vo) {
		return orderdetailDAO.adminorderDetail(vo);
	}
	
//예림 끝
	
	// 민주 시작
	@Override
	public void insertOrderDetail(OrderDetailVO vo) {
		orderdetailDAO.insertOrderDetail(vo);
	}

	@Override
	public void updateOrderDetail(OrderDetailVO vo) {
		orderdetailDAO.updateOrderDetail(vo);
	}

	@Override
	public List<OrderDetailVO> selectOrderDetail(OrderDetailVO vo) {
		return orderdetailDAO.selectOrderDetail(vo);
	}

	@Override
	public List<OrderDetailVO> selectProducIdx(OrderDetailVO vo) {
		return orderdetailDAO.selectProducIdx(vo);
	}
	// 민주 끝
	

}
