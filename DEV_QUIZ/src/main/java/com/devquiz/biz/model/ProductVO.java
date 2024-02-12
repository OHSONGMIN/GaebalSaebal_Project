package com.devquiz.biz.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import javax.persistence.Entity;

@JsonIgnoreProperties(value = { "productsearchKeyword", "uploadFile", "productOri" })
//@Entity
public class ProductVO {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int productIdx; // 상품 번호
	private int cateIdx; // 카테고리 번호
	private String productName; // 상품 이름
	private String productOri; // 상품 이미지 원래 이름
	private String productFile; // 상품 이미지 파일 이름
	private String productContent; // 상품 내용
	private int productPrice; // 상품 가격
	private int productStock; // 상품 수량
	private Date productDate; // 상품 등록일
	private int productDel; // 상품 삭제
	private int productLike; // 상품 찜

	// 검색조건 처리용
	// @JsonIgnore //JSON 데이터 변경 제외
	private String searchCondition;
	// @JsonIgnore
	private String searchKeyword;

	private String imagePath; // 이미지 파일명
	private String filename;
	private String filepath;

	// 파일업로드
	// @JsonIgnore
	private MultipartFile uploadFile; // 상품 이미지 등록시 사용 (원래 테이블 컬럼에 이미지 파일이 있으므로 적절한 방식으로 고민해보기)

	public ProductVO() {
		System.out.println(">> ProductVO() 객체생성");
	}

	public int getProductIdx() {
		return productIdx;
	}

	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}

	public int getCateIdx() {
		return cateIdx;
	}

	public void setCateIdx(int cateIdx) {
		this.cateIdx = cateIdx;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductOri() {
		return productOri;
	}

	public void setProductOri(String productOri) {
		this.productOri = productOri;
	}

	public String getProductFile() {
		return productFile;
	}

	public void setProductFile(String productFile) {
		this.productFile = productFile;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public int getProductDel() {
		return productDel;
	}

	public void setProductDel(int productDel) {
		this.productDel = productDel;
	}

	public int getProductLike() {
		return productLike;
	}

	public void setProductLike(int productLike) {
		this.productLike = productLike;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "ProductVO [productIdx=" + productIdx + ", cateIdx=" + cateIdx + ", productName=" + productName
				+ ", productOri=" + productOri + ", productFile=" + productFile + ", productContent=" + productContent
				+ ", productPrice=" + productPrice + ", productStock=" + productStock + ", productDate=" + productDate
				+ ", productDel=" + productDel + ", productLike=" + productLike + ", searchCondition=" + searchCondition
				+ ", searchKeyword=" + searchKeyword + ", imagePath=" + imagePath + ", filename=" + filename
				+ ", filepath=" + filepath + ", uploadFile=" + uploadFile + "]";
	}

}
