package com.devquiz.biz.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.MainDAO;
import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.ScoreVO;
import com.devquiz.biz.service.MainService;


@Service("mainService")
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDAO mainDAO;
	// (이하영 시작) ============================================================	
		// [이하영] [admin] : 카테고리 데이터 추가를 위한 메소드
		@Override
		public void adminInsertCate(Map<String, String> map) {
			mainDAO.adminInsertCate(map);
		}
		// [이하영] [admin] : 전체 카테고리 조회를 위한 메소드
		@Override
		public List<CategoryVO> adminGetCateAll(CategoryVO vo) {
			return mainDAO.adminGetCateAll(vo);
		}
		// [이하영] [gaebal] : 게임 실행시 카테고리를 선택하기 위한 메소드
		@Override
		public List<CategoryVO> gaebalGetGameCate(CategoryVO vo) {
			return mainDAO.gaebalGetGameCate(vo);
		}
		// [이하영] [gaebal] : 문의 게시판에서 카테고리를 선택하기 위한 메소드
		@Override
		public List<CategoryVO> gaebalGetQuestionCate(CategoryVO vo) {
			// TODO Auto-generated method stub
			return mainDAO.gaebalGetQuestionCate(vo);
		}
		// [이하영] [gaebal] : 게임 실행 - 포인트를 적립하기 위한 메소드
		@Override
		public void gaebalInsertPoint(ScoreVO vo) {
			mainDAO.gaebalInsertPoint(vo);
		}
		// [이하영] [gaebal] : 포인트 화면에 가져오기!
		@Override
		public MemberVO gaebalGetMemPoint(MemberVO vo) {
			return mainDAO.gaebalGetMemPoint(vo);
		}
		
	// (이하영 끝) ============================================================	

	// [박효정] [gaebal] : 상품 게시판에서 카테고리를 선택하기 위한 메소드
	@Override
	public List<CategoryVO> gaebalGetProductCate(CategoryVO vo) {
		return mainDAO.gaebalGetProductCate(vo);
	}
	//공지사항불러오기
	@Override
	public List<BoardVO> getNotice() {
		List<BoardVO> noticeList = mainDAO.getNotice();
		return noticeList;
	}



}
