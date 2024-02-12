package com.devquiz.biz.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class OrderDetailVO {
	private int orderDetailIdx;
	private int orderIdx;
	private int productIdx;
	private int memberIdx;
//예림 추가 + getter,setter 도 추가
	private int sumPrice;
	private String productName;
	private Date orderDate;
	private int productPrice;
//예림 끝
	
// 민주 시작
	private String productFile;

// 민주 끝
	
	public int getOrderDetailIdx() {
		return orderDetailIdx;
	}
	public void setOrderDetailIdx(int orderDetailIdx) {
		this.orderDetailIdx = orderDetailIdx;
	}
	public int getOrderIdx() {
		return orderIdx;
	}
	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
	}
	public int getProductIdx() {
		return productIdx;
	}
	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}	
	
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}	
	
	public String getProductFile() {
		return productFile;
	}
	public void setProductFile(String productFile) {
		this.productFile = productFile;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [orderDetailIdx=" + orderDetailIdx + ", orderIdx=" + orderIdx + ", productIdx="
				+ productIdx + ", memberIdx=" + memberIdx + ", sumPrice=" + sumPrice + ", productName=" + productName
				+ ", orderDate=" + orderDate + ", productPrice=" + productPrice + ", productFile=" + productFile + "]";
	}
	
	
	
	
	

}
