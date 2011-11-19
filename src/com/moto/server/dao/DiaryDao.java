package com.moto.server.dao;

import java.util.List;

import com.moto.server.bean.Diary;

public interface DiaryDao {
	
	public int insert(String title, String content, int userId);
	
	public boolean delete(int id);
	
	public boolean update(int id, String title, String content);
	
	public Diary select(int diaryId);
	
	public List<Diary> selectByUser(int userId);
	
}
