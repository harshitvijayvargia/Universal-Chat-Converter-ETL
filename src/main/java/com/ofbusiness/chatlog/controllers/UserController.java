package com.ofbusiness.chatlog.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ofbusiness.chatlog.service.UserService;
import com.ofbusiness.chatlog.User;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")  
	ResponseEntity<List<User>> all() {

		return new ResponseEntity<List<User>>((List<User>) userService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("saveUser")
	ResponseEntity<List<User>> newUser(@RequestBody List<User> users)
	{	
		return new ResponseEntity<List<User>>((List<User>) userService.newUser(users), HttpStatus.ACCEPTED);
	}
	
}
