package com.ssafy.ws.model.repo;

import java.util.List;

import com.ssafy.model.Book;

public interface BookRepo {
	
	public Book insert(int isbn);
	public Book update(int isbn);
	public String delete(int isbn);
	public Book select(String title);
	public List<Book> search();

}
