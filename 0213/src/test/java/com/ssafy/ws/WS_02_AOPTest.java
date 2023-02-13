package com.ssafy.ws;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.service.BookService;

public class WS_02_AOPTest extends AbstractTest{	

	@Autowired
	BookService bservice;
	
	/**
	 * BookService의 메서드들을 호출해보면서 AOP에서 설정한 내용이 로그로 잘 출력되는지 확인한다.
	 */
	@Test
	public void testInsert() {
		bservice.insert(null);
		// 로그의 예 - 메서드선언부:int com.ssafy.ws.model.service.BookService.insert(Book) 전달 파라미터:[null]
		bservice.select("111-222-3333");
		// 로그의 예 - 메서드선언부:Book com.ssafy.ws.model.service.BookService.select(String) 전달 파라미터:[111-222-3333]
		bservice.search();
		// 로그의 예 - 메서드선언부:List com.ssafy.ws.model.service.BookService.search() 전달 파라미터:[]
	}
}
