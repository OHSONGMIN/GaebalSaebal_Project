package com.devquiz.biz.model;

import java.util.Date;

public class PointVO {
	
	private int pointIdx;
	private int memberIdx;
	private int pointStatus;
	private int point;
	//예림 수정 11.28
	private String pointDate;
	private String dueDate;
	private int num;
	//예림 수정
	public int getPointIdx() {
		return pointIdx;
	}
	public void setPointIdx(int pointIdx) {
		this.pointIdx = pointIdx;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getPointStatus() {
		return pointStatus;
	}
	public void setPointStatus(int pointStatus) {
		this.pointStatus = pointStatus;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPointDate() {
		return pointDate;
	}
	public void setPointDate(String pointDate) {
		this.pointDate = pointDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "PointVO [pointIdx=" + pointIdx + ", memberIdx=" + memberIdx + ", pointStatus=" + pointStatus
				+ ", point=" + point + ", pointDate=" + pointDate + ", dueDate=" + dueDate + ", num=" + num + "]";
	}


}
