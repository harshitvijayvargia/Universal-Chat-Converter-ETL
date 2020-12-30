package com.ofbusiness.chatlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ofbusiness.chatlog.User;
import com.ofbusiness.chatlog.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public List<User> newUser(List<User> users) {
		return (List<User>) userRepository.saveAll(users);
	}

}
