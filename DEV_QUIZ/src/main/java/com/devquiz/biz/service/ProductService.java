package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.ProductVO;

public interface ProductService {
	void insertProduct(ProductVO vo);
	void updateProduct(ProductVO vo);
	void deleteProduct(ProductVO vo);
	ProductVO getProduct(ProductVO vo);
	List<ProductVO> getProductList(ProductVO vo);
	List<ProductVO> getAdProductPagingList(int page);
	CommunityPageVO ProductPagingParam(int page);
//	void deleteImage(int productIdx);
}
