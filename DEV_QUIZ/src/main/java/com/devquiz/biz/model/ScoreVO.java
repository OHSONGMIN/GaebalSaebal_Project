package com.devquiz.biz.model;

public class ScoreVO {
	private int memberIdx;
	private int score;
	private int rank;
	private int point;
	
	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "ScoreVO [memberIdx=" + memberIdx + ", score=" + score + ", rank=" + rank + ", point=" + point + "]";
	}
	
	
}
