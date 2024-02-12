package com.devquiz.biz.model;

import java.util.Date;

public class AnswerVO {
	private int answerIdx;
	private int questionIdx;
	private String content;
	private String answerOri;
	private String answerFile;
	private Date answerDate;
	public int getAnswerIdx() {
		return answerIdx;
	}
	public void setAnswerIdx(int answerIdx) {
		this.answerIdx = answerIdx;
	}
	public int getQuestionIdx() {
		return questionIdx;
	}
	public void setQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswerOri() {
		return answerOri;
	}
	public void setAnswerOri(String answerOri) {
		this.answerOri = answerOri;
	}
	public String getAnswerFile() {
		return answerFile;
	}
	public void setAnswerFile(String answerFile) {
		this.answerFile = answerFile;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	@Override
	public String toString() {
		return "AnswerVO [answerIdx=" + answerIdx + ", questionIdx=" + questionIdx + ", content=" + content
				+ ", answerOri=" + answerOri + ", answerFile=" + answerFile + ", answerDate=" + answerDate + "]";
	}
	
	
}
