package com.fdmgroup.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.daos.OrderDAO;
import com.fdmgroup.entities.Order;

public class OrderDAOTest {
	// include JUnit 4 in the pom
	// add the Order to the persistence.xml
	// generate the tables

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectweek4");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();


	@Before
	public void setup() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<Order> queryDelete = entityManager.createQuery("DELETE FROM Order",Order.class);
		queryDelete.executeUpdate();
		entityTransaction.commit();
	}
	@Test
	public void testThatWhenICallThelistOrdersMethodInOrderDAOItReturnsAnEmptyList() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		List<Order> listOrders = orderDAO.listOrders();
		int listSize = listOrders.size();
		assertEquals(0,listSize);
	}
	@Test // 2
	public void testThatWhenIAddOrderToAnEmptyListThatTheListContainsOneOrder() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		

		Order order = new Order();
		order.setOrderID(1);
		orderDAO.addOrder(order);

		List<Order> listOrders = orderDAO.listOrders();
		int listSize = listOrders.size();
		assertEquals(1,listSize);
	}

	@Test // 3
	public void testThatWhenIAddOrderToAnEmptyListThatTheGetOrderMethodReturnsTheOrder() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		Order order = new Order();
		order.setOrderID(10);
		orderDAO.addOrder(order);

		Order orderInDB = orderDAO.listOrders().get(0);
		int orderID = orderInDB.getOrderID();
		assertEquals(10, orderID);
	}

	@Test // 4
	public void testThatWhenIAddOrderToAnEmptyListWhenICallRemoveOrderTheListIsEmpty() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		Order order = new Order();
		order.setOrderID(1);
		orderDAO.addOrder(order);

		orderDAO.removeOrder(1);

		List<Order> listOrders = orderDAO.listOrders();
		int listSize = listOrders.size();
		assertEquals(0,listSize);
	}
	@Test //5
	public void testThatWhenIAddOrderToAnEmptyListWhenICallUpdateOrderTheOrderIsUpdated() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		Order order = new Order();
		order.setOrderID(1);
		order.setOrderDate(12345253456L);
		orderDAO.addOrder(order);

		Date orderDate = new Date(312345253456L);
		order.setOrderDate(orderDate);
		orderDAO.updateOrder(order);
		//Update user

		Order orderInDB = orderDAO.getOrder(1);
		Date dateInDB = orderInDB.getOrderDate();
		assertEquals(orderDate,dateInDB);
	}
	@Test // 6 add a site user then remove all users then check there are no users
	public void testThatWhenIAddOrderToAnEmptyListThenRemoveAllOrders() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		Order order = new Order();
		order.setOrderID(13);
		long timestamp = 1532576392010L;
		order.setOrderDate(timestamp);
		orderDAO.addOrder(order);
		orderDAO.removeAllOrders();

		List<Order> listOrders = orderDAO.listOrders();
		int listSize = listOrders.size();
		assertEquals(0,listSize);
	}
	@Test //7     // add a user with username a, remove a user with username b:
	public void testThatWhenIAddOrderAThenRemoveOrderB() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		Order order = new Order();
		order.setOrderID(1);
		orderDAO.addOrder(order);

		orderDAO.removeOrder(2);
		List<Order> listOrders = orderDAO.listOrders();
		int listSize = listOrders.size();
		assertEquals(-1,listSize);
	}
	@Test //8    // add a site user with username a, add the same user again:
	public void testThatWhenIAddOrderAThenAddsAgain() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);

		Order order = new Order();
		order.setOrderID(1);
		orderDAO.addOrder(order);

		Order copyOrder = new Order();
		copyOrder.setOrderID(1);
		orderDAO.addOrder(copyOrder);

		List<Order> listOrders = orderDAO.listOrders();
		Order checkOrder = listOrders.get(0);
		assertEquals(order,checkOrder);
	}
	@Test //9        // get a user with a username of a -> returns null
	public void testThatWhenIRemoveOrderNullIsReturned() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setEntityManager(entityManager);
		assertNull(null, entityManager.find(Order.class, 1));	
	}
}
