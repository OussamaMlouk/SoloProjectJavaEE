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

	public String updateSong(String song, Long songId) {
		return repo.updateSong(song, songId);
	}
	
	public void setRepo(SongRepository repo) {
		this.repo = repo;
	}

}
