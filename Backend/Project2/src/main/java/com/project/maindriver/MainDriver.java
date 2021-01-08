package com.project.maindriver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.dao.LikeDao;
import com.project.dao.PostDAO;
import com.project.dao.PostDAOImpl;
import com.project.dao.UserDAO;
import com.project.dao.UserDAOImpl;
import com.project.model.Like;
import com.project.model.Post;
import com.project.model.User;

public class MainDriver {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	public static UserDAO udi = appContext.getBean("userDao", UserDAO.class);
	public static PostDAO pdi = appContext.getBean("postDao", PostDAO.class);
	public static LikeDao ldi = appContext.getBean("likeDao", LikeDao.class);
	
	public static void main(String[] args) {
		
//////UNCOMMENT THIS OUT IF YOU HAVE NOT INITIALIZED THE DB ALSO IN THE CFG FILE CHANGE hbm2ddl.auto to CREATE!!!!!!
		insertInitialValues();
		
		//System.out.println(udi.readUserByID(2));
		//udi.updatePassword(1, "joe");
		//pdi.updateText(1, "joe did this");
		//System.out.println("\n\t" + udi.readUserByUsername("david"));
//		System.out.println("\n\t\t" + pdi.readAllPosts());
//		System.out.println("\n\t" + pdi.readPostByID(1));
//		System.out.println("\n\t\t" + pdi.readAllPostByUserId(1));
		System.out.println("\n\t" + udi.readAllUsers());
		System.out.println("\n\t" + pdi.readAllPosts());
		//System.out.println("\n\t" + ldi.readAllLikes());
	}

	public static void insertInitialValues() {
		udi.insertUser(new User("david","asdf","david","lyu","d@mail.com", null));
		udi.insertUser(new User("david1","asdf","david","lyu","dav@mail.com", null));
		pdi.createPost(new Post(udi.readUserByID(1), "1234545", null));
		ldi.addLikeToDB(new Like(pdi.readPostByID(1), udi.readUserByID(1)));
		ldi.addLikeToDB(new Like(pdi.readPostByID(1), udi.readUserByID(2)));

//		udi.insertUser(new User("david","asdf","david","lyu","d@mail.com", null));
//		udi.insertUser(new User("david1","asdf","david","lyu","dav@mail.com", null));
		//pdi.createPost(new Post(1, udi.readUserByID(1), "1234545", null));
	}
}
