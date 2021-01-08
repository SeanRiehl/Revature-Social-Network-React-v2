package com.project.servletcontroller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Post;
import com.project.model.User;
import com.project.service.PostService;
import com.project.service.PostServiceImpl;
import com.project.service.UserService;
import com.project.service.UserServiceImpl;

public class PostController {
	
	ObjectMapper mapper = new ObjectMapper();
	UserService usi = new UserServiceImpl();
	PostService psi = new PostServiceImpl();





	public void createPost(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		
		Map<String,String> body = mapper.readValue(req.getReader(), new TypeReference<Map<String,String>>() {});
		int authorId = Integer.parseInt(body.get("authorId"));
		User author = new User(1,"username","password","david","lyu","d@mail.com",null);
//		User author = usi.getUserByID(authorId);
		//post returns boolean not sure if you wanna return it
		//not sure how to send byte and convert it to string from frontend
		boolean postIsCreated = psi.addPost(new Post(author, body.get("postText"), null));
		
		res.setStatus(HttpServletResponse.SC_CREATED);
		
	}
	
	public void getAllPost(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		List<Post> allPost = psi.getAllPosts();
		
		res.getWriter().write(mapper.writeValueAsString(allPost));
	}
	
	public void getAllUsersPost(HttpServletRequest req, HttpServletResponse res) {
		
	}
}
