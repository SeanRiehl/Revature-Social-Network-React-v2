package com.project.dao;

import java.util.List;

import com.project.model.Like;

public interface LikeDao {
	
	//CREATE
	public void addLikeToDB(Like like);
	
	//READ
	public List<Like> readAllLikes();
	public List<Like> readLikeByUser(int userId);
	public List<Like> readLikeByPost(int postId);
	
	//UPDATE
	//do we need to actually update the likes?
	
	
	//DELETE
	public void deleteLike(); //we would use this if we wanted to allow users to "unlike" posts. 
	//I'll tentatively add functionality for this that we can use later if we wish; I know unliking posts 
	//is not part of the MVP as I understand it.
	

}
