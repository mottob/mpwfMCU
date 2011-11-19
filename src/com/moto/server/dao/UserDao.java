package com.moto.server.dao;

import com.moto.server.bean.User;

public interface UserDao {
	
	public int insert(String username, String password, String email, String state);
	
	public boolean delete(int id);
	
	public boolean update(int id, String username, String email, String state);
	
	public boolean changePassword(int id, String password);
	
	public User select(int id);
}
