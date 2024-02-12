package com.devquiz.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.PointVO;

@Repository
public class MemberDAO {
	//@Autowired
	private SqlSessionTemplate mybatis;
	
	public MemberDAO() {
		System.out.println(">>> MemberDAO() 객체 생성");
	}
	@Autowired
	public MemberDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> MemberDAO(SqlSessionTemplate) 객체 생성");
		this.mybatis = mybatis;
	}
	
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	
	//회원가입 : 김지홍 
	public void insertMember(MemberVO vo) {
		mybatis.insert("memberDAO.insertMember", vo);
	}
  
	//로그인 : 김지홍
	public MemberVO loginMember(MemberVO vo) {
		return (MemberVO)mybatis.selectOne("memberDAO.loginMember", vo);
	}
  
	//관리자 로그인
	public MemberVO adminLogin(MemberVO vo) {
		return (MemberVO)mybatis.selectOne("memberDAO.adminLogin", vo);
	}
	
	
	//회원정보수정 : 김지홍
	public void updateMember(MemberVO vo) {
		mybatis.update("memberDAO.updateMember", vo);
	}
  
	//회원탈퇴(물리삭제) : 김지홍
	public void deleteMember(MemberVO vo) {
		mybatis.delete("memberDAO.deleteMember", vo);
	}
  
	
	//회원탈퇴(논리삭제) : 김지홍
	public void deleteMemberSoft(MemberVO vo) {
		mybatis.update("memberDAO.deleteMemberSoft", vo);
	}
	
	
	//아이디 중복체크 : 김지홍  
	public int idCheck(String id) {
		int result =  mybatis.selectOne("memberDAO.idCheck", id);		 
		return result;
	}
  
	//이메일 중복체크 : 김지홍
	public int emailCheck(String email) {
		int result = mybatis.selectOne("memberDAO.emailCheck", email);
		return result;
	}
  
	//아이디 찾기 : 김지홍	  
	public String searchId(String name, String email) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("email", email);  

		String searchId = mybatis.selectOne("memberDAO.searchId", params);		  
	  
		return searchId;
	}
	
	//회원정보가져오기	
	public MemberVO searchMem(Map<String, String> params) {
		return mybatis.selectOne("memberDAO.searchMem", params);
	}
		
	//비밀번호 바꿔주기
	public void updatePwd(Map<String, String> params) {
		mybatis.update("memberDAO.pwdUpdate", params);
	}
	
	
	// 11월 28일 추가---------------------------------------------
	//내 즐겨찾기 목록
	public List<CategoryVO> myFavorite(int memberIdx) {
		return mybatis.selectList("memberDAO.myFavorite", memberIdx);
	}

	//내가 쓴 글 목록
	public List<CommunityVO> myWrite(int memberIdx) {
		return mybatis.selectList("memberDAO.myWrite", memberIdx);		
	}
	
	//내 포인트 적립 내역
	public List<PointVO> myPoints(int memberIdx) {
		return mybatis.selectList("memberDAO.myPoints", memberIdx);
	}
	
	
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	
	
	//예림 시작
	//예림 시작
	//예림 시작
	//아이디로 게시글 내역 조회
	public List<BoardVO> admingetId(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 admingetId() 실행");
		return mybatis.selectList("memberDAO.admingetId", memberIdx);
	}
	//아이디로 게시글 수 조회
	public int admingetIdCnt(int memberIdx) {
		System.out.println("===> MyBatis JDBC로admin getIdCnt() 실행");
		return mybatis.selectOne("memberDAO.admingetIdCnt", memberIdx);
	}
	
	//아이디로 포인트 합계 조회
	public int admingetPointCnt(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 admingetPointCnt() 실행");
		return mybatis.selectOne("memberDAO.admingetPointCnt", memberIdx);
	}

	//회원정보 수정
	public void adminupdateMember(MemberVO vo) {
		System.out.println("===> MyBatis JDBC로 adminupdateMember() 실행");
		mybatis.update("memberDAO.adminupdateMember", vo);
	}

	//회원정보 글삭제
	public void admindeleteMember(MemberVO vo) {
		System.out.println("===> MyBatis JDBC로 admindeleteMember() 실행");
		mybatis.update("memberDAO.admindeleteMember", vo);
	}

	//회원정보  1개 조회
	public MemberVO admingetMember(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 admingetMember() 실행");
		return mybatis.selectOne("memberDAO.admingetMember", memberIdx);
	}

	//회원정보 조회
	public List<MemberVO> admingetMemberList() {
		System.out.println("===> MyBatis JDBC로 admingetMemberList() 실행");
		return null;
	}
	
	//회원정보 조회
	public List<MemberVO> admingetMemberList(MemberVO vo) {
		System.out.println("===> MyBatis JDBC로 admingetMemberList() 실행");
		// 검색조건 값이 없을 때 기본값 설정
		if (vo.getSearchCondition() == null) {
			vo.setSearchCondition("ID");
		}
		if (vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		String sql = "";
		if ("ID".equals(vo.getSearchCondition())) {
			sql = "memberDAO.admingetMemberList_I";
		} else if ("NAME".equals(vo.getSearchCondition())) {
			sql = "memberDAO.admingetMemberList_N";
		}
		
		return mybatis.selectList(sql, vo.getSearchKeyword());
	}
	
	//아이디별 주문 건수
	public int adminorderCnt(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 adminorderCnt() 실행");
		return mybatis.selectOne("memberDAO.adminorderCnt", memberIdx);
	}

	public void adminupdateAdmin(MemberVO vo) {
		System.out.println("===> MyBatis JDBC로 adminupdateAdmin() 실행");
		mybatis.update("memberDAO.adminupdateAdmin", vo);
	}
//예림 끝
//예림 끝
//예림 끝
		
	// (시작) ==================== [한예림 23.11.28] ==================== (시작)	
	//멤버리스트 조회(페이징 처리)
	public List<MemberVO> adminMemberPaging(Map<String, Integer> pagingParams) {
		System.out.println("===> MyBatis JDBC로 adminMemberPaging() 실행");
		return mybatis.selectList("memberDAO.adminMemberPaging", pagingParams);
	}
	
	//멤버수 조회
	public int adminMemberCount() {
		System.out.println("===> MyBatis JDBC로 adminMemberCount() 실행");
		return mybatis.selectOne("memberDAO.adminMemberCount");
	}
	// (끝) ==================== [한예림 23.11.28] ==================== (끝)	
	
}			
