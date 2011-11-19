package com.moto.server.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class Result {
	private String msg;

	public Result() {
	}
	
	public Result(String msg) {
		this.msg = msg;
	}
	
	@XmlAttribute
	public String getMessage() {
		return msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}
}
