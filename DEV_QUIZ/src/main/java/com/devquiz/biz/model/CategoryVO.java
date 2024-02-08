package com.devquiz.biz.model;

public class CategoryVO {
	
	private int cateIdx;
	private String cateName;
	
	public int getCateIdx() {
		return cateIdx;
	}
	public void setCateIdx(int cateIdx) {
		this.cateIdx = cateIdx;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	@Override
	public String toString() {
		return "CategoryVO [cateIdx=" + cateIdx + ", cateName=" + cateName + "]";
	}
	
}
