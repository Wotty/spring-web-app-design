package com.fdmgroup.controllers;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.fdmgroup.entities.Item;
import com.fdmgroup.entities.Order;
import com.fdmgroup.entities.OrderDetails;
import com.fdmgroup.entities.Payment;
import com.fdmgroup.entities.SiteUser;
public class Runner {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("finalproject");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		//Item
		Item item1 = new Item();
		item1.setItemID(1);
		item1.setItemName("Dress");
		item1.setDescription("This is a Dress");
		item1.setPrice(9.99);
		Item item2 = new Item();
		item2.setItemID(2);
		item2.setItemName("Shirt");
		item2.setDescription("This is a Shirt");
		item2.setPrice(7.99);
		Item item3 = new Item();
		item3.setItemID(3);
		item3.setItemName("T-shirt");
		item3.setDescription("This is a T-shirt");
		item3.setPrice(9.99);
		Item item4 = new Item();
		item4.setItemID(4);
		item4.setItemName("Socks");
		item4.setDescription("This is Socks");
		item4.setPrice(7.99);
		Item item5 = new Item();
		item5.setItemID(5);
		item5.setItemName("Pants");
		item5.setDescription("This is Pants");
		item5.setPrice(4.99);

		//SiteUser Entities
		SiteUser siteUser1 = new SiteUser();
		siteUser1.setUsername("jdk");
		siteUser1.setAddress("1 street");
		siteUser1.setEmail("jo@doe.com");
		siteUser1.setFirstName("Joe");
		siteUser1.setLastName("Doe");
		siteUser1.setPassword("asdf");
		siteUser1.setPostcode("RG11AA");

		SiteUser siteUser2 = new SiteUser();
		siteUser2.setUsername("doe");
		siteUser2.setAddress("1 street");
		siteUser2.setEmail("jade@doe.com");
		siteUser2.setFirstName("Jack");
		siteUser2.setLastName("Doe");
		siteUser2.setPassword("asdf");
		siteUser2.setPostcode("RG11AA");

		SiteUser siteUser3 = new SiteUser();
		siteUser3.setUsername("jade");
		siteUser3.setAddress("1 street");
		siteUser3.setEmail("jade@doe.com");
		siteUser3.setFirstName("Joe");
		siteUser3.setLastName("Doe");
		siteUser3.setPassword("asdf");
		siteUser3.setPostcode("RG11AA");

		SiteUser siteUser4 = new SiteUser();
		siteUser4.setUsername("jade2");
		siteUser4.setAddress("1 street");
		siteUser4.setEmail("jade@doe.com");
		siteUser4.setFirstName("Joe");
		siteUser4.setLastName("Doe");
		siteUser4.setPassword("asdf");
		siteUser4.setPostcode("RG11AA");

		SiteUser siteUser5 = new SiteUser();
		siteUser5.setUsername("jade3");
		siteUser5.setAddress("1 street");
		siteUser5.setEmail("jade@doe.com");
		siteUser5.setFirstName("Joe");
		siteUser5.setLastName("Doe");
		siteUser5.setPassword("asdf");
		siteUser5.setPostcode("RG11AA");

		//Payment
		Payment payment1 = new Payment();
		payment1.setSiteUser(siteUser1);
		payment1.setCardCSV("234");
		payment1.setCardExpiry("2021-07-09");
		payment1.setCardNumber("000000000000000000");
		payment1.setPaymentDate("2021-01-10");
		Payment payment2 = new Payment();
		payment2.setSiteUser(siteUser1);
		payment2.setCardCSV("234");
		payment2.setCardExpiry("2021-07-09");
		payment2.setCardNumber("000000000000000001");
		payment2.setPaymentDate("2021-07-09");
		Payment payment3 = new Payment();
		payment3.setSiteUser(siteUser1);
		payment3.setCardCSV("334");
		payment3.setCardExpiry("2021-07-09");
		payment3.setCardNumber("000000000000000002");
		payment3.setPaymentDate("2021-07-09");
		Payment payment4 = new Payment();
		payment4.setSiteUser(siteUser1);
		payment4.setCardCSV("444");
		payment4.setCardExpiry("2021-07-09");
		payment4.setCardNumber("000000000000000003");
		payment4.setPaymentDate("2021-07-09");
		Payment payment5 = new Payment();
		payment5.setSiteUser(siteUser1);
		payment5.setCardCSV("555");
		payment5.setCardExpiry("2021-07-09");
		payment5.setCardNumber("000000000000000004");
		payment5.setPaymentDate("2021-07-09");


