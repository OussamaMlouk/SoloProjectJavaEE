package com.qa.business.service;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.persistence.domain.User;
import com.qa.persistence.repository.UserRepository;
import com.qa.util.JSONUtil;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;

	@Inject
	private JSONUtil util;

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Override
	public String getAllUsers() {
		return repo.getAllUsers();
	}

	@Override
	public String createUser(String user) {
		User newUser = util.getObjectForJSON(user, User.class);
		String newUserName = newUser.getUserName();
		String newPassword = newUser.getPassword();
		Query query = em.createQuery("SELECT userName FROM User u");
		Collection<String> userNames = (Collection<String>) query.getResultList();
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{3,}$";
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(newPassword);
		for (String userName : userNames) {
			if (newUserName.equals(userName)) {
				return "{\"message\": \"username already taken\"}";
			}
		}
		if (matcher.matches()) {
			return repo.createUser(user);
		} else {
			return "{\"message\": \"password must include at least one lowercase letter, one uppercase letter and one digit\"}";
		}
	}

	@Override
	public Long getIdFromUserName(String userName) {
		return repo.getIdFromUserName(userName);
	}

	@Override
	public String deleteUser(Long userId) {
		return repo.deleteUser(userId);
	}

	@Override
	public String deleteUser(String userName) {
		return repo.deleteUser(userName);
	}

	@Override
	public String deleteUserWithPassword(String user) {
		return repo.deleteUserWithPassword(user);
	}

	@Override
	public String updateUser(String user, Long userId) {
		return repo.updateUser(user, userId);
	}

	@Override
	public String updateUser(String users) {
		User[] usersObject = util.getObjectForJSON(users, User[].class);
		User newUserObject = usersObject[1];
		String newUserName = newUserObject.getUserName();
		String newUserPassword = newUserObject.getPassword();
		Query query = em.createQuery("SELECT userName FROM User u");
		Collection<String> userNames = (Collection<String>) query.getResultList();
		String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{3,}$";
		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(newUserPassword);
		for (String userName : userNames) {
			if (newUserName.equals(userName)) {
				return "{\"message\": \"username already taken\"}";
			}
		}
		if (matcher.matches()) {
			return repo.updateUser(users);
		} else {
			return "{\"message\": \"password must include at least one lowercase letter, one uppercase letter and one digit\"}";
		}
	}

	@Override
	public String readUser(Long userId) {
		return repo.readUser(userId);
	}

	@Override
	public String readUser(String userName) {
		return repo.readUser(userName);
	}

	@Override
	public User findUser(String userName) {
		return repo.findUser(userName);
	}

	@Override
	public User findUser(Long userId) {
		return repo.findUser(userId);
	}

	public void setRepo(UserRepository repo) {
		this.repo = repo;
	}

}
