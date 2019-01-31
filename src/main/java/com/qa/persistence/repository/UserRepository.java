package com.qa.persistence.repository;

public interface UserRepository {
	
	String getAllUsers();
	
	String createUser(String user);
	
	Long getIdFromUserName(String userName);
	
	String deleteUser(Long userId);
	
	String deleteUser(String userName);
	
	String updateUser(String user, Long userId);
	
	String updateUser(String user, String userName);
	
	String readUser(Long songId);
	
	String readUser(String userName);
	
}
