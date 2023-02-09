package com.ssafy.ws.model.repo;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.model.User;

@Component
public class UserRepoImpl implements UserRepo {
	@Autowired
	private DataSource dataSource;
	
	public UserRepoImpl(DataSource dataSource) {	
		this.dataSource = dataSource;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public User select(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
