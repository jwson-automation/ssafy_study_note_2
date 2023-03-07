package com.ssafy.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;

import io.swagger.annotations.ApiOperation;

@Controller
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@ApiOperation(value = "모든 책 정보를 리턴합니다.")
	@GetMapping(value = { "/", "/index" })
	public String book() {
		logger.info("Welcome Book Index!.");

		return "index";
	}
	
	@ApiOperation(value = "로그인 합니다.")
	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, HttpSession session, Model model) {
		logger.info("Welcome home! The client locale is {}.", user);
		if ("ssafy".equals(user.getId()) && "1234".equals(user.getPass())) {
			logger.debug("message {}", user);
			user.setName("김싸피");
			session.setAttribute("loginUser", user);
			return "redirect:/index";
		} else {
			model.addAttribute("msg", "로그인 실패");
			return "index";
		}
	}
	
	@ApiOperation(value = "로그아웃 합니다.")
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@ApiOperation(value = "책을 추가합니다.")
	@GetMapping("/list")
	public String showList(Model model) {
		List<Book> books = new ArrayList<>();
		books.add(new Book("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", "abc1.png"));
		books.add(new Book("111-222-4444", "임꺽정", "책제목2", 10000, "좋은 책 2", "abc2.png"));
		books.add(new Book("111-333-4444", "심청이", "책제목3", 10000, "좋은 책 3", "abc3.png"));
		model.addAttribute("books", books);
		return "list";
	}
	
	@ApiOperation(value = "책 정보를 추가합니다.")
	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}

	@ApiOperation(value = "추가 정보를 보여줍니다.")
	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Book book) {
		return "regist_result";
	}
	
//	
//	@RequestMapping("/error/commonerr")
//	public String showError500() {
//		return "error/commonerr";
//	}
//	
//	@RequestMapping("/error/404")
//	public String showError404() {
//		return "error/404";
//	}

}
