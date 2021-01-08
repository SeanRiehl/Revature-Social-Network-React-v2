package com.project.servletcontroller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.User;
import com.project.service.UserService;
import com.project.service.UserServiceImpl;

public class UserController {
	
	UserService usi = new UserServiceImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * This method returns the user and creates a session
	 * @param req
	 * @param res
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void createUser(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		User user = mapper.readValue(req.getReader(), User.class);
		boolean gotUser = usi.addUser(user);
		if(gotUser == false || user == null) return;
		req.getSession().setAttribute("User", user);
		res.getWriter().write(mapper.writeValueAsString(user));
	}
	
	/**
	 * This method is sent by a GET request and gets the username from the parameters and 
	 * will find that user in the database.
	 * It will return a JSON of the user object except for the password.
	 * @param req
	 * @param res
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void getUser(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		Map<String, String[]> username = req.getParameterMap();
		User user = usi.getUserByUsername(username.get("username")[0]);
		System.out.println(user);
		if(user == null) {
			return;
		}
		Map<String, String> userMap = mapper.convertValue(user, new TypeReference<Map<String,String>>() {});
		userMap.remove("password");
		res.getWriter().write(mapper.writeValueAsString(userMap));
	}

}
