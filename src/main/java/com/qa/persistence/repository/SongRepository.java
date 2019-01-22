package com.qa.persistence.repository;

import com.qa.persistence.domain.Song;

public interface SongRepository {

	String getAllSongs();

	String createSong(String song);

	String deleteSong(Long songId);

	String updateSong(String song, Long songId);

}
