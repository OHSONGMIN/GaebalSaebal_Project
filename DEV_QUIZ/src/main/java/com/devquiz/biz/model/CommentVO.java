package com.devquiz.biz.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommentVO {

	private int commIdx;
	private int boardIdx;
	private int memberIdx;
	private String commContent;
	private Date commDate;
	private String nickname;
	
	
	
	public CommentVO() {
		System.out.println(">> CommentVO() 객체생성");
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCommIdx() {
		return commIdx;
	}
	public void setCommIdx(int commIdx) {
		this.commIdx = commIdx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date getCommDate() {
		return commDate;
	}
	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}

	@Override
	public String toString() {
		return "CommentVO [commIdx=" + commIdx + ", boardIdx=" + boardIdx + ", memberIdx=" + memberIdx
				+ ", commContent=" + commContent + ", commDate=" + commDate + ", nickname=" + nickname + "]";
	}
	
	
	
	
}
