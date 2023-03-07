package com.ssafy.ws.model.repo;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.User;

@Mapper
public interface UserRepo {
	User select(String id);
}
