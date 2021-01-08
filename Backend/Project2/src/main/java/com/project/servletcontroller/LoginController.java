package com.project.servletcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.User;
import com.project.service.UserService;
import com.project.service.UserServiceImpl;

public class LoginController {

	UserService usi = new UserServiceImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * This method should be sent via POST and have username and password as properties in JSON or send through the
	 * parameters 
	 * returns user object
	 * @param req
	 * @param res
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Map<String,String> login;
		if(username != null || password != null) {
			login = new HashMap<String,String>();
			login.put("username", username);
			login.put("password", password);
		} else {
			login = mapper.readValue(req.getReader(),new TypeReference<Map<String,String>>() {});
		}
		System.out.println(login);
		User user = usi.getUserByUsername(login.get("username"));
		if(user == null || !login.get("password").equals(user.getPassword())) {
			return;
		}
		req.getSession().setAttribute("User", user);
		res.getWriter().write(mapper.writeValueAsString(user));
	}
}
