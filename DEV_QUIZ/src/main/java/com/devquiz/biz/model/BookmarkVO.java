package com.devquiz.biz.model;

public class BookmarkVO {
	
	private int bookmarkIdx;
	private int memberIdx;
	private int boardIdx;
	private int cateIdx;
	
	public int getBookmarkIdx() {
		return bookmarkIdx;
	}
	public void setBookmarkIdx(int bookmarkIdx) {
		this.bookmarkIdx = bookmarkIdx;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public int getCateIdx() {
		return cateIdx;
	}
	public void setCateIdx(int cateIdx) {
		this.cateIdx = cateIdx;
	}
	
	@Override
	public String toString() {
		return "BookmarkVO [bookmarkIdx=" + bookmarkIdx + ", memberIdx=" + memberIdx + ", boardIdx=" + boardIdx
				+ ", cateIdx=" + cateIdx + "]";
	}
	
	
}

