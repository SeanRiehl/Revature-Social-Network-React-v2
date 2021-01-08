package com.project.service;

import java.util.List;

import com.project.model.Post;

public interface PostService {
	public boolean addPost(Post post);
	public List<Post> getAllPosts();
	public Post getPostByID(int post_id);
	public boolean updateText(int post_id, String postText);
	public boolean updatePicture(int post_id, byte[] picture);
	public boolean updateLikes(int post_id, int user_id);
	public boolean removePost(Post post);
}
