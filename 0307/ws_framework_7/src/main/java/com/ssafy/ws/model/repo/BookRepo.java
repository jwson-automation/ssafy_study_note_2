package com.ssafy.ws.model.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.Book;

@Mapper
public interface BookRepo {

	int insert(Book book);

	int update(Book book);

	int delete(String isbn);
	
	Book select(String isbn);
	
	List<Book> search();
}
