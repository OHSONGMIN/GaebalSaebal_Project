package com.devquiz.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.ProductVO;
import com.devquiz.biz.service.MainService;
import com.devquiz.biz.service.ProductService;

@Controller
@SessionAttributes("product")
public class ProductController {

	private ProductService productService;
	private MainService mainService;

	public ProductController() {
		System.out.println("===========> ProductController() 객체 생성");
	}

	@Autowired
	public ProductController(ProductService productService, MainService mainService) {
		System.out.println("===========> ProductController(productService) 객체 생성");
		System.out.println("::: ProductController productService : " + productService);
		this.productService = productService;
		this.mainService = mainService;
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		System.out.println("====> Map searchConditionMap() 실행");
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("상품번호", "PRODUCT_IDX");
		conditionMap.put("상품명", "PRODUCT_NAME");
		return conditionMap;
	}

	@RequestMapping("/get_product")
	public String getProduct(ProductVO vo, CategoryVO cate, Model model) {
		List<CategoryVO> cateList = mainService.gaebalGetProductCate(cate);
		model.addAttribute("cate", cateList);
		System.out.println(">>> 상품 상세 보여주기");
		System.out.println("vo : " + vo);

		ProductVO product = productService.getProduct(vo);
		System.out.println("product : " + product);

		model.addAttribute("product", product);

		return "admin/product/getProduct";
	}

	@RequestMapping("/get_member_product")
	public String getMemberProduct(ProductVO vo, Model model) {
		System.out.println(">>> 상품 상세 보여주기");
		System.out.println("vo : " + vo);

		ProductVO product = productService.getProduct(vo);
		System.out.println("product : " + product);

		model.addAttribute("product", product);

		return "gaebal/product/getMemberProduct";
	}

	@RequestMapping("/get_product_list")
	public String getProductList(ProductVO vo, CategoryVO cate, Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "cateIdx", required = false) Integer cateIdx) {
		List<CategoryVO> cateList = mainService.gaebalGetProductCate(cate);
		model.addAttribute("cate", cateList);

		List<ProductVO> productPagingList;
		CommunityPageVO pageVO;

		System.out.println("page = " + page);
		productPagingList = productService.getAdProductPagingList(page);
		pageVO = productService.ProductPagingParam(page);

		System.out.println("pageVO : " + pageVO);
		model.addAttribute("productPagingList", productPagingList);
		model.addAttribute("paging", pageVO);

		System.out.println(">>> 상품 전체 목록 보여주기");
		System.out.println("vo : " + vo);

		List<ProductVO> productList = productService.getProductList(vo);

		model.addAttribute("productList", productList);
		model.addAttribute("page", page);

		return "admin/product/getProductList";
	}



	@RequestMapping("/get_member_product_list")
	public String getMemberProductList(ProductVO vo, CategoryVO cate, Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "cateIdx", required = false) Integer cateIdx) {
		List<CategoryVO> cateList = mainService.gaebalGetProductCate(cate);
		model.addAttribute("cate", cateList);

		List<ProductVO> productPagingList;
		CommunityPageVO pageVO;

		System.out.println("page = " + page);
		productPagingList = productService.getAdProductPagingList(page);
		pageVO = productService.ProductPagingParam(page);

		System.out.println("pageVO : " + pageVO);
		model.addAttribute("productPagingList", productPagingList);
		model.addAttribute("paging", pageVO);

		System.out.println(">>> 상품 전체 목록 보여주기");
		System.out.println("vo : " + vo);

		List<ProductVO> productList = productService.getProductList(vo);

		model.addAttribute("productList", productList);
		model.addAttribute("page", page);

		return "gaebal/product/getMemberProductList";
	}

	@RequestMapping("/insert_product_view")
	public String showProductInsertPage(CategoryVO vo, Model model) {
		// 카테고리 목록을 가져와서 모델에 추가
		List<CategoryVO> cateList = mainService.gaebalGetProductCate(vo);
		model.addAttribute("cate", cateList);

		return "admin/product/insertProduct";
	}

