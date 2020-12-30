package com.ofbusiness.chatlog.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ofbusiness.chatlog.Message;
import com.ofbusiness.chatlog.User;

public interface MessageRepository extends PagingAndSortingRepository<Message, UUID> {
	
	public List<Message> findByUser(User user);
	
	public void deleteById(UUID id);
	
	public void deleteByUser(User user);

	public List<Message> findByUser(User user, Pageable paging);
	
}
