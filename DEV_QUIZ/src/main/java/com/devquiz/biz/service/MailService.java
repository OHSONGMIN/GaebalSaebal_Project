package com.devquiz.biz.service;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.devquiz.biz.model.MemberVO;

@Service
public class MailService{
	public void sendMail(MemberVO vo) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"context-dev-email.xml"});
		JavaMailSenderImpl mailSender = (JavaMailSenderImpl)ctx.getBean("mailSender");
		
		// 메일 제목, 내용
		String subject = "[개발새발] 요청하신 임시 비밀번호입니다";
		String content = "";
		content += vo.getId() + "께서 요청하신 임시 비밀번호는<br>" ;
		content += vo.getPwd() + "입니다. <br>";
		content += "안전한 사이트 이용을 위하여 로그인 후 비밀번호 변경을 권장합니다.";
		
		// 보내는 사람
		String from = "potatokim0823@naver.com";
		
		// 받는 사람
		String[] to = new String[1];
		to[0] = vo.getEmail();
		
		try {
			// 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

			// 메일 내용을 채워줌
			mailHelper.setFrom(from);	// 보내는 사람 셋팅
			mailHelper.setTo(to);		// 받는 사람 셋팅
			mailHelper.setSubject(subject);	// 제목 셋팅
			mailHelper.setText(content, true);	// 내용 셋팅

			// 메일 전송
			mailSender.send(mail);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
