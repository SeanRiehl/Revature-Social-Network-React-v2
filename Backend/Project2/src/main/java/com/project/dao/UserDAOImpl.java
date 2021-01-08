package com.project.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.User;

@Repository("userDao")
@Transactional
public class UserDAOImpl implements UserDAO {

	private SessionFactory sesFact; //initially null
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UserDAOImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	//@Autowired
	public void setSesFact(SessionFactory sesFact) {
		this.sesFact = sesFact;
	}

	@Override
	public boolean insertUser(User user) {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();
		//Transaction tx = ses.beginTransaction();

		ses.save(user);

		// Boilerplate session end
		//tx.commit();
		
		//Try to get inserted user.
//		List<User> uList = ses.createQuery("FROM User WHERE"
//				+ " username ='" + user.getUsername() + "'", User.class).list();
//
//		
//		//Check if inserted user exists
//		if(uList.size() > 0)
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
		return true;
	}

	@Override
	public List<User> readAllUsers() {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();

		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);

		Root<User> root = cr.from(User.class);
		cr.select(root);

		Query<User> query = ses.createQuery(cr);
		List<User> results = query.getResultList();

		// Business logic
		// List<User> userList = ses.createQuery("from User", User.class).list();

		return results;
	}

	@Override
	public User readUserByID(int userID) {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();

		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);

		Root<User> root = cr.from(User.class);

		cr.select(root).where(cb.equal(root.get("userId"), userID));

		Query<User> query = ses.createQuery(cr);
		List<User> results = query.getResultList();

		// Business logic
		// User user = ses.get(User.class, user_id);

		if (results.size() == 0) {
			return null;
		}

		return results.get(0);
	}

	@Override
	public User readUserByUsername(String username) {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();

		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);

		Root<User> root = cr.from(User.class);

		cr.select(root).where(cb.equal(root.get("username"), username));

		Query<User> query = ses.createQuery(cr);
		List<User> results = query.getResultList();

		// Business logic
		// User user = ses.get(User.class, username);
		// List<User> useList = ses.createQuery("from User where" + " username='" +
		// username + "'", User.class).list();

		if (results.size() == 0) {
			return null;
		}
		// System.out.println(results);

		return results.get(0);
	}

	@Override
	public boolean updateUsername(int user_id, String username) {
		
		Session ses = sesFact.getCurrentSession();
		CriteriaBuilder cb = ses.getCriteriaBuilder();

		CriteriaUpdate<User> cUpdate = cb.createCriteriaUpdate(User.class);
		Root<User> root = cUpdate.from(User.class);
		cUpdate.set("username", username);
		cUpdate.where(cb.equal(root.get("userId"), user_id));

		//Transaction t = ses.beginTransaction();
		
		int updateCnt = ses.createQuery(cUpdate).executeUpdate();
		//t.commit();
		
		//Check if any rows updated
		if(updateCnt > 0)
		{
			return true;
		}
		
		return false;

	}

	@Override
	public boolean updatePassword(int user_id, String password) {
		Session ses = sesFact.getCurrentSession();
		CriteriaBuilder cb = ses.getCriteriaBuilder();

		CriteriaUpdate<User> cUpdate = cb.createCriteriaUpdate(User.class);
		Root<User> root = cUpdate.from(User.class);
		cUpdate.set("password", password);
		cUpdate.where(cb.equal(root.get("userId"), user_id));

		//Transaction t = ses.beginTransaction();
		//Store the number of rows that were updated
		int updateCnt = ses.createQuery(cUpdate).executeUpdate();
		//t.commit();
		
		//Check if any rows updated
		if(updateCnt > 0)
		{
			return true;
		}
		
		return false;
		

	}

	@Override
	public boolean updatePicture(int userID, byte[] picture) 
	{
		//get Hibernate session
		Session ses = sesFact.getCurrentSession();
		//Get the criteria builder
		CriteriaBuilder cb = ses.getCriteriaBuilder();
		
		//Create update criteria
		CriteriaUpdate<User> cUpdate = cb.createCriteriaUpdate(User.class);
		
		//Set up the From table
		Root<User> root = cUpdate.from(User.class);
		
		//Set statement. Can have duplicates.
		cUpdate.set("profilePicture", picture);
		
		//Set filter (Where)
		cUpdate.where(cb.equal(root.get("userId"), userID));

		//Transaction t = ses.beginTransaction();
		int updateCnt = ses.createQuery(cUpdate).executeUpdate();
//		t.commit();
		
		//Check if any rows updated
		if(updateCnt > 0)
		{
			return true;
		}
		
		return false;

	}

	@Override
	public boolean deleteUser(User user) {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();
		//Transaction tx = ses.beginTransaction();

		// Business logic
		ses.delete(user);
		
		// Boilerplate session end
		//tx.commit();
		
		//Try to get deleted user.
		List<User> uList = ses.createQuery("FROM User WHERE"
				+ " username ='" + user.getUsername() + "'", User.class).list();
		
		//Check if deleted user still exists
		if(uList.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
