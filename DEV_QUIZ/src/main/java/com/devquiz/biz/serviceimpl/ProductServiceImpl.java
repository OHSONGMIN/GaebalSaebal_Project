package com.devquiz.biz.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.ProductDAO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired 
	private ProductDAO productDAO;

	public ProductServiceImpl() {
		System.out.println(">> ProductServiceImpl() 객체 생성");
	}
	
	@Override
	public void insertProduct(ProductVO vo) {
		productDAO.insertProduct(vo);
	}

	@Override
	public void updateProduct(ProductVO vo) {
		productDAO.updateProduct(vo);
	}

	@Override
	public void deleteProduct(ProductVO vo) {
		productDAO.deleteProduct(vo);
	}

	@Override
	public ProductVO getProduct(ProductVO vo) {
		return productDAO.getProduct(vo);
	}

	@Override
	public List<ProductVO> getProductList(ProductVO vo) {
		return productDAO.getProductList(vo);
	}
	
	int pageLimit = 9; 
	int blockLimit = 3;

	@Override
	public List<ProductVO> getAdProductPagingList(int page) {
		int pageStart = (page - 1) * pageLimit;
		System.out.println("pageLimit" + pageLimit);
		Map<String, Integer> pagingParams = new HashMap();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		List<ProductVO> productPagingList = productDAO.getAdProductPagingList(pagingParams);
		
		return productPagingList;
	}

	 @Override
	    public CommunityPageVO ProductPagingParam(int page) {
	        int productCount = productDAO.Count();
	        int maxPage = (int) Math.ceil((double) productCount / pageLimit);
	        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
	        System.out.println("startPage" + startPage);
	        int endPage = startPage + blockLimit - 1;
	        if (endPage > maxPage) {
	            endPage = maxPage;
	        }
	        int blockPreStartPage = (((int) (Math.ceil((double) page / blockLimit))) - 2) * blockLimit + 1;
	        int blockNextStartPage = ((int) (Math.ceil((double) page / blockLimit))) * blockLimit + 1;

	        CommunityPageVO vo = new CommunityPageVO();
	        vo.setPage(page);
	        vo.setMaxPage(maxPage);
	        vo.setStartPage(startPage);
	        vo.setEndPage(endPage);
	        vo.setBlockLimit(blockLimit);
	        vo.setBlockPreStartPage(blockPreStartPage);
	        vo.setBlockNextStartPage(blockNextStartPage);

	        return vo;
	    }
	}