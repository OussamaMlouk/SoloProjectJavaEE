package com.qa.business.service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.regex.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.persistence.domain.Song;
import com.qa.persistence.repository.SongRepository;
import com.qa.util.JSONUtil;

public class SongServiceImpl implements SongService {

	@Inject
	private SongRepository repo;

	@Inject
	private JSONUtil util;

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public String getAllSongs() {
		return repo.getAllSongs();
	}

	public String createSong(String song) {
		Song newSong = util.getObjectForJSON(song, Song.class);
		String newSongName = newSong.getSongName();
		String newArtistName = newSong.getArtistName();
		String newAlbumName = newSong.getAlbumName();
		String newProducerName = newSong.getProducerName();
		String newYear = newSong.getYear();
		Long userId = newSong.getUserId();
		Query query = em.createQuery("SELECT s FROM Song s");
		Collection<Song> songs = (Collection<Song>) query.getResultList();
		Collection<Song> userSongList = songs.stream().filter(s -> s.getUserId().equals(userId))
				.collect(Collectors.toList());
		String yearPattern = "^\\d{4}$";
		Pattern pattern = Pattern.compile(yearPattern);
		Matcher matcher = pattern.matcher(newYear);
		for (Song userSongs : userSongList) {
			if (newSongName.equals(userSongs.getSongName()) && newArtistName.equals(userSongs.getArtistName())
					&& newAlbumName.equals(userSongs.getAlbumName())) {
				if (newProducerName.equals(userSongs.getProducerName()) && newYear.equals(userSongs.getYear())) {
					return "{\"message\": \"you already have this song\"}";
				}
			}
		}
		if (matcher.matches()) {
			if (("Kanye West".equalsIgnoreCase(newArtistName)) || ("Kanye West".equalsIgnoreCase(newProducerName))) {
				return repo.createSong(song);
			} else {
				return "{\"message\": \"Kanye West must be the artist or the producer for this song\"}";
			}
		} else {
			return "{\"message\": \"year must be four digits\"}";
		}

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
