package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.model.Book;
import com.ssafy.ws.model.repo.BookRepo;

@Component
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepo bookRepo;
	
	
	public void setBookRepo(BookRepo bookrepo) {
		this.bookRepo = bookrepo;
	}
	
	public BookRepo getBookRepo() {		
		return bookRepo;
	}

	@Override
	public Book insert(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book update(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book select(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
