package com.ofbusiness.chatlog.controllers;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ofbusiness.chatlog.Message;
import com.ofbusiness.chatlog.User;
import com.ofbusiness.chatlog.service.MessageService;


@RestController
@RequestMapping("/chatlog")
public class MessageController {
	
	private final MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/{user}")
	UUID addMessage(@PathVariable User user, @RequestBody Message message)
	{
		return messageService.addMessage(user, message);		
	}
	
	@GetMapping("/{user}")
	List<Message> getAllMessages(@PathVariable User user,@RequestParam(defaultValue = "10") Integer limit,  @RequestParam(required = false) UUID id)
	{
		return messageService.getAllMessages(user, limit,id);
	}

	@DeleteMapping("/{user}")
	void deleteAllMessages(@PathVariable User user)
	{
		messageService.deleteAllMessages(user);
	}
	
	@DeleteMapping("/{user}/{messageID}")
	ResponseEntity<HttpStatus> deleteMessageById(@PathVariable User user, @PathVariable UUID messageID)
	{
		return messageService.deleteMessageById(user, messageID);
	}
	
	//for debugging purposes
	@GetMapping("/alm")
	List<Message> all()
	{
		return messageService.getAllMessages();
	}
	
}

