package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.SongService;

@Path("/song")
public class SongEnpoint {

	@Inject
	private SongService service;

	@Path("/getAllSongs")
	@GET
	@Produces({ "application/json" })
	public String getAllSongs() {
		return service.getAllSongs();
	}
	
	@Path("/readSong/{songId}")
	@GET
	@Produces({"application/json"})
	public String readSong(@PathParam("songId") Long songId) {
		return service.readSong(songId);
	}

	@Path("/createSong")
	@POST
	@Produces({ "application/json" })
	public String createSong(String song) {
		return service.createSong(song);
	}

	@Path("/deleteSong/{songId}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteSong(@PathParam("songId") Long songId) {
		return service.deleteSong(songId);
	}
	
	@Path("/updateSong/{songId}")
	@POST
	@Produces({"application/json"})
	public String updateSong(String song, @PathParam("songId") Long songId) {
		return service.updateSong(song, songId);
	}
	

	public void setService(SongService service) {
		this.service = service;
	}
}