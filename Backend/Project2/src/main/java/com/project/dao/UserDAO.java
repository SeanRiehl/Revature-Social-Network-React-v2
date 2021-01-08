package com.project.dao;

import java.util.List;

import com.project.model.User;

public interface UserDAO {
	// Create
	public boolean insertUser(User user);

	// Read
	public List<User> readAllUsers();
	public User readUserByID(int user_id);
	public User readUserByUsername(String username);

	// Update
	public boolean updateUsername(int user_id, String username);
	public boolean updatePassword(int user_id, String password);
	public boolean updatePicture(int user_id, byte[] picture);

	// Delete
	public boolean deleteUser(User user);
}
