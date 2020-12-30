package com.ofbusiness.chatlog.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ofbusiness.chatlog.User;

public interface UserRepository extends PagingAndSortingRepository<User, String>
{
	
}