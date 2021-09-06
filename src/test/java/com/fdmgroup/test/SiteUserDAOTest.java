package com.fdmgroup.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.daos.SiteUserDAO;
import com.fdmgroup.entities.SiteUser;

public class SiteUserDAOTest {
	// include JUnit 4 in the pom
	// add the SiteUser to the persistence.xml
	// generate the tables

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectweek4");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();


	@Before
	public void setup() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<SiteUser> queryDelete = entityManager.createQuery("DELETE FROM SiteUser",SiteUser.class);
		queryDelete.executeUpdate();
		entityTransaction.commit();
	}
	@Test
	public void testThatWhenICallThelistUsersMethodInUserDAOItReturnsAnEmptyList() {
		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		List<SiteUser> listUsers = siteUserDAO.listUsers();
		int listSize = listUsers.size();
		assertEquals(0,listSize);
	}
	@Test // 2
	public void testThatWhenIAddUserToAnEmptyListThatTheListContainsOneUser() {
		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("abcd");
		siteUserDAO.addUser(siteUser);

		List<SiteUser> listUsers = siteUserDAO.listUsers();
		int listSize = listUsers.size();
		assertEquals(1,listSize);
	}

	@Test // 3
	public void testThatWhenIAddUserToAnEmptyListThatTheGetUserMethodReturnsTheUser() {
		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("abcd");
		siteUserDAO.addUser(siteUser);

		SiteUser siteUserInDB = siteUserDAO.getUser("abcd");
		String usernameInDB = siteUserInDB.getUsername();
		assertEquals("abcd", usernameInDB);
	}

	@Test // 4
	public void testThatWhenIAddUserToAnEmptyListWhenICallRemoveUserTheListIsEmpty() {
		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("abcd");
		siteUserDAO.addUser(siteUser);

		siteUserDAO.removeUser("abcd");

		List<SiteUser> listUsers = siteUserDAO.listUsers();
		int listSize = listUsers.size();
		assertEquals(0,listSize);
	}
	@Test //5
	public void testThatWhenIAddUserToAnEmptyListWhenICallUpdateUserTheUserIsUpdated() {

		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("A");
		siteUser.setPassword("lamo");
		siteUserDAO.addUser(siteUser);

		siteUser.setPassword("newpassword");
		siteUserDAO.updateUser(siteUser);
		//Update user

		SiteUser siteUserInDB = siteUserDAO.getUser("A");
		String passwordInDB = siteUserInDB.getPassword();
		assertEquals("newpassword",passwordInDB);
	}
	@Test // 6 add a site user then remove all users then check there are no users
	public void testThatWhenIAddUserToAnEmptyListThenRemoveAllUsers() {

		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("A");
		siteUser.setPassword("lamo");
		siteUserDAO.addUser(siteUser);
		siteUserDAO.removeAllUsers();

		List<SiteUser> listUsers = siteUserDAO.listUsers();
		int listSize = listUsers.size();
		assertEquals(0,listSize);
	}
	@Test //7     // add a user with username a, remove a user with username b:
	public void testThatWhenIAddUserAThenRemoveUserB() {

		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("abcd");
		siteUserDAO.addUser(siteUser);

		siteUserDAO.removeUser("B");
		List<SiteUser> listUsers = siteUserDAO.listUsers();
		int listSize = listUsers.size();
		assertEquals(1,listSize);
	}
	@Test //8    // add a site user with username a, add the same user again:
	public void testThatWhenIAddUserAThenAddsAgain() {

		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);

		SiteUser siteUser = new SiteUser();
		siteUser.setUsername("abcd");
		siteUser.setPassword("check");
		siteUserDAO.addUser(siteUser);

		SiteUser copySiteUser = new SiteUser();
		copySiteUser.setUsername("abcd");
		siteUserDAO.addUser(copySiteUser);

		List<SiteUser> listUsers = siteUserDAO.listUsers();
		SiteUser checkSiteUser = listUsers.get(0);
		assertEquals(siteUser,checkSiteUser);
	}
	@Test //9        // get a user with a username of a -> returns null
	public void testThatWhenIRemoveUserNullIsReturned() {

		SiteUserDAO siteUserDAO = new SiteUserDAO();
		siteUserDAO.setEntityManager(entityManager);
		assertNull(null, entityManager.find(SiteUser.class, "a"));	
	}
}
