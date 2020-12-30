package com.ofbusiness.chatlog;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	User() {}
	
	@Id
	@Column(length=16)
	private String userName;
	
	public User(String name)
	{
		this.userName = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "User{" + "user=" + this.userName + "messages=" + '}';
	}
	
}
