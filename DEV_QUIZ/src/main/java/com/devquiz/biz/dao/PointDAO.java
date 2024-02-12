package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.PointVO;

@Repository
public class PointDAO {
	//@Autowired
	private SqlSessionTemplate mybatis;
	
	public PointDAO() {
		System.out.println(">>> PointDAO() 객체 생성");
	}
	@Autowired
	public PointDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> PointDAO(SqlSessionTemplate) 객체 생성");
		this.mybatis = mybatis;
	}
//예림 시작
	//아이디로 포인트 내역 조회
	public List<PointVO> admingetPoint(int memberIdx) {
		System.out.println("===> MyBatis JDBC로 admingetPoint() 실행");
		return mybatis.selectList("pointDAO.admingetPoint", memberIdx);
	}
	
	//포인트 삭제
	public void admindeletePoint(PointVO vo) {
		System.out.println("===> MyBatis JDBC로 admindeletePoint() 실행");
		mybatis.delete("pointDAO.admindeletePoint", vo);
	}

//예림 끝
	
	// 민주 시작
	public void buyProductPoint(PointVO vo) {
		mybatis.insert("pointDAO.buyProductPoint", vo);
	}
	// 민주 끝
}
