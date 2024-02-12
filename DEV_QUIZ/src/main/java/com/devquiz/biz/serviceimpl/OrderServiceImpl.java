package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.OrderDAO;
import com.devquiz.biz.model.OrderVO;
import com.devquiz.biz.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	
	public OrderServiceImpl() {
		System.out.println(">>>> OrderServiceImpl() 객체 생성");
	}

	@Override
	public void insertOrder(OrderVO vo) {
		orderDAO.insertOrder(vo);
	}

	@Override
	public void updateOrder(OrderVO vo) {
		orderDAO.updateOrder(vo);
	}

	@Override
	public int selectOrderIdx(OrderVO vo) {
		return orderDAO.selectOrderIdx(vo);
	}

	@Override
	public List<OrderVO> selectOrder(OrderVO vo) {
		return orderDAO.selectOrder(vo);
	}

}