//	@RequestMapping("/insert_product_view")
//	public String insertProductView() {
//		return "admin/product/insertProduct";
//	}
	@RequestMapping("/insert_product")
	public String insertProduct(ProductVO vo, HttpServletRequest request, Model model)
			throws IllegalStateException, IOException {
		System.out.println(">>> 상품 등록");
		System.out.println("vo : " + vo);

		MultipartFile uploadFile = vo.getUploadFile();

		if (uploadFile != null && !uploadFile.isEmpty()) {
			// 업로드된 이미지 파일의 원본 파일명
			String filename = uploadFile.getOriginalFilename();
			System.out.println("::: 원본파일명 : " + filename);

			if (filename != null && !filename.isEmpty()) {
				vo.setProductOri(filename);
			} else {
				// 파일명이 없는 경우에 대한 처리
				vo.setProductOri("UnknownFilename");
			}

			if (uploadFile == null) {
				System.out.println("::: uploadFile 파라미터가 전달되지 않은 경우");
			} else if (uploadFile.isEmpty()) {
				System.out.println("::: 전달받은 파일 데이터가 없는 경우");
			} else { // 업로드 파일이 존재하는 경우
				System.out.println("uploadFile.isEmpty() : " + uploadFile.isEmpty());
				// 원본파일명 구하기

				UUID uuid = UUID.randomUUID();

//				String projectPath = System.getProperty("user.dir") + "/src/main/webapp/resources/image/";

				String savedFilename = uuid + "_" + uploadFile.getOriginalFilename();
				System.out.println("::: 저장파일명 : " + savedFilename);

				// 물리적 파일 복사
				String destPathFile = "C:/MyStudy/temp/" + savedFilename;
				uploadFile.transferTo(new File(destPathFile));

				// 원본 파일명을 vo에 설정
				vo.setProductOri(filename); // 수정된 부분

				// 저장 파일명을 vo에 설정
				vo.setProductFile(savedFilename);

				// 이미지 미리보기를 위한 파일 경로 설정
				String imagePath = "/temp/" + savedFilename;
				vo.setImagePath(imagePath);

			}

			productService.insertProduct(vo);
			System.out.println("vo : " + vo);
			return "redirect:get_product_list";
		}

		// 만약 조건을 만족하지 않는 경우에 대한 기본 반환값 설정
		return "redirect:some_default_page";
	}

	private String getFileExtension(String filename) {
		int dotIndex = filename.lastIndexOf(".");
		return (dotIndex == -1) ? "" : filename.substring(dotIndex);
	}

	@RequestMapping("/update_product")
	public String updateProduct(@ModelAttribute("product") ProductVO vo, HttpServletRequest request, Model model,
			HttpSession session) throws IllegalStateException, IOException {

		System.out.println(">>> 상품 수정");
		System.out.println("vo : " + vo);
		MultipartFile uploadFile = vo.getUploadFile();
		System.out.println("> uploadFile : " + uploadFile);

		if (uploadFile != null && !uploadFile.isEmpty()) {
			// 업로드된 이미지 파일의 원본 파일명
			String filename = uploadFile.getOriginalFilename();
			System.out.println("::: 원본파일명 : " + filename);

			if (filename != null && !filename.isEmpty()) {
				vo.setProductOri(filename);
			} else {
				// 파일명이 없는 경우에 대한 처리
				vo.setProductOri("UnknownFilename");
			}

			// 업로드 파일이 존재하는 경우에만 처리
			System.out.println("uploadFile.isEmpty() : " + uploadFile.isEmpty());
			// 원본파일명 구하기
			UUID uuid = UUID.randomUUID();
			String savedFilename = uuid + "_" + uploadFile.getOriginalFilename();
			System.out.println("::: 저장파일명 : " + savedFilename);

			// 물리적 파일 복사
			String destPathFile = "C:/MyStudy/temp/" + savedFilename;
			uploadFile.transferTo(new File(destPathFile));

			// 원본 파일명을 vo에 설정
			vo.setProductOri(filename); // 수정된 부분

			// 저장 파일명을 vo에 설정
			vo.setProductFile(savedFilename);

			// 이미지 미리보기를 위한 파일 경로 설정
			String imagePath = "/temp/" + savedFilename;
			vo.setImagePath(imagePath);
		}

		productService.updateProduct(vo);
		model.addAttribute("product", session.getAttribute("product"));
		System.out.println("vo : " + vo);
		return "redirect:get_product_list";
	}

	@RequestMapping("/delete_product")
	public String deleteProduct(ProductVO vo, SessionStatus sessionStatus) {
		System.out.println(">>> 상품 삭제");
		System.out.println("vo : " + vo);

		productService.deleteProduct(vo);
		sessionStatus.setComplete();

		return "redirect:get_product_list";
	}

}
