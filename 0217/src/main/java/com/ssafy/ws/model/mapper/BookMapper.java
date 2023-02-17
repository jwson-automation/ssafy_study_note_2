package com.ssafy.ws.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.Book;

@Mapper
public interface BookMapper {
	
	public Book highestAverage();
	public List<Book> getAllList();

}
