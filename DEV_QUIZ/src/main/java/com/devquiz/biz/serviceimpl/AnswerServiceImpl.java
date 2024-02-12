package com.devquiz.biz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devquiz.biz.dao.AnswerDAO;
import com.devquiz.biz.model.AnswerVO;
import com.devquiz.biz.service.AnswerService;
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
	@Autowired //타입이 일치하는 객체(인스턴스) 주입
	private AnswerDAO answerDAO;

	public AnswerServiceImpl() {
		System.out.println(">> AnswerServiceImpl() 객체 생성");
	}
//예림 23.11.24

	@Override
	public void adminupdateAnswer(AnswerVO vo) {
		answerDAO.adminupdateAnswer(vo);
	}

	@Override
	public void admindeleteAnswer(AnswerVO vo) {
		answerDAO.admindeleteAnswer(vo);
	}

//예림 끝
	

}
