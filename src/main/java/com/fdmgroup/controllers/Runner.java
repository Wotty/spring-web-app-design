package com.fdmgroup.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Item;
import com.fdmgroup.entities.Payment;
import com.fdmgroup.entities.SiteUser;

public class Runner {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("finalproject");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Item item1 = new Item();
		item1.setItemID(1);
		item1.setItemName("Dress");
		item1.setDescription("This is a Dress");
		item1.setPrice(9.99);

		Item item2 = new Item();
		item2.setItemID(2);
		item2.setItemName("Shirt");
		item1.setDescription("This is a Shirt");
		item2.setPrice(7.99);

		Item item3 = new Item();
		item3.setItemID(3);
		item3.setItemName("T-shirt");
		item1.setDescription("This is a T-shirt");
		item3.setPrice(9.99);

		Item item4 = new Item();
		item2.setItemID(4);
		item2.setItemName("Socks");
		item1.setDescription("This is Socks");
		item2.setPrice(7.99);

		Item item5 = new Item();
		item3.setItemID(5);
		item3.setItemName("Pants");
		item1.setDescription("This is Pants");
		item3.setPrice(4.99);


		//SiteUser Entity
		SiteUser siteUser1 = new SiteUser();
		siteUser1.setUsername("Will");
		siteUser1.setPassword("will");
		siteUser1.setFirstName("Will");
		siteUser1.setLastName("Otty");
		siteUser1.setEmail("william.otty@gmail.com");
		siteUser1.setPostcode("RG1 1AA");


		SiteUser siteUser2 = new SiteUser();
		siteUser2.setUsername("Arsalaan");
		siteUser2.setPassword("arsalaan");
		siteUser2.setFirstName("Arsalaan");
		siteUser2.setLastName("Naeem");
		siteUser2.setEmail("a.naeem@fdmgroup.com");
		siteUser2.setPostcode("RG1 5AA");


		SiteUser siteUser3 = new SiteUser();
		siteUser3.setUsername("Muhammad");
		siteUser3.setPassword("muhammad");
		siteUser3.setFirstName("Muhammad");
		siteUser3.setLastName("Patia");
		siteUser3.setEmail("m.patia@outlook.co.uk");
		siteUser3.setPostcode("RG1 4AA");

		SiteUser siteUser4 = new SiteUser();
		siteUser4.setUsername("Amanda");
		siteUser4.setPassword("amanda");
		siteUser4.setFirstName("Amanda");
		siteUser4.setLastName("Yuen");
		siteUser4.setEmail("a.yuen@fdmgroup.com");
		siteUser4.setPostcode("RG1 3AA");

		SiteUser siteUser5 = new SiteUser();
		siteUser5.setUsername("ashwini123");
		siteUser5.setPassword("ash321");
		siteUser5.setFirstName("Ashwini");
		siteUser5.setLastName("Aruleswaran");
		siteUser5.setEmail("a.Aruleswaran@fdmgroup.com");
		siteUser5.setPostcode("RG1 2AA");

		//Payment
		Payment payment1 = new Payment();
		payment1.setSiteUser(siteUser1);
		payment1.setCardCSV("234");
		payment1.setCardExpiry("01-01-23");
		payment1.setCardNumber("000000000000000000");
		payment1.setPaymentDate("01-01-23");

		Payment payment2 = new Payment();
		payment2.setSiteUser(siteUser1);
		payment2.setCardCSV("234");
		payment2.setCardExpiry("01-01-23");
		payment2.setCardNumber("000000000000000001");
		payment2.setPaymentDate("01-01-23");

		Payment payment3 = new Payment();
		payment3.setSiteUser(siteUser1);
		payment3.setCardCSV("334");
		payment3.setCardExpiry("01-01-23");
		payment3.setCardNumber("000000000000000002");
		payment3.setPaymentDate("01-01-33");


		Payment payment4 = new Payment();
		payment4.setSiteUser(siteUser1);
		payment4.setCardCSV("444");
		payment4.setCardExpiry("01-01-24");
		payment4.setCardNumber("000000000000000003");
		payment4.setPaymentDate("01-01-44");

		Payment payment5 = new Payment();
		payment5.setSiteUser(siteUser1);
		payment5.setCardCSV("555");
		payment5.setCardExpiry("01-01-25");
		payment5.setCardNumber("000000000000000004");
		payment5.setPaymentDate("01-01-35");

		entityTransaction.begin();

		TypedQuery<Item> itemQuery = entityManager.createQuery("DELETE FROM Item", Item.class);
		itemQuery.executeUpdate();

		TypedQuery<SiteUser> querySiteUser = entityManager.createQuery("Delete from Users", SiteUser.class);
		querySiteUser.executeUpdate();

		entityManager.persist(item1);
		entityManager.persist(item2);
		entityManager.persist(item3);
		entityManager.persist(item4);
		entityManager.persist(item5);

		entityTransaction.commit();
		entityManager.close();
		
	}
}	