		//Order
		Order orders1 = new Order();
		orders1.setOrderID(1);
		orders1.setSiteUser(siteUser1);
		orders1.setOrderDate("2021-07-09");
		Order orders2 = new Order();
		orders2.setOrderID(2);
		orders2.setSiteUser(siteUser1);
		orders2.setOrderDate("2021-07-09");
		Order orders3 = new Order();
		orders3.setOrderID(3);
		orders3.setSiteUser(siteUser2);
		orders3.setOrderDate("2021-07-09");
		Order orders4 = new Order();
		orders4.setOrderID(4);
		orders4.setSiteUser(siteUser3);
		orders4.setOrderDate("2021-07-09");
		Order orders5 = new Order();
		orders5.setOrderID(5);
		orders5.setSiteUser(siteUser3);
		orders5.setOrderDate("2021-07-09");
		Order orders6 = new Order();
		orders6.setOrderID(6);
		orders6.setSiteUser(siteUser4);
		orders6.setOrderDate("2021-07-09");
		Order orders7 = new Order();
		orders7.setOrderID(7);
		orders7.setSiteUser(siteUser5);
		orders7.setOrderDate("2021-07-09");
		Order orders8 = new Order();
		orders8.setOrderID(8);
		orders8.setSiteUser(siteUser5);
		orders8.setOrderDate("2021-07-09");

		//Ordered Item Details Entity
		OrderDetails orderedItemDetails1 = new OrderDetails();
		orderedItemDetails1.setOrder(orders1);
		orderedItemDetails1.setItem(item1);
		orderedItemDetails1.setOrderQty(2);
		OrderDetails orderedItemDetails2 = new OrderDetails();
		orderedItemDetails2.setOrder(orders2);
		orderedItemDetails2.setItem(item4);
		orderedItemDetails2.setOrderQty(3);
		OrderDetails orderedItemDetails3 = new OrderDetails();
		orderedItemDetails3.setOrder(orders3);
		orderedItemDetails3.setItem(item1);
		orderedItemDetails3.setOrderQty(10);
		OrderDetails orderedItemDetails4 = new OrderDetails();
		orderedItemDetails4.setOrder(orders4);
		orderedItemDetails4.setItem(item3);
		orderedItemDetails4.setOrderQty(5);
		OrderDetails orderedItemDetails5 = new OrderDetails();
		orderedItemDetails5.setOrder(orders5);
		orderedItemDetails5.setItem(item4);
		orderedItemDetails5.setOrderQty(12);
		OrderDetails orderedItemDetails6 = new OrderDetails();
		orderedItemDetails6.setOrder(orders6);
		orderedItemDetails6.setItem(item3);
		orderedItemDetails6.setOrderQty(7);
		OrderDetails orderedItemDetails7 = new OrderDetails();
		orderedItemDetails7.setOrder(orders7);
		orderedItemDetails7.setItem(item1);
		orderedItemDetails7.setOrderQty(10);
		OrderDetails orderedItemDetails8 = new OrderDetails();
		orderedItemDetails8.setOrder(orders8);
		orderedItemDetails8.setItem(item5);
		orderedItemDetails8.setOrderQty(12);

		//Interface used to control transactions on resource-local Entity Managers.
		entityTransaction.begin();

		TypedQuery<OrderDetails> itemQuery1 = entityManager.createQuery("DELETE FROM OrderDetails", OrderDetails.class);
		itemQuery1.executeUpdate();

		TypedQuery<Item> itemQuery2 = entityManager.createQuery("DELETE FROM Item", Item.class);
		itemQuery2.executeUpdate();

		TypedQuery<Order> itemQuery3 = entityManager.createQuery("DELETE FROM order_table", Order.class);
		itemQuery3.executeUpdate();

		TypedQuery<Payment> itemQuery4 = entityManager.createQuery("DELETE FROM Payment", Payment.class);
		itemQuery4.executeUpdate();

		TypedQuery<SiteUser> itemQuery5 = entityManager.createQuery("DELETE FROM SiteUser", SiteUser.class);
		itemQuery5.executeUpdate();


		entityManager.persist(item1);
		entityManager.persist(item2);
		entityManager.persist(item3);
		entityManager.persist(item4);
		entityManager.persist(item5);

		entityManager.persist(siteUser1);
		entityManager.persist(siteUser2);
		entityManager.persist(siteUser3);
		entityManager.persist(siteUser4);
		entityManager.persist(siteUser5);

		entityManager.persist(payment1);
		entityManager.persist(payment2);
		entityManager.persist(payment3);
		entityManager.persist(payment4);
		entityManager.persist(payment5);


		entityManager.persist(orders1);
		entityManager.persist(orders2);
		entityManager.persist(orders3);
		entityManager.persist(orders4);
		entityManager.persist(orders5);
		entityManager.persist(orders6);
		entityManager.persist(orders7);
		entityManager.persist(orders8);

		entityManager.persist(orderedItemDetails1);
		entityManager.persist(orderedItemDetails2);
		entityManager.persist(orderedItemDetails3);
		entityManager.persist(orderedItemDetails4);
		entityManager.persist(orderedItemDetails5);
		entityManager.persist(orderedItemDetails6);
		entityManager.persist(orderedItemDetails7);
		entityManager.persist(orderedItemDetails8);

		entityTransaction.commit();
		entityManager.close();
	}
}	