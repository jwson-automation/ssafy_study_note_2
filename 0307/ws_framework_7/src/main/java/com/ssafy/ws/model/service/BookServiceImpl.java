package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.repo.BookRepo;

/**
 * 빈으로 등록될 수 있도록 @Service를 선언한다.
 *
 */
@Service
public class BookServiceImpl implements BookService {
	/**
	 * has a 관계로 사용할 BookRepo 타입의 repo를 선언한다.
	 */
	@Autowired
	private BookRepo mapper;
	
	/**
	 * setter를 통해 BookRepo를 주입받는다.
	 * @Autowired를 통해 BookRepo 타입의 빈을 주입 받는다.
	 * @param repo
	 */
	@Autowired
	public void setBookRepo(BookRepo repo) {
		this.mapper = repo;
	}

	public BookRepo getBookRepo() {
		return mapper;
	}

	@Override
	public int insert(Book book) {
	    return mapper.insert(book);
	}

	@Override
	public int update(Book book) {
		return mapper.update(book);
	}

	@Override
	public int delete(String isbn) {
		return mapper.delete(isbn);
	}

	@Override
	public Book select(String isbn) {
		return mapper.select(isbn);
	}

	@Override
	public List<Book> search() {
		return mapper.search();
	}

}
