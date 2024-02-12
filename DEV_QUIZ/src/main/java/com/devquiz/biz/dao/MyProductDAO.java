package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.MyProductVO;

@Repository
public class MyProductDAO {
	private SqlSessionTemplate mybatis;
	
	public MyProductDAO() {
		System.out.println(">>> MyProductDAO() 객체 생성");
	}
	
	@Autowired
	public MyProductDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> MyProductDAO(mybatis) 객체 생성");
		this.mybatis = mybatis;
	}
	
	// 결제
	public void insertMyProduct(MyProductVO vo) {
		System.out.println("===> mybatis insertMyProduct() 실행");
		mybatis.insert("myProduct.insertMyProduct", vo);
	}
	
	// 결제 취소
	public void deleteMyProduct(MyProductVO vo) {
		System.out.println("===> mybatis deleteMyProduct() 실행");
		mybatis.delete("myProduct.deleteMyProduct", vo);
	}
	
	// 마이페이지 조회
	public List<MyProductVO> selectMyProduct(MyProductVO vo) {
		System.out.println("===> mybatis selectMyProduct() 실행");
		return mybatis.selectList("myProduct.selectMyProduct", vo.getMemberIdx());
	}
	
	// 구매를 위한 조회
	public int selectMyProductOk(MyProductVO vo) {
		System.out.println();
		return mybatis.selectOne("myProduct.selectMyProductOk", vo);
	}

}
