package com.devquiz.biz.model;

public class GameVO {
	private int quizIdx;
	private int cateIdx;
	private String quiz;
	private int savedPoint;
	private int savedScore;
	private String exampleNo1;
	private String exampleNo2;
	private String exampleNo3;
	private String exampleNo4;
	private String quizAnswer;
	private String quizOri;
	private String quizFile;
	
	//game_manage 페이지에서 카테고리 데이터를 불러오기 위함.
	private String cateName;
	
	public int getQuizIdx() {
		return quizIdx;
	}
	public void setQuizIdx(int quizIdx) {
		this.quizIdx = quizIdx;
	}
	public int getCateIdx() {
		return cateIdx;
	}
	public void setCateIdx(int cateIdx) {
		this.cateIdx = cateIdx;
	}
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	public int getSavedPoint() {
		return savedPoint;
	}
	public void setSavedPoint(int savedPoint) {
		this.savedPoint = savedPoint;
	}
	public int getSavedScore() {
		return savedScore;
	}
	public void setSavedScore(int savedScore) {
		this.savedScore = savedScore;
	}
	public String getExampleNo1() {
		return exampleNo1;
	}
	public void setExampleNo1(String exampleNo1) {
		this.exampleNo1 = exampleNo1;
	}
	public String getExampleNo2() {
		return exampleNo2;
	}
	public void setExampleNo2(String exampleNo2) {
		this.exampleNo2 = exampleNo2;
	}
	public String getExampleNo3() {
		return exampleNo3;
	}
	public void setExampleNo3(String exampleNo3) {
		this.exampleNo3 = exampleNo3;
	}
	public String getExampleNo4() {
		return exampleNo4;
	}
	public void setExampleNo4(String exampleNo4) {
		this.exampleNo4 = exampleNo4;
	}
	public String getQuizOri() {
		return quizOri;
	}
	public void setQuizOri(String quizOri) {
		this.quizOri = quizOri;
	}
	public String getQuizFile() {
		return quizFile;
	}
	public void setQuizFile(String quizFile) {
		this.quizFile = quizFile;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getQuizAnswer() {
		return quizAnswer;
	}
	public void setQuizAnswer(String quizAnswer) {
		this.quizAnswer = quizAnswer;
	}
	
	@Override
	public String toString() {
		return "GameVO [quizIdx=" + quizIdx + ", cateIdx=" + cateIdx + ", quiz=" + quiz + ", savedPoint=" + savedPoint
				+ ", savedScore=" + savedScore + ", exampleNo1=" + exampleNo1 + ", exampleNo2=" + exampleNo2
				+ ", exampleNo3=" + exampleNo3 + ", exampleNo4=" + exampleNo4 + ", quizAnswer=" + quizAnswer
				+ ", quizOri=" + quizOri + ", quizFile=" + quizFile + ", cateName=" + cateName + "]";
	}
	
}
