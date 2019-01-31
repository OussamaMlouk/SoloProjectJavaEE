package com.qa.persistence.repository;

import com.qa.persistence.domain.Song;

public interface SongRepository {

	String getAllSongs();

	String createSong(String song);
	
	Long getIdFromSongName(String songName);

	String deleteSong(Long songId);
	
	String deleteSong(String songName);

	String updateSong(String song, Long songId);
	
	String updateSong(String song, String songName);
	
	String readSong(Long songId);
	
	String readSong(String songName);
	
	String getSongList(Long userId);

}
