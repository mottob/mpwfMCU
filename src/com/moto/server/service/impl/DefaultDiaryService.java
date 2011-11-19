package com.moto.server.service.impl;

import java.util.List;

import com.moto.server.bean.Diary;
import com.moto.server.dao.DiaryDao;
import com.moto.server.service.DiaryService;

public class DefaultDiaryService implements DiaryService {

	private DiaryDao diaryDAO;
	
	public DiaryDao getDiaryDAO() {
		return diaryDAO;
	}

	public void setDiaryDAO(DiaryDao diaryDao) {
		this.diaryDAO = diaryDao;
	}

	public Diary getDiaryById(int id) {
		return diaryDAO.select(id);
	}

	public List<Diary> getDiaryByUserId(int userId) {
		return diaryDAO.selectByUser(userId);
	}

	public int createDiary(String title, String content, int userId) {
		return diaryDAO.insert(title, content, userId);
	}

	public boolean modifyDiary(int id, String title, String content,
			int userId) {
		return diaryDAO.update(id, title, content);
	}

	public boolean deleteDiray(int id) {
		return diaryDAO.delete(id);
	}
}
