package com.ofbusiness.chatlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ofbusiness.chatlog.Message;
import com.ofbusiness.chatlog.User;
import com.ofbusiness.chatlog.repositories.UserRepository;
import com.ofbusiness.chatlog.repositories.MessageRepository;

@Service
public class MessageService {
	
	private final MessageRepository messageRepository;
	private final UserRepository userRepository;
	
	public MessageService(MessageRepository repository, UserRepository userRepository) {
		this.messageRepository = repository;
		this.userRepository = userRepository;
	}
	
	public List<Message> getAllMessages(User user, Integer limit, UUID id)
	{
		if(id!=null)
		{
			return doCustomPagination(user, limit, id);
		}
		Pageable paging  = PageRequest.of(0, limit, Sort.by("timeStamp").descending());
		List<Message> list = messageRepository.findByUser(user, paging);
		return list;
	}
	
	public List<Message> doCustomPagination(User user, Integer limit, UUID id)
	{
		List<Message> messages = messageRepository.findByUser(user);
		List<Message> toBeReturned = new ArrayList<>();
		//sorting arraylist
		for(int i=0;i<messages.size();i++)
		{
			for(int j=i+1;j<messages.size();j++)
			{
				if((messages.get(i).getTimeStamp())<(messages.get(j).getTimeStamp()))
				{
					Message temp = messages.get(i);
					messages.set(i, messages.get(j));
					messages.set(j, temp);
				}
			}
		}
		
		int count = 0;
		boolean flag = false;
		for(int i=0;i<messages.size();i++)
		{
			if(count>=limit)
				break;
			if(flag == true)
			{
				toBeReturned.add(messages.get(i));
				count++;
				continue;
			}
			if(messages.get(i).getId().equals(id))
			{
				flag = true;
				toBeReturned.add(messages.get(i));
				count++;
			}
		}
		return toBeReturned;
	}

	public void deleteAllMessages(User user) {
		Iterable<Message> list = messageRepository.findByUser(user);
		for(Message message: list)
		{
				messageRepository.deleteById(message.getId());
		}
		return;
	}

	public ResponseEntity<HttpStatus> deleteMessageById(User user, UUID messageID) {
		List<Message> messages = messageRepository.findByUser(user);
		boolean flag = false;
		for(Message message: messages)
		{
			if(message.getId().equals(messageID))
			{
				flag = true;
				break;
			}
		}
		if(!flag)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		messageRepository.deleteById(messageID);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public UUID addMessage(User user, Message message) {
		message.setUser(user);
		Iterable<User> list = new ArrayList<>();
		list = userRepository.findAll();
		if(!((ArrayList<User>) list).contains(user))
		{
			System.out.println("Did not find user");
			return null;
		}
		UUID id = messageRepository.save(message).getId();
		return id;
	}

	public List<Message> getAllMessages() {
		return (List<Message>) messageRepository.findAll();
	}


}

