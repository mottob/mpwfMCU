package com.moto.server.service.impl;

import java.io.File;

import com.moto.server.bean.Avatar;
import com.moto.server.dao.AvatarDao;
import com.moto.server.service.AvatarService;

public class DefaultAvatarService implements AvatarService {

	private AvatarDao avatarDAO;
	
	public AvatarDao getAvatarDAO() {
		return avatarDAO;
	}

	public void setAvatarDAO(AvatarDao avatarDAO) {
		this.avatarDAO = avatarDAO;
	}

	public Avatar getAvatarById(int id) {
		// TODO Auto-generated method stub
		return avatarDAO.select(id);
	}

	public Avatar getAvatarByUserId(int userId) {
		// TODO Auto-generated method stub
		return avatarDAO.selectByUser(userId);
	}

	public int createAvatar(String description, File file, int userId) {
		// TODO Auto-generated method stub
		return avatarDAO.insert(description, file, userId);
	}

	public boolean modifyAvatar(int id, String description, File file) {
		// TODO Auto-generated method stub
		return avatarDAO.update(id, description, file);
	}

	public boolean deleteAvatar(int id) {
		// TODO Auto-generated method stub
		return avatarDAO.delete(id);
	}
}
