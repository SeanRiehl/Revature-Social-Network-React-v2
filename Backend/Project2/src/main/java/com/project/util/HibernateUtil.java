//package com.project.util;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class HibernateUtil {
//	private static String url = System.getenv("PROJECT_2_URL");
//	private static String username = System.getenv("REVATURE_DB_USERNAME");
//	private static String password = System.getenv("REVATURE_DB_PASSWORD");
//	
//
//	private static Configuration cfg;
//	private static SessionFactory sf;
//	
//	
//	/**
//	 * This initializes the config file for hibernate util using environmental variables
//	 */
//	static {
//		System.out.println("in static block");
//		System.out.println(url);
//		System.out.println(username);
//		System.out.println(password);
//		cfg = new Configuration().configure("hibernate.cfg.xml");
//		if(cfg.getProperty("hibernate.connection.url") == null) {
//			cfg.setProperty("hibernate.connection.url", url);
//			cfg.setProperty("hibernate.connection.username", username);
//			cfg.setProperty("hibernate.connection.password", password);	
//		}
//		sf = cfg.buildSessionFactory();
//	}
//	public static Session getSession() {
//		return sf.openSession();
//	}
//}
