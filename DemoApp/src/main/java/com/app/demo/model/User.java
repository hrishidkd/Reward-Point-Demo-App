package com.app.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private int userId;
	private String userName;

	public User() {
		super();
	}

	public User(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}

}
