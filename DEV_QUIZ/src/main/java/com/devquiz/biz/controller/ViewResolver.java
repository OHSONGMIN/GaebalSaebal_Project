package com.devquiz.biz.controller;

/* ViewResolver 클래스는 Controller 가 리턴한 String(뷰 이름)에 
 * 접두어(prefix_위치)와 접미어(suffix_확장자)를 결합해서
 * 재요청(응답할) 페이지의 경로와 파일명을 완성해서 리턴
 */
public class ViewResolver {
	private String prefix;
	private String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
	
}
