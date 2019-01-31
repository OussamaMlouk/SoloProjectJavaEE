package com.qa.business.service;

public interface SongService {
	
	String getAllSongs();

	String createSong(String song);

	String deleteSong(Long songId);
	
	String deleteSong(String songName);

	String updateSong(String song, Long songId);
	
	String updateSong(String song, String songName);
	
	String readSong(Long songId);
	
	String readSong(String songName);
	
	String getSongList(Long userId);

}
