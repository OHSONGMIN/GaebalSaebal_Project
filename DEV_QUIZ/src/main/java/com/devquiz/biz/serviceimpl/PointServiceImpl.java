package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.MemberDAO;
import com.devquiz.biz.dao.PointDAO;
import com.devquiz.biz.model.BoardVO;
import com.devquiz.biz.model.MemberVO;
import com.devquiz.biz.model.PointVO;
import com.devquiz.biz.service.MemberService;
import com.devquiz.biz.service.PointService;

@Service("pointService")
public class PointServiceImpl implements PointService {
	@Autowired //타입이 일치하는 객체(인스턴스) 주입
	private PointDAO pointDAO;

	public PointServiceImpl() {
		System.out.println(">> PointServiceImpl() 객체 생성");
	}
//예림 시작
	@Override
	public List<PointVO> admingetPoint(int memberIdx) {
		return pointDAO.admingetPoint(memberIdx);
	}

	@Override
	public void admindeletePoint(PointVO vo) {	
		pointDAO.admindeletePoint(vo);
	}
//예림 끝
	
	// 민주 시작
	@Override
	public void buyProductPoint(PointVO vo) {
		pointDAO.buyProductPoint(vo);
	}
	// 민주 끝
}
