package com.devquiz.biz.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "searchCondition", "searchKeyword", "uploadFile", "boardOri" })
public class CommunityVO {
	private int boardIdx;
	private int memberIdx;
	private int cateIdx;
	private String title;
	private String content;
	private int hit;
	private Date regDate;
	private String nickname;
	private String cateName;
	private String boardOri; //원본 파일명
	private String boardFile; //저장 파일명
		
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	//검색조건 처리용
	//@JsonIgnore //JSON 데이터 변경 제외
	private String searchCondition;
	//@JsonIgnore
	private String searchKeyword;
	
//	private String imagePath;
//	private String filename; 
//	private String filepath; 
	
	//파일업로드
	//@JsonIgnore
	private MultipartFile uploadFile;
	
	public CommunityVO() {
		System.out.println(">> CommunityVO() 객체생성");
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
	public int getCateIdx() {
		return cateIdx;
	}
	public void setCateIdx(int cateIdx) {
		this.cateIdx = cateIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	//검색조건 처리용 -----------
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

	public String getBoardOri() {
		return boardOri;
	}

	public void setBoardOri(String boardOri) {
		this.boardOri = boardOri;
	}

	public String getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
/*
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
*/
	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "CommunityVO [boardIdx=" + boardIdx + ", memberIdx=" + memberIdx + ", cateIdx=" + cateIdx + ", title="
				+ title + ", content=" + content + ", hit=" + hit + ", regDate=" + regDate + ", nickname=" + nickname
				+ ", cateName=" + cateName + ", boardOri=" + boardOri + ", boardFile=" + boardFile
				+ ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", uploadFile=" + uploadFile + "]";
	}
	
/*	
 	public String toString() {
		return "CommunityVO [boardIdx=" + boardIdx + ", memberIdx=" + memberIdx + ", cateIdx=" + cateIdx + ", title="
				+ title + ", content=" + content + ", hit=" + hit + ", regDate=" + regDate + ", nickname=" + nickname
				+ ", cateName=" + cateName + ", boardOri=" + boardOri + ", boardFile=" + boardFile
				+ ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword + ", imagePath="
				+ imagePath + ", filename=" + filename + ", filepath=" + filepath + ", uploadFile=" + uploadFile + "]";
	} 
*/
}
