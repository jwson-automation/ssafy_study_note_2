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

public class BookServiceImpl implements BookService {
	/**
	 * has a 관계로 사용할 BookRepo 타입의 repo를 선언한다.
	 */
	private BookRepo repo;
	
	/**
	 * setter를 통해 BookRepo를 주입받는다.
	 * @Autowired를 통해 BookRepo 타입의 빈을 주입 받는다.
	 * @param repo
	 */
	@Autowired
	public void setBookRepo(BookRepo repo) {
		this.repo = repo;
	}

	public BookRepo getBookRepo() {
		return repo;
	}

	@Override
	public int insert(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Book select(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
