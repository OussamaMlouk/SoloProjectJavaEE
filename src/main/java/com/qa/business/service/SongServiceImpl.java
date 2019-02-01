package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.SongRepository;

public class SongServiceImpl implements SongService {
	
	@Inject
	private SongRepository repo;

	public String getAllSongs() {
		return repo.getAllSongs();
	}

	public String createSong(String song) {
		return repo.createSong(song);
	}

	public String deleteSong(Long songId) {
		return repo.deleteSong(songId);
	}
	
	public String deleteSong(String songName) {
		return repo.deleteSong(songName);
	}
	
	public String deleteSong(String songName, String userName) {
		return repo.deleteSong(songName, userName);
	}

	public String updateSong(String song, Long songId) {
		return repo.updateSong(song, songId);
	}
	
	public String updateSong(String song, String songName) {
		return repo.updateSong(song, songName);
	}
	
	public String readSong(Long songId) {
		return repo.readSong(songId);
	}
	
	public String readSong(String songName) {
		return repo.readSong(songName);
	}
	
	public void setRepo(SongRepository repo) {
		this.repo = repo;
	}
	
	public String getSongList(Long userId) {
		return repo.getSongList(userId);
	}
	
	public String getSongList(String userName) {
		return repo.getSongList(userName);
	}

}
