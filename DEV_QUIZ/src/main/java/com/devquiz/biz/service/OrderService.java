package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.OrderVO;

public interface OrderService {
	void insertOrder(OrderVO vo);
	int selectOrderIdx(OrderVO vo);
	List<OrderVO> selectOrder(OrderVO vo);
	void updateOrder(OrderVO vo);
}
