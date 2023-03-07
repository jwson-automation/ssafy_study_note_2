package com.ssafy.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.repo.BookRepo;
import com.ssafy.ws.model.service.BookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bookapi")
@CrossOrigin("*")
public class BookRestController {

	private static final Logger logger = LoggerFactory.getLogger(BookRestController.class);
	
    @Autowired
    private BookService bookService;
    
    @ApiOperation(value = "모든 도서 목록을 반환한다.")
    @GetMapping("/book")
    public List<Book> bookList() {
    	return bookService.search();
    }
    
    @ApiOperation(value = "query string에 해당하는 검색 조건에 해당하는 도서 목록을 반환한다.")
    @GetMapping("/book/{isbn}")
    public Book bookInfo(@PathVariable String isbn) {
    	return bookService.select(isbn);
    }
    
    @ApiOperation(value = "Book 객체를 저장한다.")
    @PostMapping("/book")
    public void insertBook(@RequestBody Book dto) {
    	logger.debug("dto : {}", dto);
    	bookService.insert(dto);
    }
    
    @ApiOperation(value = "Book 객체를 수정한다.")
    @PutMapping("/book")
    public void update(@RequestBody Book dto) {
    	logger.debug("dto:{}", dto);
    	
    	bookService.update(dto);
    }
    
    @ApiOperation(value = "Book 객체를 삭제한다")
    @DeleteMapping("/book/{isbn}")
    public void delete(@PathVariable String isbn) {
    	bookService.delete(isbn);
    }
}
