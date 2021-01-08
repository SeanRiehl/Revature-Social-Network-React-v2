package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.project.dao.UserDAO;
import com.project.dao.UserDAOImpl;
import com.project.model.User;

public class UserServiceImpl implements UserService {

	private UserDAO uDao = new UserDAOImpl();

	
	public UserServiceImpl() 
	{
		
	}


	public UserServiceImpl(UserDAO uDao) 
	{
		this.uDao = uDao;
	}
	

	public UserDAO getuDao() 
	{
		return uDao;
	}

	public void setuDao(UserDAO uDao) 
	{
		this.uDao = uDao;
	}


	
	
	
	

	@Override
	public boolean addUser(User user) 
	{
		
		return uDao.insertUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		return uDao.readAllUsers();
	}

	@Override
	public User getUserByID(int userID) {
		
		return uDao.readUserByID(userID);
	}

	@Override
	public User getUserByUsername(String username) {
		
		return uDao.readUserByUsername(username);
	}

	@Override
	public boolean updateUsername(int user_id, String username) {
		
		return uDao.updateUsername(user_id, username);
	}

	@Override
	public boolean updatePassword(int user_id, String password) {
		
		return uDao.updatePassword(user_id, password);
	}

	@Override
	public boolean updatePicture(int user_id, byte[] picture) {
		
		return uDao.updatePicture(user_id, picture);
	}

	@Override
	public boolean removeUser(User user) {
		
		return uDao.deleteUser(user);
	}

	@Override
	public boolean verifyCredentials(String username, String password) {
		
		User chkUser = uDao.readUserByUsername(username);
		
		if(chkUser.getPassword().equals(password))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUsernameExists(String username) {
		//The long way. Probably not proper.
		
		List<User> uList = uDao.readAllUsers();
		List<String> unList = new ArrayList<String>();
		
		for(User u : uList)
		{
			unList.add(u.getUsername());
		}
		
		if(unList.contains(username))
		{
			return true;
		}
		
		return false;
	}
}
