package com.devquiz.biz.service;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.devquiz.biz.model.ProductVO;

public class ProductServiceClient {

	public static void main(String[] args) {
		System.out.println("--- 스프링 컨테이너 구동전 --------");
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		System.out.println("--- 스프링 컨테이너 구동후 --------");

		ProductService productService = (ProductService) container.getBean("productService");
		System.out.println("productService : " + productService);

		ProductVO vo = new ProductVO();
		vo.setProductName("상품명-spring");
		vo.setProductContent("상품내용-spring");
		vo.setProductPrice(30000);

		productService.insertProduct(vo);

		List<ProductVO> list = productService.getProductList(new ProductVO());
		for (ProductVO product : list) {
			System.out.println(product);
		}

		System.out.println("--- 스프링 컨테이너 종료처리 --------");
		container.close();

	}

}
