package com.devquiz.biz.model;

public class MyProductVO {
	private int myIdx;
	private int memberIdx;
	private int productIdx;
	private int myProdsDel;
	
	// join
	private String productFile;
	private String productName;
	
	public MyProductVO() {
		System.out.println(">> MyProductVO() 객체 생성");
	}
	
	public int getMyIdx() {
		return myIdx;
	}
	public void setMyIdx(int myIdx) {
		this.myIdx = myIdx;
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
	public int getMyProdsDel() {
		return myProdsDel;
	}
	public void setMyProdsDel(int myProdsDel) {
		this.myProdsDel = myProdsDel;
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

	@Override
	public String toString() {
		return "MyProductVO [myIdx=" + myIdx + ", memberIdx=" + memberIdx + ", productIdx=" + productIdx
				+ ", myProdsDel=" + myProdsDel + ", productFile=" + productFile + ", productName=" + productName + "]";
	}
	
}
