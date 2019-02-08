package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Song;

import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class SongDBRepository implements SongRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;

	public String getAllSongs() {
		Query query = em.createQuery("SELECT s FROM Song s");
		Collection<Song> songs = (Collection<Song>) query.getResultList();
		return util.getJSONForObject(songs);
	}

	public Long getIdFromSongName(String songName) {
		Query query = em.createQuery("Select songId FROM Song s WHERE s.songName = :songName");
		query.setParameter("songName", songName);
		@SuppressWarnings("unchecked")
		List<Long> entries = (List<Long>) query.getResultList();
		return entries.get(0);
	}

	@Transactional(REQUIRED)
	public String createSong(String song) {
		Song aSong = util.getObjectForJSON(song, Song.class);
		em.persist(aSong);
		return "{\"message\": \"song has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String deleteSong(Long songId) {
		Song songInDB = findSong(songId);
		if (songInDB != null) {
			em.remove(songInDB);
		}
		return "{\"message\": \"song sucessfully deleted\"}";
	}

	@Transactional(REQUIRED)
	public String deleteSong(String songName) {
		Long songId = getIdFromSongName(songName);
		Song songInDB = findSong(songId);
		if (songInDB != null) {
			em.remove(songInDB);
			return "{\"message\": \"song sucessfully deleted\"}";
		} else {
			return "{\"message\": \"song not found\"}";
		}

	}

	@Transactional(REQUIRED)
	public String deleteSong(String songName, String userName) {
		Query queryUser = em.createQuery("Select userId FROM User u WHERE u.userName = :userName");
		queryUser.setParameter("userName", userName);
		@SuppressWarnings("unchecked")
		List<Long> entries = (List<Long>) queryUser.getResultList();
		long userId = entries.get(0);
		Query querySongList = em.createQuery("SELECT s FROM Song s");
		@SuppressWarnings("unchecked")
		Collection<Song> songs = (Collection<Song>) querySongList.getResultList();
		Collection<Song> userSongList = songs.stream().filter(s -> s.getUserId().equals(userId))
				.collect(Collectors.toList());
		if (userSongList != null) {
			for (Song s : userSongList) {
				if (s.getSongName().equals(songName)) {
					userSongList.remove(s);
					em.remove(s);
					return "{\"message\": \"song sucessfully deleted\"}";
				}
			}
			return "{\"message\": \"song not found\"}";
		} else {
			return "{\"message\": \"user not found\"}";
		}
	}

	@Transactional(REQUIRED)
	public String updateSong(String song, Long songId) {
		Song songInDB = findSong(songId);
		if (songInDB != null) {
			deleteSong(songId);
			createSong(song);
			return "{\"message\": \"song successfully updated\"}";
		} else {
			return "{\"message\": \"song not found\"}";
		}
	}

	@Transactional(REQUIRED)
	public String updateSong(String song, String songName) {
		Long songId = getIdFromSongName(songName);
		Song songInDB = findSong(songId);
		if (songInDB != null) {
			deleteSong(songName);
			createSong(song);
			return "{\"message\": \"song successfully updated\"}";
		} else {
			return "{\"message\": \"song not found\"}";
		}
	}

	public String getSongList(Long userId) {
		Query query = em.createQuery("SELECT s FROM Song s");
		Collection<Song> songs = (Collection<Song>) query.getResultList();
		Collection<Song> userSongList = songs.stream().filter(s -> s.getUserId().equals(userId))
				.collect(Collectors.toList());
		return util.getJSONForObject(userSongList);
	}

	public String getSongList(String userName) {
		Query queryUser = em.createQuery("Select userId FROM User u WHERE u.userName = :userName");
		queryUser.setParameter("userName", userName);
		@SuppressWarnings("unchecked")
		List<Long> entries = (List<Long>) queryUser.getResultList();
		long userId = entries.get(0);
		Query querySongList = em.createQuery("SELECT s FROM Song s");
		Collection<Song> songs = (Collection<Song>) querySongList.getResultList();
		Collection<Song> userSongList = songs.stream().filter(s -> s.getUserId().equals(userId))
				.collect(Collectors.toList());
		if (userSongList != null) {
			return util.getJSONForObject(userSongList);
		} else {
			return "{\"message\": \"user not found\"}";
		}
	}

	public String readSong(Long songId) {
		return util.getJSONForObject(findSong(songId));

	}

	public String readSong(String songName) {
		Long songId = getIdFromSongName(songName);
		return util.getJSONForObject(findSong(songId));

	}

	private Song findSong(Long songId) {
		return em.find(Song.class, songId);
	}

	public void setManager(EntityManager em) {
		this.em = em;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
