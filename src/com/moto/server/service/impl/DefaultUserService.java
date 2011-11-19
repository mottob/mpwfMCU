package com.moto.server.service.impl;

import com.moto.server.bean.User;
import com.moto.server.dao.UserDao;
import com.moto.server.service.UserService;

public class DefaultUserService implements UserService {

	private UserDao userDAO;
	
	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser(int id) {
		return userDAO.select(id);
	}

	public int register(String name, String password, String email, String state) {
		return userDAO.insert(name, password, email, state);
	}

	public boolean unregister(int id) {
		return userDAO.delete(id);
	}

	public boolean modify(int id, String name, String email, String state) {
		return userDAO.update(id, name, email, state);
	}

	public boolean changePassword(int id, String password) {
		return userDAO.changePassword(id, password);
	}

	public boolean login(String name, String password) {
		return true;
	}

}
