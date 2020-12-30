package com.ofbusiness.chatlog;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import com.ofbusiness.chatlog.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreloadUsers {

	private static final Logger log = LoggerFactory.getLogger(PreloadUsers.class);
	
	@Bean
	CommandLineRunner initiateDatabase(UserRepository userRepository)
	{
	    return args -> {
	        log.info("Preloading " + userRepository.save(new User("DataUser1")));
	        log.info("Preloading " + userRepository.save(new User("DataUser2")));
	      };
	    }
	}

