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

import com.qa.persistence.domain.Song;
import com.qa.persistence.domain.Song;
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

	@Transactional(REQUIRED)
	public String createSong(String song) {
		Song anSong = util.getObjectForJSON(song, Song.class);
		em.persist(anSong);
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
