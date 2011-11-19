package com.moto.server.service;

import java.util.List;

import com.moto.server.bean.Diary;

public interface DiaryService {
	public Diary getDiaryById(int id);
	
	public List<Diary> getDiaryByUserId(int userId);
	
	public int createDiary(String title, String content, int userId);
	
	public boolean modifyDiary(int id, String title, String content, int userId);
	
	public boolean deleteDiray(int id);
}
