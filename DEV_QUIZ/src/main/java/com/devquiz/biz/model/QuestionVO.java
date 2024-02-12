package com.devquiz.biz.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "searchCondition", "searchKeyword"})
public class QuestionVO {
	private int questionIdx;
	private int memberIdx;
	private int productIdx;
	private int cateIdx;
	private String questionTitle;
	private String questionContent;
	private String questionOri;
	private String questionFile;
	private Date questionDate;
	
	// [이하영] 문의 게시판에서 작성자 확인을 위해 별명 추가.
	private String nickName;
	// [이하영] 문의 게시판에서 문의 카테고리 확인을 위해 카테고리 이름 추가.
	private String cateName;
	// [이하영] 문의 게시판에서 상품 이름 확인을 위해 상품 이름 추가.
	private String productName;
	
	private String imagePath; // 이미지 파일명
	private String filename;
	private String filepath;
	
	
	//검색조건 처리용
	//@JsonIgnore //JSON 데이터 변경 제외
	private String searchCondition;
	//@JsonIgnore
	private String searchKeyword;
	
	//파일 업로드
	private MultipartFile uploadFile;
	
	//예림 추가 시작 - getter, setter도
		private String nickname;
		//private String cateName;
		//private String productName;
	//예림 끝	

	public QuestionVO() {
		System.out.println(">> BoardVO() 객체생성");
	}

	public int getQuestionIdx() {
		return questionIdx;
	}

	public void setQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public int getProductIdx() {
		return productIdx;
	}

	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}

	public int getCateIdx() {
		return cateIdx;
	}

	public void setCateIdx(int cateIdx) {
		this.cateIdx = cateIdx;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionOri() {
		return questionOri;
	}

	public void setQuestionOri(String questionOri) {
		this.questionOri = questionOri;
	}

	public String getQuestionFile() {
		return questionFile;
	}

	public void setQuestionFile(String questionFile) {
		this.questionFile = questionFile;
	}

	public Date getQuestionDate() {
		return questionDate;
	}

	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "QuestionVO [questionIdx=" + questionIdx + ", memberIdx=" + memberIdx + ", productIdx=" + productIdx
				+ ", cateIdx=" + cateIdx + ", questionTitle=" + questionTitle + ", questionContent=" + questionContent
				+ ", questionOri=" + questionOri + ", questionFile=" + questionFile + ", questionDate=" + questionDate
				+ ", nickName=" + nickName + ", cateName=" + cateName + ", productName=" + productName + ", imagePath="
				+ imagePath + ", filename=" + filename + ", filepath=" + filepath + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword + ", uploadFile=" + uploadFile + ", nickname="
				+ nickname + "]";
	}
}





