package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.ProductVO;

@Repository
public class ProductDAO {

	// @Autowired
	private SqlSessionTemplate mybatis;

	public ProductDAO() {
		System.out.println(">>> ProductDAO() 객체 생성");
	}

	@Autowired
	public ProductDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> ProductDAO(SqlSessionTemplate) 객체 생성");
		this.mybatis = mybatis;
	}

	public void insertProduct(ProductVO vo) {
		System.out.println("===> MyBatis JDBC로 insertProduct() 실행");
		mybatis.insert("productDAO.insertProduct", vo);
	}

	public void updateProduct(ProductVO vo) {
		System.out.println("===> MyBatis JDBC로 updateProduct() 실행");
		mybatis.update("productDAO.updateProduct", vo);
	}

	public void deleteProduct(ProductVO vo) {
		System.out.println("===> MyBatis JDBC로 deleteProduct() 실행");
		mybatis.delete("productDAO.deleteProduct", vo);
	}

	public ProductVO getProduct(ProductVO vo) {
		System.out.println("===> MyBatis JDBC로 getProduct() 실행");
		return mybatis.selectOne("productDAO.getProduct", vo);
	}

	public List<ProductVO> getProductList() {
		System.out.println("===> MyBatis JDBC로 getProductList() 실행");
		return null;
	}

	//페이징 처리
	public List<ProductVO> getAdProductPagingList(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 getAdProductPagingList() 실행");
		return mybatis.selectList("productDAO.getAdProductPagingList", pagingParams);
	}
	
	public List<ProductVO> getProductList(ProductVO vo) {
		System.out.println("===> MyBatis JDBC로 getProductList() 실행");
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("PRODUCT_IDX");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		String sql = "";
		if ("PRODUCT_IDX".equals(vo.getSearchCondition())) {
			sql = "productDAO.getProductList_I";
		} else if ("PRODUCT_NAME".equals(vo.getSearchCondition())) {
			sql = "productDAO.getProductList_N";
		}
		
		return mybatis.selectList(sql, vo.getSearchKeyword());
	}

	
	// 상품 개수 조회
	public int Count() {
		System.out.println("===> MyBatis JDBC로 Count() 실행");
		return mybatis.selectOne("productDAO.Count");
	}
	
//	public void deleteImage(int productIdx) {
//		System.out.println("===> MyBatis JDBC로 deleteImage() 실행");
//		mybatis.update("communityDAO.deleteImage", productIdx);
//	}
	
}

