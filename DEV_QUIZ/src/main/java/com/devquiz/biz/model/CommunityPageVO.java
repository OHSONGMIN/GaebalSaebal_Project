package com.devquiz.biz.model;

public class CommunityPageVO {

	private int page; //현재 페이지
	private int maxPage; //전체 필요한 페이지 개수
	private int startPage; //현재 블록 기준 시작 페이지 값
	private int endPage; //현재 블록 기준 마지막 페이지 값
	private int blockLimit; //한 블록 당 표시될 페이지 개수
	private int blockPreStartPage; //이전 블록의 시작 페이지
	private int blockNextStartPage; //다음 블록의 시작 페이지
	private int boardCount; //게시물 개수
	
	
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getBlockPreStartPage() {
		return blockPreStartPage;
	}
	public void setBlockPreStartPage(int blockPreStartPage) {
		this.blockPreStartPage = blockPreStartPage;
	}
	public int getBlockNextStartPage() {
		return blockNextStartPage;
	}
	public void setBlockNextStartPage(int blockNextStartPage) {
		this.blockNextStartPage = blockNextStartPage;
	}
	public int getBlockLimit() {
		return blockLimit;
	}
	public void setBlockLimit(int blockLimit) {
		this.blockLimit = blockLimit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		return "CommunityPageVO [page=" + page + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", blockLimit=" + blockLimit + ", blockPreStartPage=" + blockPreStartPage
				+ ", blockNextStartPage=" + blockNextStartPage + ", boardCount=" + boardCount + "]";
	}
	
}
