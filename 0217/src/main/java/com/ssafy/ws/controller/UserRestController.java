package com.ssafy.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.UserService;
import com.ssafy.ws.util.DupException;

@RestController
public class UserRestController {

	public ArrayList<User> users = new ArrayList<User>();

	public UserRestController() {
		users.add(new User("test1", "test", "test", "test"));
		users.add(new User("test2", "test", "test", "test"));
		users.add(new User("test3", "test", "test", "test"));
	}
	
	@Autowired
	private UserService service;

	@GetMapping("/list")
	public String thymeleaf(Model model) {
		List<User> list = service.selectAll();

		model.addAttribute("list", list);

		return "allusers";

	}
	

	@GetMapping("/user")
	public ArrayList<User> UserInfo() {
		return this.select();
	}

	@GetMapping("/user/{id}")
	public User UserInfo(@PathVariable String id) throws Exception {
		this.isNotFound(id);
		return this.select(id);
	}

	@PostMapping("/user")
	public void UserInsert(@RequestBody User user) throws Exception {
		this.dupCheck(user);
		this.insert(user);
	}

	@PutMapping("/user")
	public void UserUpdate(@RequestBody User user) throws Exception {
		this.isNotFound(user);
		this.update(user);
	}

	@DeleteMapping("/user/{id}")
	public void UserDelete(@PathVariable String id) {
		this.delete(id);
	}

	public ArrayList<User> select() {
		return this.users;
	}

	public User select(String id) {
		
		User matchedUser = null;
		
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				matchedUser = users.get(i);
			}
		}
		
		return matchedUser;
	}

	public void insert(User user) {		
		users.add(user);
		
	}

	public void update(User user) {
		
		int matchedIndex = 100; //지금 예외처리 안해놔서 숫자 범위 벗어나면 인덱스 에러
		String id = user.getId();
		
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				matchedIndex = i;
			}
		}	
		
		this.users.set(matchedIndex, user);
		
	}

	public void delete(String id) {
		
		int matchedIndex = 100; //지금 예외처리 안해놔서 숫자 범위 벗어나면 인덱스 에러
		
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				matchedIndex = i;
			}
		}	
		
		this.users.remove(matchedIndex);
	}
	
	public void dupCheck(User user) throws Exception {
		
		String id = user.getId();
		
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) throw new DupException("A100","이미 등록된 사용자가 있습니다.");
		}	
		
	}
	
	public void isNotFound(String id) throws Exception {
	
		boolean notFound = true;
	
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				notFound = false;
			}
		}	
		if (notFound) throw new DupException("A101","등록되지 않은 사용자입니다.");
		
	}
	
	public void isNotFound(User user) throws Exception {
		
		String id = user.getId();
		boolean notFound = true;
		
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getId())) {
				notFound = false;
			}
		}
		if (notFound) throw new DupException("A101","등록되지 않은 사용자입니다.");	
		
	}

}
