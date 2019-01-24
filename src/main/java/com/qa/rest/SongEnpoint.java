package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.SongService;

public class SongEnpoint {
	@Path("/song")
	public class SongEndpoint {

		@Inject
		private SongService service;

		@Path("/getAllSongs")
		@GET
		@Produces({ "application/json" })
		public String getAllSongs() {
			return service.getAllSongs();
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

		public void setService(SongService service) {
			this.service = service;
		}
	}
}
