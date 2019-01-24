package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;

	public String getAllUsers() {
		return repo.getAllUsers();
	}

	public String createUser(String user) {
		return repo.createUser(user);
	}

	public String deleteUser(Long userId) {
		return repo.deleteUser(userId);
	}

	public String updateUser(String user, Long userId) {
		return repo.updateUser(user, userId);
	}

	public String readUser(Long userId) {
		return repo.readUser(userId);
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}

}
