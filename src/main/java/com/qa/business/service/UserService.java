package com.qa.business.service;

public interface UserService {
	
	String getAllUsers();

	String createUser(String user);
	
	Long getIdFromUserName(String userName);

	String deleteUser(Long userId);

	String deleteUser(String userName);
	
	String updateUser(String user, Long userId);
	
	String updateUser(String users);
		
	String readUser(Long userId);
	
	String readUser(String userName);
	
}
