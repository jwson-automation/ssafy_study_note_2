package com.ssafy.ws.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.service.UserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@GetMapping("/user/list")
	public String thymeleaf(Model model) {
		List<User> list = service.selectAll();
		model.addAttribute("list", list);
		return "allusers";
	}	

}
