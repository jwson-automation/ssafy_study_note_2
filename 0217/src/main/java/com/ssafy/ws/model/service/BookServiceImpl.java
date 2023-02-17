package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;


	@Override
	public Book highestAverage() {
		return mapper.highestAverage();
	}

	@Override
	public List<Book> getAllList() {
		return mapper.getAllList();
	}

}
