package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.dto.User;

public interface UserService {
	
    public List<User> selectAll();
	public User select();
	public void insert();
	public void update();
	public void delete();
}
