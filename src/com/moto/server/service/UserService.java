package com.moto.server.service;

import com.moto.server.bean.User;

public interface UserService {
	public User getUser(int id);
	
	public int register(String name, String password, String email, String state);
	
	public boolean unregister(int id);
	
	public boolean login(String name, String password);
	
	public boolean modify(int id, String name, String email, String state);
	
	public boolean changePassword(int id, String password);
}
