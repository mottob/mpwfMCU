package com.moto.server.service;

import java.io.File;

import com.moto.server.bean.Avatar;

public interface AvatarService {
	public Avatar getAvatarById(int id);
	
	public Avatar getAvatarByUserId(int userId);
	
	public int createAvatar(String description, File file, int userId);
	
	public boolean modifyAvatar(int id, String description, File file);
	
	public boolean deleteAvatar(int id);
}
