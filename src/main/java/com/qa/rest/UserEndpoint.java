package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.UserService;

@Path("/user")
public class UserEndpoint {
	@Inject
	private UserService service;

	@Path("/getAllUsers")
	@GET
	@Produces({ "application/json" })
	public String getAllUsers() {
		return service.getAllUsers();
	}

	@Path("/readUser/{userId}")
	@GET
	@Produces({ "application/json" })
	public String readUser(@PathParam("userId") Long userId) {
		return service.readUser(userId);
	}

	@Path("/createUser")
	@POST
	@Produces({ "application/json" })
	public String createUser(String user) {
		return service.createUser(user);
	}

	@Path("/deleteUser/{userId}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteUser(@PathParam("userId") Long userId) {
		return service.deleteUser(userId);
	}

	@Path("/updateUser/{userId}")
	@POST
	@Produces({ "application/json" })
	public String updateUser(String user, @PathParam("userId") Long userId) {
		return service.updateUser(user, userId);
	}

	public void setService(UserService service) {
		this.service = service;
	}
}
