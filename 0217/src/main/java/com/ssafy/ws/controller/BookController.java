package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.service.BookService;

@RestController
public class BookController {
	
	@Autowired BookService service;
	
	@GetMapping("/getAllList")
	public List<Book> getAllList(){
		return service.getAllList();
	}
	
	@GetMapping("/highestAverage")
	public Book highestAverage() {
		return service.highestAverage();
	}
	

}
