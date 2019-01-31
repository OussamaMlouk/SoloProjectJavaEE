package com.qa.business.service;

public interface UserService {
	
	String getAllUsers();

	String createUser(String user);

	String deleteUser(Long userId);

	String deleteUser(String userName);
	
	String updateUser(String user, Long userId);
	
	String updateUser(String user, String userName);
	
	String readUser(Long userId);
	
	String readUser(String userName);
	
}
