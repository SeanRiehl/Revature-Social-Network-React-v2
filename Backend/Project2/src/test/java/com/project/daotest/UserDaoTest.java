package com.project.daotest;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.project.dao.UserDAOImpl;
import com.project.util.HibernateUtil;

public class UserDaoTest {
	private static UserDAOImpl udi;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HibernateUtil hu = new HibernateUtil();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		udi = new UserDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
	}

}
