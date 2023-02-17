package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.dto.User;

public interface UserService {
	
    public List<User> selectAll();
	public User select(String id);
	public void insert(User user);
	public void update(User user);
	public void delete(String id);
}
