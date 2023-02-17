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

	
	@Autowired
	private UserService service;

	@GetMapping("/list")
	public String thymeleaf(Model model) {
		List<User> list = service.selectAll();
		model.addAttribute("list", list);
		return "allusers";

	}

	@GetMapping("/user")
	public List<User> UserInfo() {
		return service.selectAll();
	}

	@GetMapping("/user/{id}")
	public User UserInfo(@PathVariable String id) throws Exception {
		this.isNotFound(id);
		return service.select(id);
	}

	@PostMapping("/user")
	public void UserInsert(@RequestBody User user) throws Exception {
		this.dupCheck(user);
		service.insert(user);
	}

	@PutMapping("/user")
	public void UserUpdate(@RequestBody User user) throws Exception {
		this.isNotFound(user);
		service.update(user);
	}

	@DeleteMapping("/user/{id}")
	public void UserDelete(@PathVariable String id) {
		service.delete(id);
	}

	public void dupCheck(User user) throws Exception {		
		String id = user.getId();
		if (service.select(id) != null) throw new DupException("A100","이미 등록된 사용자가 있습니다.");
	}
	
	public void isNotFound(String id) throws Exception {		
		if (service.select(id) == null) throw new DupException("A101","등록되지 않은 사용자입니다.");
		
	}
	
	public void isNotFound(User user) throws Exception {		
		String id = user.getId();
		if (service.select(id) == null) throw new DupException("A101","등록되지 않은 사용자입니다.");	
		
	}

}
