package com.qa.business.service;

public interface SongService {
	
	String getAllSongs();

	String createSong(String song);

	String deleteSong(Long songId);

	String updateSong(String song, Long songId);
	
	String readSong(Long songId);
	
	String getSongList(Long userId);

}
