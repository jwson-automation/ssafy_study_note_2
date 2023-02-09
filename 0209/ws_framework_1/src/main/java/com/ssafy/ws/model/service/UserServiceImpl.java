package com.ssafy.ws.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.model.User;
import com.ssafy.ws.model.repo.UserRepo;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public User select(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
