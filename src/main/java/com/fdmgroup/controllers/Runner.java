package com.fdmgroup.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Item;

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

		entityTransaction.begin();

		TypedQuery<Item> itemQuery = entityManager.createQuery("DELETE FROM Item", Item.class);
		itemQuery.executeUpdate();

		entityManager.persist(item1);
		entityManager.persist(item2);
		entityManager.persist(item3);
		entityManager.persist(item4);
		entityManager.persist(item5);

		entityTransaction.commit();
		entityManager.close();
	}
}
