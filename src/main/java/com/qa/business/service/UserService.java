package com.qa.business.service;

public interface UserService {
	
	String getAllUsers();

	String createUser(String user);

	String deleteUser(Long userId);

	String updateUser(String user, Long userId);
	
	String readUser(Long userId);
	
}
