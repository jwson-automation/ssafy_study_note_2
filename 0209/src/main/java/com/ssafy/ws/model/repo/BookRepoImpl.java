package com.ssafy.ws.model.repo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.model.Book;

@Component
public class BookRepoImpl implements BookRepo {
	
	@Autowired
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Book insert(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book update(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book select(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
