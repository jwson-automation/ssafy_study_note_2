package com.ssafy.ws.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.User;
import com.ssafy.ws.model.repo.UserRepo;

/**
 * 빈으로 등록될 수 있도록 @Service를 선언한다.
 *
 */
public class UserServiceImpl implements UserService {
	/**
	 * has a 관계로 사용할 UserRepo 타입의 repo를 선언한다.
	 */
	private UserRepo repo;
	
	/**
	 * 생성자를 통해 UserRepo를 주입받는다.
	 * UserRepo 타입의 빈을 주입받기 위해 @Autowired를 사용한다.
	 * @param repo
	 */
	public UserServiceImpl(UserRepo repo) {
		this.repo = repo;
	}

	@Override
	public User select(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
