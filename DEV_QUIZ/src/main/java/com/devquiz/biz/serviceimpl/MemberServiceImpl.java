package com.devquiz.biz.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.MemberDAO;
import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommunityPageVO;
import com.devquiz.biz.model.CommunityVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.PointVO;
import com.devquiz.biz.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired //타입이 일치하는 객체(인스턴스) 주입
	private MemberDAO memberDAO;

	public MemberServiceImpl() {
		System.out.println(">> MemberServiceImpl() 객체 생성");
	}
	
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	// (시작) ==================== [김지홍] ==================== (시작)
	
	//회원 가입 : 김지홍
	public void insertMember(MemberVO vo) {
		this.memberDAO.insertMember(vo);
	}
	  
	//회원정보 수정: 김지홍
	public void updateMember(MemberVO vo) {
		this.memberDAO.updateMember(vo);
	}	  
	  
	//회원탈퇴(물리) : 김지홍
	public void deleteMember(MemberVO vo) {
		this.memberDAO.deleteMember(vo);
	}
	  
	 //회원탈퇴(논리) : 김지홍	  
	 public void deleteMemberSoft(MemberVO vo) {
		this.memberDAO.deleteMemberSoft(vo);		 
	 }
	    
	//아이디 중복체크 : 김지홍
	public int idCheck(String id) {
		int result = this.memberDAO.idCheck(id);
		return result;
	}
	  
	//이메일중복체크 : 김지홍
	public int emailCheck(String email) {
		int result = this.memberDAO.emailCheck(email);
		return result;
	}
	  
	//아이디찾기 : 김지홍		
	public String searchId(String name, String email) {
		String searchId = this.memberDAO.searchId(name, email);
		return searchId;
	}
	
	  
	public List<MemberVO> getMemberList(MemberVO vo) {
		return null;
	}
	  
	public MemberVO loginMember(MemberVO vo) {
		return this.memberDAO.loginMember(vo);
	}

	//관리자로그인
	public MemberVO adminLogin(MemberVO vo) {
		return this.memberDAO.adminLogin(vo);
	}

	

	public void pwdUpdate(String email, String pwd) {
		Map<String, String> params = new HashMap();
		params.put("email", email);
		params.put("pwd", pwd);		
		
		memberDAO.updatePwd(params);
	}
	
	
	//찾은 유저  데려오기
	public MemberVO searchMem(String id,String email) {
		Map<String, String> params = new HashMap();
		params.put("id", id);
		params.put("email", email);
		return this.memberDAO.searchMem(params);
	}	
	
	
	// 즐겨찾기 목록 가져오기
	@Override
	public List<CategoryVO> myFavorite(int memberIdx) {
		return memberDAO.myFavorite(memberIdx);
	}
	
	//내가 쓴 글
	@Override
	public List<CommunityVO> myWrite(int memberIdx) {
		return memberDAO.myWrite(memberIdx);
	}

	//내 포인트
	@Override
	public List<PointVO> myPoints(int memberIdx) {
		return memberDAO.myPoints(memberIdx);
	}
	
	
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	// (끝) ==================== [김지홍] ==================== (끝)
	
	
	//예림 시작
	@Override
	public void adminupdateMember(MemberVO vo) {
		memberDAO.adminupdateMember(vo);
	}

	@Override
	public void admindeleteMember(MemberVO vo) {
		memberDAO.admindeleteMember(vo);
	}

	@Override
	public MemberVO admingetMember(int memberIdx) {
		return memberDAO.admingetMember(memberIdx);
	}

	@Override
	public List<MemberVO> admingetMemberList(MemberVO vo) {
		return memberDAO.admingetMemberList(vo);
	}
	
	@Override
	public List<BoardVO> admingetId(int memberIdx) {
		return memberDAO.admingetId(memberIdx);
	}

	@Override
	public int admingetIdCnt(int memberIdx) {
		return memberDAO.admingetIdCnt(memberIdx);
	}

	@Override
	public int admingetPointCnt(int memberIdx) {
		return memberDAO.admingetPointCnt(memberIdx);
	}

	@Override
	public int adminorderCnt(int memberIdx) {
		return memberDAO.adminorderCnt(memberIdx);
	}
	@Override
	public void adminupdateAdmin(MemberVO vo) {
		memberDAO.adminupdateAdmin(vo);
	}
	
	// (시작) ==================== [한예림 23.11.28] ==================== (시작)
	int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
	int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
	
	@Override
	public List<MemberVO> adminMemberPaging(int page) {
		int pageStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", pageLimit);
		List<MemberVO> MemberPagingList = memberDAO.adminMemberPaging(pagingParams);
		
		return MemberPagingList;
	}

	@Override
	public CommunityPageVO adminMemberPagingParam(int page) {
		// 전체 멤버 갯수 조회
        int memberCount = memberDAO.adminMemberCount();
        // 전체 페이지 갯수 계산(22/10=2.2=> 3)
        int maxPage = (int) (Math.ceil((double) memberCount / pageLimit));
        // 시작 페이지 값 계산(1, 11, 21, 31, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(10, 20, 30, 40, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        CommunityPageVO vo = new CommunityPageVO();
        vo.setPage(page);
        vo.setMaxPage(maxPage);
        vo.setStartPage(startPage);
        vo.setEndPage(endPage);
        return vo;
	}
// (끝) ==================== [한예림 23.11.28] ==================== (끝)	

	
}
