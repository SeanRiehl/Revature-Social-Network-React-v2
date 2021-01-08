package com.project.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
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

import com.project.model.Post;

@Repository("postDao")
@Transactional
public class PostDAOImpl implements PostDAO {

	private SessionFactory sesFact; //this is null initially
	
	public PostDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public PostDAOImpl(SessionFactory sesFact) {
		super();
		this.sesFact = sesFact;
	}

	@Override
	public void createPost(Post post) 
	{
//		// Boilerplate session start
//		System.out.println("IN CREATE POST---------------------------------------------------------------------");
//		System.out.println(post.getAuthor());
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//
//		ses.save(post);
//
//		// Boilerplate session end
//		tx.commit();
//		ses.close();
		
		sesFact.getCurrentSession().save(post);
	}

	@Override
	public List<Post> readAllPosts() {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();

		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);

		Root<Post> root = cr.from(Post.class);

		Query<Post> query = ses.createQuery(cr);
		List<Post> results = query.getResultList();

		return results;
	}

	@Override
	public Post readPostByID(int post_id) {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();

		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);

		Root<Post> root = cr.from(Post.class);

		cr.select(root).where(cb.equal(root.get("postId"), post_id));

		Query<Post> query = ses.createQuery(cr);
		List<Post> results = query.getResultList();

		if (results.size() == 0) {
			return null;
		}

		return results.get(0);
	}

	////////////////////////////////////////// readPostByUser
	@Override
	public List<Post> readAllPostByUserId(int userId) {
		// Boilerplate session start
		Session ses = sesFact.getCurrentSession();

		CriteriaBuilder cb = ses.getCriteriaBuilder();
		CriteriaQuery<Post> cr = cb.createQuery(Post.class);

		Root<Post> root = cr.from(Post.class);

		// select criteria
		cr.select(root).where(cb.equal(root.get("author"), userId));

		Query<Post> query = ses.createQuery(cr);
		List<Post> results = query.getResultList();

		if (results.size() == 0) {
			return null;
		}

		return results;
	}

	@Override
	public boolean updateText(int postID, String postText) {

		Session ses = sesFact.getCurrentSession();
		CriteriaBuilder cb = ses.getCriteriaBuilder();

		CriteriaUpdate<Post> cUpdate = cb.createCriteriaUpdate(Post.class);
		Root<Post> root = cUpdate.from(Post.class);
		cUpdate.set("postText", postText);
		cUpdate.where(cb.equal(root.get("postId"), postID));

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
	public boolean updatePicture(int post_id, byte[] picture) {

		//HAVE NOT YET TESTED THIS ONE, BUT THEORETICALLY IT SHOULD WORK
		Session ses = sesFact.getCurrentSession();
		CriteriaBuilder cb = ses.getCriteriaBuilder();

		CriteriaUpdate<Post> cUpdate = cb.createCriteriaUpdate(Post.class);
		Root<Post> root = cUpdate.from(Post.class);
		cUpdate.set("postPicture", picture);
		cUpdate.where(cb.equal(root.get("postId"), post_id));

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
	public boolean updateLikes(int post_id, int user_id) 
	{
		// TODO Auto-generated method stub
		System.out.println("Not implemented yet.");
		return false;

	}
	
	
	@Override
	public boolean deletePost(Post post) 
	{
		Session ses = sesFact.getCurrentSession();
		CriteriaBuilder cb = ses.getCriteriaBuilder();

		CriteriaDelete<Post> cDelete = cb.createCriteriaDelete(Post.class);
		Root<Post> root = cDelete.from(Post.class);
		
		cDelete.where(cb.equal(root.get("postId"), post.getPostId()));

		//Transaction t = ses.beginTransaction();
		int updateCnt = ses.createQuery(cDelete).executeUpdate();
		//t.commit();
		
		//Check if any rows updated
		if(updateCnt > 0)
		{
			return true;
		}
		
		return false;
		
	}


//	@Override
//	public boolean deletePost(Post post) {
//		// Boilerplate session start
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//
//		// Business logic
//		ses.delete(post);
//		
//
//		// Boilerplate session end
//		tx.commit();
//		
//		List<Post> pList = ses.createQuery("FROM User WHERE"
//				+ " postId ='" + post.getPostId() + "'", Post.class).list();
//
//		
//		ses.close();
//		
//		//Check if deleted user still exists
//		if(pList.size() == 0)
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//		
//	}
}
