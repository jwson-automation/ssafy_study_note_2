package com.ssafy.ws;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.repo.BookRepo;
import com.ssafy.ws.model.repo.UserRepo;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.UserService;

/**
 * AbstractTest를 상속받아 기본 @RunWith와 @ContextConfiguration 설정을 재사용한다.
 */
public class WS_01_BeanCreationTest extends AbstractTest{

	/**
	 * 로깅 처리를 위해 Logger를 설정한다.
	 */
	private static Logger logger = LoggerFactory.getLogger(WS_01_BeanCreationTest.class);


	// 필요한 빈들을 @Autowired로 주입받는다.
	@Autowired
	BookRepo brepo;
	@Autowired
	BookService bserv;
	@Autowired
	UserRepo urepo;
	@Autowired
	UserService userv;
	@Autowired
	DataSource ds;
	
	/**
	 * BookRepo, UserRepo, BookService, UserService가 잘 생성되었는지 테스트한다.
	 */
	@Test
	public void testBeanCreation() {
		assertNotNull(brepo);
		assertNotNull(bserv);
		assertNotNull(urepo);
		assertNotNull(userv);
	}

	/**
	 * 컨텍스트에서 직접 얻어온 BookRepo와 BookService가 가지는 BookRepo가 동일한지 테스트 한다.
	 */
	@Test
	public void testSingleton() {
		// aop가 적용된 BookServiceImpl에서 가져온 repo는 proxy객체이므로 둘은 다르다.
		// assertEquals(brepo, ((BookServiceImpl) bserv).getBookRepo());
	}

	/**
	 * DataSource객체가 잘 생성되었는지 테스트하고 출력한다.
	 */
	@Test
	public void testDataSource() {
		logger.debug("datasource 확인: {}", ds);
		assertNotNull(ds);
	}

}
