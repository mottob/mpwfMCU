package com.moto.server.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="diary")
public class Diary extends Result {
	private int id;
	private String title;
	private String content;
	private int userId;
	private Date date;

	public Diary() {
	}
	
	public Diary(int id, String title, String content, int userId, Date date) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.date = date;
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlAttribute
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlAttribute
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@XmlAttribute
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@XmlAttribute
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
