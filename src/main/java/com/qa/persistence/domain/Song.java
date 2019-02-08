package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SONG")
public class Song {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long songId;
	private String songName;
	private String albumName;
	private String artistName;
	private String producerName;
	@Size(min = 4, max = 4)
	private String year;

	private Long userId;

	public Song() {
		// empty constructor is needed to create a new instance via reflection by your
		// persistence framework
	}

	public Song(String songName, String albumName, String artistName, String producerName, String year) {
		this.songName = songName;
		this.albumName = albumName;
		this.artistName = artistName;
		this.producerName = producerName;
		this.year = year;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public Long getSongId() {
		return songId;
	}

	public void setSongId(Long songId) {
		this.songId = songId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUser(Long userId) {
		this.userId = userId;
	}

}
