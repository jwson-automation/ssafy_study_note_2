package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.dto.Book;

public interface BookService {
	
	public Book highestAverage();
	public List<Book> getAllList();
	

}
