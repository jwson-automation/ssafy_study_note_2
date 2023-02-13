package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.repo.BookRepo;
import com.ssafy.ws.model.repo.UserRepo;

public class WS_05_RepoTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(WS_05_RepoTest.class);
 
	// mybatis를 사용할 때 필요한 속성들이 잘 전달되는지 확인한다.
	@Autowired
	DataSource ds;

	@Autowired
	SqlSessionFactory factory;

	@Test
	public void testMybatisSet() {
		assertNotNull(ds);
		assertNotNull(factory);
	}

	// 필요한 빈들을 가져온다.
	@Autowired
	BookRepo brepo;
	@Autowired
	UserRepo urepo;

	/**
	 * Book 저장을 테스트 한다. 임의의 Book 객체를 만들어서 저장해보고
	 * 다시 그 책을 조회했을 때 예상했던 값과 조회 결과가 같은지 확인한다.
	 * 테스트 후 다시 DB를 원래대로 돌리기 위해 @Transactional을 선언한다.
	 */
	@Test
	@Transactional
	public void testInsertBook() {
		// 자료를 저장한다.
		Book book = new Book("isbn", "title", "author", 199, "desc", "img");
		assertEquals(brepo.insert(book), 1);
		// 저장한 정보를 다시 조회해서 잘 저장되었는지 확인한다.
		Book selected = brepo.select("isbn");
		assertEquals(book.getTitle(), selected.getTitle());
		assertEquals(book.getPrice(), selected.getPrice());
	}

	/**
	 * 기본 자료인 111-222-3333을 업데이트 처리 해본다.
	 * 업데이트 후 다시 동일 자료를 조회해서 수정한 값이 잘 저장되어있는지 확인한다.
	 * 테스트 후 다시 DB를 원래대로 돌리기 위해 @Transactional을 선언한다.
	 */
	@Test
	@Transactional
	public void testUpdateBook() {
		// 기존 자료를 수정한다.
		Book book = new Book("111-000-0001", "author", "title", 100, "desc", "img");
		assertEquals(brepo.update(book), 1);
		// 수정한 정보를 다시 조회해서 잘 저장되었는지 확인한다.
		Book selected = brepo.select("111-000-0001");
		assertEquals(selected.getTitle(), "title");
		assertEquals(selected.getPrice(), 100);
	}

	/**
	 * 기본 자료인 111-222-3333을 업데이트 처리 해본다.
	 * 삭제 후 다시 조회해 보면 조회 결과가 null이 나와야 한다.
	 * 테스트 후 다시 DB를 원래대로 돌리기 위해 @Transactional을 선언한다.
	 */
	@Test
	@Transactional
	public void testDeleteBook() {
		Book book = brepo.select("111-000-0001");
		assertNotNull(book);

		assertEquals(brepo.delete("111-000-0001"), 1);
		book = brepo.select("111-000-0001");
		assertNull(book);
	}

	/**
	 * 기본 자료인 111-222-3333을 업데이트 처리 해본다.
	 * select는 자료를 원복시킬 필요가 없기 때문에 @Transactional 이 필요 없다.
	 */
	@Test
	public void testSelectBook() {
		// 자료를 조회하고 초기 값을 가지고 있는지 확인한다.
		Book book = brepo.select("111-000-0001");
		assertEquals(book.getTitle(), "도서명 11");
		assertEquals(book.getPrice(), 1500);
	}

	/**
	 * 모든 도서 정보를 조회한다.
	 * select는 자료를 원복시킬 필요가 없기 때문에 @Transactional 이 필요 없다.
	 */
	@Test
	public void testSelectAllBook() {
		List<Book> books = brepo.search();
		assertEquals(books.size(), 20);
		assertEquals(books.get(0).getTitle(), "도서명 11");
	}

	/**
	 * 기본 자료인 ssafy의 정보를 조회한다.
	 */
	@Test
	public void testSelectUser() {
		User user = urepo.select("ssafy");

		assertEquals(user.getName(), "김싸피");
	}
}
