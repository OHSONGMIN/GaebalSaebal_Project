package com.devquiz.biz.service;

import java.util.List;
import com.devquiz.biz.model.PointVO;

public interface PointService {
//예림 시작
	List<PointVO> admingetPoint(int memberIdx);
	void admindeletePoint(PointVO vo);
//예림 끝
	
	// 민주 시작
	void buyProductPoint(PointVO vo);
	// 민주 끝
}
