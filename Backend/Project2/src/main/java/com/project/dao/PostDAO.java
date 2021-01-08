package com.project.dao;

import java.util.List;

import com.project.model.Post;

public interface PostDAO {
	// Create
	public void createPost(Post post);
	
	// Read
	public List<Post> readAllPosts();
	public Post readPostByID(int post_id);
	public List<Post> readAllPostByUserId(int userId);
	
	// Update
	public boolean updateText(int post_id, String postText); // TODO: Figure out these methods
	public boolean updatePicture(int post_id, byte[] picture);
	public boolean updateLikes(int post_id, int user_id);
	
	// Delete
	public boolean deletePost(Post post);
}
