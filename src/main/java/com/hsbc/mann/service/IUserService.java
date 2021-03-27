package com.hsbc.mann.service;

import com.hsbc.mann.entity.User;

public interface IUserService {

	Integer registerUser(User user);
	
	User findByUserEmail(String email);
}
