package com.devquiz.biz.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devquiz.biz.model.BookmarkVO;
import com.devquiz.biz.model.CommunityVO;

@Repository
public class BookmarkDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public BookmarkDAO() {
		System.out.println(">>> BookmarkDAO() 객체 생성");
	}
	
	public BookmarkDAO(SqlSessionTemplate mybatis) {
		System.out.println(">>> BookmarkDAO() 객체 생성");
	}

}
