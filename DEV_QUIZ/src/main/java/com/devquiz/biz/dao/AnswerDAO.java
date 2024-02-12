package com.devquiz.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.AnswerVO;


@Repository
public class AnswerDAO {
	//@Autowired
	private SqlSessionTemplate mybatis;
	
	public AnswerDAO() {
		System.out.println(">>> AnswerDAO() 객체 생성");
	}
	@Autowired
	public AnswerDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> AnswerDAO(SqlSessionTemplate) 객체 생성");
		this.mybatis = mybatis;
	}
//예림 23.11.24
	//답변 수정
	public void adminupdateAnswer(AnswerVO vo) {
		System.out.println("===> MyBatis JDBC로 adminupdateAnswer() 실행");
		mybatis.update("answerDAO.adminupdateAnswer", vo);
	}

	//답변 삭제
	public void admindeleteAnswer(AnswerVO vo) {
		System.out.println("===> MyBatis JDBC로 admindeleteAnswer() 실행");
		mybatis.delete("answerDAO.admindeleteAnswer", vo);
	}
//예림끝
}
