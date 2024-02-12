package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.MyProductDAO;
import com.devquiz.biz.model.MyProductVO;
import com.devquiz.biz.service.MyProductService;

@Service("myProductService")
public class MyProductServiceImpl implements MyProductService {
	@Autowired
	private MyProductDAO myProductDAO;
	
	public MyProductServiceImpl() {
		System.out.println(">>>> MyProductServiceImpl() 객체 생성");
	}

	@Override
	public void insertMyProduct(MyProductVO vo) {
		myProductDAO.insertMyProduct(vo);
	}

	@Override
	public void deleteMyProduct(MyProductVO vo) {
		myProductDAO.deleteMyProduct(vo);
	}

	@Override
	public List<MyProductVO> selectMyProduct(MyProductVO vo) {
		return myProductDAO.selectMyProduct(vo);
	}

	@Override
	public int selectMyProductOk(MyProductVO vo) {
		return myProductDAO.selectMyProductOk(vo);
	}
	
}
