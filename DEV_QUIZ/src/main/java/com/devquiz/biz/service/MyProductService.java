package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.MyProductVO;

public interface MyProductService {
	void insertMyProduct(MyProductVO vo);
	void deleteMyProduct(MyProductVO vo);
	List<MyProductVO> selectMyProduct(MyProductVO vo);
	int selectMyProductOk(MyProductVO vo);
}
