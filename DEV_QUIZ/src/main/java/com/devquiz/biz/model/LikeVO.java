package com.devquiz.biz.model;

import java.sql.Date;

public class LikeVO {
	private int memberIdx;
	private int productIdx;
	private Date likeDate;
	
	// join
	private String productFile;
	private String productName;
	private int productPrice;
	
	public LikeVO() {
		System.out.println(">> LikeVO() 객체 생성");
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

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	// join
	public String getProductFile() {
		return productFile;
	}
	
	public void setProductFile(String productFile) {
		this.productFile = productFile;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "LikeVO [memberIdx=" + memberIdx + ", productIdx=" + productIdx + ", likeDate=" + likeDate
				+ ", productFile=" + productFile + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}

}
