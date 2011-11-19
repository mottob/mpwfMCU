package com.moto.server.dao;

import java.io.File;

import com.moto.server.bean.Avatar;

public interface AvatarDao {
	
	public int insert(String description, File file, int userId);
	
	public boolean delete(int id);
	
	public boolean update(int id, String description, File file);
	
	public Avatar select(int id);
	
	public Avatar selectByUser(int userId);
}
