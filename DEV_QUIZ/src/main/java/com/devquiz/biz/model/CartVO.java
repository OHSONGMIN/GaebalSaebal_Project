package com.devquiz.biz.model;

import java.sql.Date;

public class CartVO {
	private Date cartDate;
	private int cartIdx;
	private int memberIdx;
	private int productIdx;
	private int productNum;
	
	// join
	private int cateIdx;
	private String productName;
	private String productFile;
	private int productPrice;
	
	public CartVO() {
		System.out.println(">> CartVO() 객체 생성");
	}

	public Date getCartDate() {
		return cartDate;
	}

	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}

	public int getCartIdx() {
		return cartIdx;
	}

	public void setCartIdx(int cartIdx) {
		this.cartIdx = cartIdx;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getProductIdx() {
		return productIdx;
	}

	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	
	// join
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

	public String getProductFile() {
		return productFile;
	}

	public void setProductFile(String productFile) {
		this.productFile = productFile;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "CartVO [cartDate=" + cartDate + ", cartIdx=" + cartIdx + ", memberIdx=" + memberIdx + ", productIdx="
				+ productIdx + ", productNum=" + productNum + ", cateIdx=" + cateIdx + ", productName=" + productName
				+ ", productFile=" + productFile + ", productPrice=" + productPrice + "]";
	}
	
}
