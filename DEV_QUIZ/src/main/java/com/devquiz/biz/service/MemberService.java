package com.devquiz.biz.service;

import java.util.List;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.PointVO;

public interface MemberService {
	
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	
	//회원가입 : 김지홍
	void insertMember(MemberVO vo);
  
	//회원정보수정 : 김지홍
	void updateMember(MemberVO vo);

	//회원탈퇴(물리) : 김지홍
	void deleteMember(MemberVO vo);
  
	//회원탈퇴(논리) IS_DEL을 0에서 1로 바꿈
	void deleteMemberSoft(MemberVO vo);
  
	//아이디 중복체크 : 김지홍
	int idCheck(String id);
  
	//이메일 중복체크 : 김지홍
	int emailCheck(String email);
  
	//로그인하기 : 김지홍
	MemberVO loginMember(MemberVO vo);
	
	//관리자 로그인
	MemberVO adminLogin(MemberVO vo);
  
	//아이디 찾기 : 김지홍
	String searchId(String name, String email);
	
	//김지홍
	List<MemberVO> getMemberList(MemberVO vo);
	
	//비밀번호찾기관련 11/24
	MemberVO searchMem(String id, String email);

	//비밀번호 바꾸기 1124
	void pwdUpdate(String email, String pwd);
	
	//즐겨찾기 목록 불러오기
	List<CategoryVO> myFavorite(int memberIdx);
	
	//내가 쓴 글 목록
	List<CommunityVO> myWrite(int memberIdx);
	
	//내 포인트 목록
	List<PointVO> myPoints(int memberIdx);
	
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	
	
	//예림 시작
	void adminupdateMember(MemberVO vo);
	void admindeleteMember(MemberVO vo);
	MemberVO admingetMember(int memberIdx);
	List<MemberVO> admingetMemberList(MemberVO vo);
	List<BoardVO> admingetId(int memberIdx);
	int admingetIdCnt(int memberIdx);
	int admingetPointCnt(int memberIdx);
	int adminorderCnt(int memberIdx);

	void adminupdateAdmin(MemberVO vo);
	//예림 끝
// (시작) ==================== [한예림 23.11.28] ==================== (시작)	
	List<MemberVO> adminMemberPaging(int page);
	CommunityPageVO adminMemberPagingParam(int page);
// (끝) ==================== [한예림 23.11.28] ==================== (끝)	
	
	
	
	
//	// 회원 관리 메소드(admin view) = 관리자 화면에서 사용하는 메소드
//	void updateMember(MemberVO vo);
//	void deleteMember(MemberVO vo);
//	MemberVO getMember(MemberVO vo);
//	List<MemberVO> getMemberList(MemberVO vo);
//	List<MemberVO> getId(MemberVO vo);
//	int getIdCnt(MemberVO vo);

}

