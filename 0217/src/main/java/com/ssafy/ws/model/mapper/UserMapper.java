package com.ssafy.ws.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.User;

@Mapper
public interface UserMapper {

	public List<User> selectAll();
	public User select(String id);
	public void insert(User user);
	public void update(User user);
	public void delete(String id);
	



}
