package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public List<User> selectAll() {
		return (List<User>) mapper.selectAll();
	}

	@Override
	public User select(String id) {
		return mapper.select(id);
	}

	@Override
	public void insert(User user) {
		mapper.insert(user);
	}

	@Override
	public void update(User user) {
		mapper.update(user);
	}


	@Override
	public void delete(String id) {
		mapper.delete(id);
	}

}
