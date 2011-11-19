package com.moto.server.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="avatar")
public class Avatar extends Result {
	private int id;
	private String description;
	private byte[] data;
	private int userId;

	public Avatar() {
	}
	
	public Avatar(int id, String description, byte[] data, int userId) {
		this.id = id;
		this.description = description;
		this.data = data;
		this.userId = userId;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@XmlAttribute
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@XmlAttribute
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
