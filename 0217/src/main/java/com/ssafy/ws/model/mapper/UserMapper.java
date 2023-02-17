package com.ssafy.ws.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.User;

@Mapper
public interface UserMapper {

	public List<User> selectAll();
	public User select();
	public void insert();
	public void update();
	public void delete();
	



}
