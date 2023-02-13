package com.ssafy.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.dto.User;

@Controller
public class BookController {

	/**
	 * <pre>
	 *  / 또는 /index 요청이 get 방식으로 들어왔을 때 index 로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@GetMapping(value = { "/", "index" })
	public String home() {
		return "index";
	}

	/**
	 * <pre>
	 * /error/404 요청이 get 방식으로 들어왔을 때 error/404로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping("/error/404")
	public String showError404() {
		return "error/404";
	}

	/**
	 * <pre>
	 * /login 요청이 post 방식으로 왔을 때 login 처리한다.
	 * </pre>
	 * 
	 * 사용자의 요청에서 계정 정보를 추출해 로그인 처리한다. 일단 사용자 id가 ssafy, 비밀번호가 1234면 로그인에 성공으로 간주한다.
	 * 로그인 성공 시 session에 정보를 담고 redirect로 index로 이동한다. 그렇지 않을 경우는 로그인 실패 메시지를 model에
	 * 담고 forward로 index로 이동한다.
	 * 
	 * @param user    전달된 파라미터는 ModelAttribute를 통해서 객체로 받을 수 있다.
	 * @param session 사용자 정보를 세션에 저장하기 위해 사용한다.
	 * @param model   Request scope에 정보를 저장하기 위해서 사용된다.
	 * @return
	 */

	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, HttpSession session, Model model) {
		if ("ssafy".equals(user.getId()) && "1234".equals(user.getPass())) {
			user.setName("김싸피");
			session.setAttribute("loginUser", user);
			return "redirect:/";
		} else {
			model.addAttribute("msg", "로그인 실패");
			return "/index";
		}
	}

	/**
	 * <pre>
	 * /logout을 get 방식으로 요청했을 때 session을 만료 시키고 로그아웃 처리한다.
	 * </pre>
	 * 
	 * 다음 경로는 redirect 형태로 /index로 이동한다.
	 * 
	 * @param session
	 * @return
	 */

	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}

	/**
	 * <pre>
	 * /list를 get 방식으로 요청할 때 도서 정보를 보여준다.
	 * </pre>
	 * 
	 * 아직 MVC의 model 영역을 완성하지 않았기 때문에 여러 개의 Book을 직접 생성해서 List 형태로 전달한다. 정보를 Model
	 * 객체에 저장 후 forward로 list를 호출한다.
	 * 
	 * @return
	 */
	List<Book> books = new ArrayList<>();

	@GetMapping("/list")
	public String showList(Model model) {
		if (books.isEmpty()) {
			books.add(new Book("111-222-3333", "홍길동", "책제목 1", 10000, "좋은 책 1", "abc1.png"));
			books.add(new Book("111-222-4444", "임꺽정", "책제목 2", 20000, "좋은 책 2", "abc2.png"));
			books.add(new Book("111-333-4444", "장길산", "책제목 3", 30000, "좋은 책 3", "abc3.png"));
		}

		model.addAttribute("books", books);
		return "list";
	}

	/**
	 * get 방식의 regist 요청이 오면 regist 페이지를 forward로 연결한다. jsp에서 등록화면을 찾아서 리턴.
	 * 
	 * @return
	 */
	@GetMapping("/regist")
	public String showRegistForm() {
		return "regist";
	}

	/**
	 * post 방식의 regist 요청이 오면 요청으로 전달된 book 객체를 그대로 regist_result에 연결한다. jsp에서 등록결과
	 * 화면을 찾아서 리턴. jsp에서는 book에 담긴 내용을 출력한다.
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Book book) {
		ModelAndView model = new ModelAndView();
		books.add(book);
		model.addObject("book", book);
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
