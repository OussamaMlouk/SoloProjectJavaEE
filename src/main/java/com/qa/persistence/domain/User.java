package com.qa.persistence.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(unique = true)
	private String userName;
	private String password;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private Set<Song> songList;

	public User() {
		// empty constructor is needed to create a new instance via reflection by your
		// persistence framework
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Song> getSongList() {
		return songList;
	}

}
