package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.OrderDetailVO;
import com.devquiz.biz.model.ProductVO;

public interface OrderdetailService {
//예림 시작
	List<OrderDetailVO> adminorderList(OrderDetailVO vo);
	int adminorderCnt(OrderDetailVO vo);
	int adminorderDetailCnt(OrderDetailVO vo);
	List<OrderDetailVO> adminorderDetail(OrderDetailVO vo);
//예림 끝

	// 민주 시작
	void insertOrderDetail(OrderDetailVO vo);

	List<OrderDetailVO> selectOrderDetail(OrderDetailVO vo);

	void updateOrderDetail(OrderDetailVO vo);

	List<OrderDetailVO> selectProducIdx(OrderDetailVO vo);
	// 민주 끝
	
}
