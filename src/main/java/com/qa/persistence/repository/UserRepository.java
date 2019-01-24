package com.qa.persistence.repository;

public interface UserRepository {
	
	String getAllUsers();
	
	String createUser(String user);
	
	String deleteUser(Long userId);
	
	String updateUser(String user, Long userId);
	
	String readUser(Long songId);
}
