package com.project.service;

import java.util.List;

import com.project.model.Post;

public class PostServiceImpl implements PostService {
	
	
	
	
	@Override
	public boolean addPost(Post post) {
		// TODO Auto-generated method stub
		// TODO: Check string length, if it's over 280 say no
		return false;
	}

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostByID(int post_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateText(int post_id, String postText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePicture(int post_id, byte[] picture) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLikes(int post_id, int user_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePost(Post post) {
		// TODO Auto-generated method stub
		return false;
	}
}
