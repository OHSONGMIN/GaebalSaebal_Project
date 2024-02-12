package com.devquiz.biz.model;

import java.sql.Date;

public class OrderVO {
	private int orderIdx;
	private int memberIdx;
	private int sumPrice;
	private int orderDel;
	private Date orderDate;
	
	public OrderVO() {
		System.out.println(">> OrderVO() 객체 생성");
	}

	public int getOrderIdx() {
		return orderIdx;
	}

	public void setOrderIdx(int orderIdx) {
		this.orderIdx = orderIdx;
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

	public int getOrderDel() {
		return orderDel;
	}

	public void setOrderDel(int orderDel) {
		this.orderDel = orderDel;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "OrderVO [orderIdx=" + orderIdx + ", memberIdx=" + memberIdx + ", sumPrice=" + sumPrice + ", orderDel="
				+ orderDel + ", orderDate=" + orderDate + "]";
	}
	
}
