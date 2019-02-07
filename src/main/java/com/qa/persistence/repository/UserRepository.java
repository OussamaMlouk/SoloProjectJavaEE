package com.qa.persistence.repository;

import com.qa.persistence.domain.User;

public interface UserRepository {

	String getAllUsers();

	String createUser(String user);

	Long getIdFromUserName(String userName);

	String deleteUser(Long userId);

	String deleteUser(String userName);

	String deleteUserWithPassword(String user);

	String updateUser(String user, Long userId);

	String updateUser(String users);

	String readUser(Long songId);

	String readUser(String userName);

	User findUser(String userName);

	User findUser(Long userId);

}
