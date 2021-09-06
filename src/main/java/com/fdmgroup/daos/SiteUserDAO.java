package com.fdmgroup.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.SiteUser;


public class SiteUserDAO {

	private EntityManager entityManager;
	private List<SiteUser> listUsers = new ArrayList<>();

	public void setEntityManager(EntityManager entityManager)	{  
		this.entityManager = entityManager;
	}
	public List<SiteUser> listUsers(){

		TypedQuery<SiteUser> queryUsers = entityManager.createQuery("SELECT s from SiteUser s",SiteUser.class);
		listUsers = queryUsers.getResultList();
		return listUsers;
	}
	public void addUser(SiteUser siteUser) {
		if(entityManager.find(SiteUser.class, siteUser.getUsername()) == null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(siteUser);
			entityTransaction.commit();
		}
	}
	public SiteUser getUser(String username) {
		SiteUser siteUser = entityManager.find(SiteUser.class, username);
		return siteUser;
	}
	public void removeUser(String username) {
		SiteUser siteUserInDB = entityManager.find(SiteUser.class, username);
		if (siteUserInDB!=null){
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(siteUserInDB); // database
			entityTransaction.commit();
		}
	}
	public void removeAllUsers() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<SiteUser> querySiteUser = entityManager.createQuery("Delete from SiteUser", SiteUser.class);
		querySiteUser.executeUpdate();
		entityTransaction.commit();

	}
	public void updateUser(SiteUser siteUser) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(siteUser); // database
		entityTransaction.commit();
	}
}
