package com.ssafy.ws.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;

@Controller
public class BookController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "index";
	}

	@GetMapping("/board/{index}")
	public String home1(@PathVariable(value = "index") String index) {

		return "index";
	}

	@RequestMapping("/error/404")
	public String showError404() {
		return "error/404";
	}

	@PostMapping("/login")
	public String doLogin(User user, HttpSession session, Model model) {
		
	    // 사용자 id와 비밀번호가 일치하는 경우 로그인에 성공
	    if (user.getId().equals("ssafy") && user.getPass().equals("1234")) {
	        // 세션에 사용자 정보를 저장
	        session.setAttribute("loginUser", user.getId());
	        // index 페이지로 redirect
	        return "redirect:/login";
	    } else {
	        // 로그인 실패 메시지를 모델에 담아서 index 페이지로 forward
	        model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
	        return "login";
	    }
	}

	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}

	@GetMapping("/list")
	public String showList(HttpServletRequest request) {
		// 여러 개의 Book 객체를 생성
		Book book1 = new Book("001", "돈키호테", "허태식", 35000, "ㅎㅇ", "ㅎㅇ");
		Book book2 = new Book("002", "레미제라블", "허태식", 32000, "ㅎㅇ", "ㅎㅇ");
		Book book3 = new Book("003", "정의란 무엇인가", "허태식", 32000, "ㅎㅇ", "ㅎㅇ");

		List<Book> books = Arrays.asList(book1, book2, book3);
		// 도서 정보를 모델에 추가
		request.setAttribute("books", books);
		// list 페이지로 forward
		return "list";
	}

	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}


	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Book book) {
	    // 요청으로 전달된 도서 정보를 그대로 사용한다.
	    return "regist_result";
	}

	/**
	 * <pre>
	 * /error/commonerr 요청이 get 방식으로 들어왔을 때 error/commonerr로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/error/commonerr")
	public String showError500() {
		return "error/commonerr";
	}

}
