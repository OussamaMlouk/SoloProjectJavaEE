package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

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
	

	public String readUser(Long userId) {
		String userInDB = util.getJSONForObject(findUser(userId));
		return userInDB;
	}

	public User findUser(Long userId) {
		return em.find(User.class, userId);
	}

	public void setManager(EntityManager em) {
		this.em = em;
	}

}
