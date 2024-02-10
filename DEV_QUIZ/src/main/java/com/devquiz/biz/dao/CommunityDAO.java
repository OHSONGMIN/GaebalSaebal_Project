package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.CategoryVO;
import com.devquiz.biz.model.CommentVO;
import com.devquiz.biz.model.CommunityVO;

@Repository
public class CommunityDAO {
	
	/* Mybatis와 Spring 연동 */
	/* @Autowired 어노테이션을 통해 SqlSessionTemplate 객체를 주입, SqlSessionTemplate 객체는 MyBatis를 사용하여 데이터베이스에 대한 SQL 세션을 관리 */
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public CommunityDAO() {
		System.out.println(">>> CommunityDAO() 객체 생성");
	}
	
	public CommunityDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> CommunityDAO() 객체 생성");
	}
		
}
