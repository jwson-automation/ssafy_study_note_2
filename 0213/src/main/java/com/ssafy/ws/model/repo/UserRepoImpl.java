package com.ssafy.ws.model.repo;

import org.springframework.stereotype.Repository;

import com.ssafy.ws.dto.User;

/**
 * 빈으로 등록될 수 있도록 @Repository를 선언한다.
 *
 */
public class UserRepoImpl implements UserRepo {

	@Override
	public User select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}