package com.ofbusiness.chatlog;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	Message() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID messageID;
	
	@Column(nullable = false)
	String message;
	
	@Column(nullable = false)
	Long timeStamp;
	
	@Column(nullable = false)
	boolean isSent;
	
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UUID getId() {
		return messageID;
	}
	public void setId(UUID id) {
		this.messageID = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public boolean isSent() {
		return isSent;
	}
	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}
	
	@Override
	public String toString() {
		return "Message{" + "timestamp=" + this.timeStamp  + '}';
	}

}
