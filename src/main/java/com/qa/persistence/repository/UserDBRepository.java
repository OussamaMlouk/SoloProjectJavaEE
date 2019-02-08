package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.User;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class UserDBRepository implements UserRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;

	public String getAllUsers() {
		Query query = em.createQuery("SELECT u FROM User u");
		Collection<User> users = (Collection<User>) query.getResultList();
		return util.getJSONForObject(users);
	}

	public Long getIdFromUserName(String userName) {
		Query query = em.createQuery("Select userId FROM User u WHERE u.userName = :userName");
		query.setParameter("userName", userName);
		@SuppressWarnings("unchecked")
		List<Long> entries = (List<Long>) query.getResultList();
		return entries.get(0);
	}

	@Transactional(REQUIRED)
	public String createUser(String user) {
		User aUser = util.getObjectForJSON(user, User.class);
		em.persist(aUser);
		return "{\"message\": \"user has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String deleteUser(Long userId) {
		User userInDB = findUser(userId);
		if (userInDB != null) {
			em.remove(userInDB);
		}
		return "{\"message\": \"user sucessfully deleted\"}";
	}

	@Transactional(REQUIRED)
	public String deleteUser(String userName) {
		Long userId = getIdFromUserName(userName);
		User userInDB = findUser(userId);
		if (userInDB != null) {
			em.remove(userInDB);
			return "{\"message\": \"user sucessfully deleted\"}";
		} else {
			return "{\"message\": \"user not found\"}";
		}
	}

	@Transactional(REQUIRED)
	public String deleteUserWithPassword(String user) {
		User inputUser = util.getObjectForJSON(user, User.class);
		String inputUserName = inputUser.getUserName();
		String inputUserPassword = inputUser.getPassword();
		Long userId = getIdFromUserName(inputUserName);
		User userInDB = findUser(userId);
		if (userId != null) {
			if (inputUserPassword.equals(userInDB.getPassword())) {
				em.remove(userInDB);
				return "{\"message\": \"user sucessfully deleted\"}";
			} else {
				return "{\"message\": \"incorrect password\"}";
			}
		} else {
			return "{\"message\": \"user not found\"}";
		}
	}

	@Transactional(REQUIRED)
	public String updateUser(String user, Long userId) {
		User userInDB = findUser(userId);
		if (userInDB != null) {
			deleteUser(userId);
			createUser(user);
			return "{\"message\": \"user successfully updated\"}";
		} else {
			return "{\"message\": \"user not found\"}";
		}
	}

	@Transactional(REQUIRED)
	public String updateUser(String users) {
		User[] usersObjects = util.getObjectForJSON(users, User[].class);
		User oldUserObject = usersObjects[0];
		User newUserObject = usersObjects[1];
		Long oldUserId = oldUserObject.getUserId();
		String oldUserPassword = oldUserObject.getPassword();
		String newUserName = newUserObject.getUserName();
		String newUserPassword = newUserObject.getPassword();
		User userInDB = findUser(oldUserId);
		if (userInDB != null) {
			if (userInDB.getPassword().equals(oldUserPassword)) {
				userInDB.setUserName(newUserName);
				userInDB.setPassword(newUserPassword);
				return "{\"message\": \"user successfully updated\"}";
			} else {
				return "{\"message\": \"incorrect password\"}";
			}
		} else {
			return "{\"message\": \"user not found\"}";
		}
	}

	public String readUser(Long userId) {
		return util.getJSONForObject(findUser(userId));
	}

	public String readUser(String userName) {
		Long userId = getIdFromUserName(userName);
		return util.getJSONForObject(findUser(userId));
	}

	public User findUser(Long userId) {
		return em.find(User.class, userId);
	}

	public User findUser(String userName) {
		return em.find(User.class, userName);
	}

	public void setManager(EntityManager em) {
		this.em = em;
	}

}
