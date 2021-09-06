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

import com.fdmgroup.daos.OrderDetailsDAO;
import com.fdmgroup.entities.Item;
import com.fdmgroup.entities.Order;
import com.fdmgroup.entities.OrderDetails;

public class OrderDetailsDAOTest {
	// include JUnit 4 in the pom
	// add the OrderDetails to the persistence.xml
	// generate the tables

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectweek4");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();


	@Before
	public void setup() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<OrderDetails> queryDelete = entityManager.createQuery("DELETE FROM OrderDetails",OrderDetails.class);
		queryDelete.executeUpdate();
		entityTransaction.commit();
	}
	@Test
	public void testThatWhenICallThelistOrderDetailsMethodInOrderDetailsDAOItReturnsAnEmptyList() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		List<OrderDetails> listOrderDetails = orderDetailsDAO.listOrderDetails();
		int listSize = listOrderDetails.size();
		assertEquals(0,listSize);
	}
	@Test // 2
	public void testThatWhenIAddOrderDetailsToAnEmptyListThatTheListContainsOneOrderDetails() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		orderDetails.setItem(item);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(3);
		item.setItemID(10);
		order.setOrderID(10);


		orderDetailsDAO.addOrderDetails(orderDetails);

		List<OrderDetails> listOrderDetails = orderDetailsDAO.listOrderDetails();
		int listSize = listOrderDetails.size();
		assertEquals(1,listSize);
	}

	@Test // 3
	public void testThatWhenIAddOrderDetailsToAnEmptyListThatTheGetOrderDetailsMethodReturnsTheOrderDetails() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		item.setItemID(10);
		order.setOrderID(10);
		orderDetails.setItem(item);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(23);
		orderDetailsDAO.addOrderDetails(orderDetails);

		OrderDetails orderDetailsInDB = orderDetailsDAO.listOrderDetails().get(0);
		int orderDetailsID = orderDetailsInDB.getOrder().getOrderID();
		assertEquals(orderDetails.getOrder().getOrderID(), orderDetailsID);
	}

	@Test // 4
	public void testThatWhenIAddOrderDetailsToAnEmptyListWhenICallRemoveOrderDetailsTheListIsEmpty() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		item.setItemID(10);
		order.setOrderID(10);
		orderDetails.setItem(item);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(3);
		orderDetailsDAO.addOrderDetails(orderDetails);

		orderDetailsDAO.removeOrderDetails(orderDetails.getOrder().getOrderID());

		List<OrderDetails> listOrderDetails = orderDetailsDAO.listOrderDetails();
		int listSize = listOrderDetails.size();
		assertEquals(0,listSize);
	}
	@Test //5
	public void testThatWhenIAddOrderDetailsToAnEmptyListWhenICallUpdateOrderDetailsTheOrderDetailsIsUpdated() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		orderDetails.setItem(item);
		item.setItemID(10);
		order.setOrderID(3);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(23);
		orderDetailsDAO.addOrderDetails(orderDetails);
		orderDetails.setOrderQty(3);
		orderDetailsDAO.updateOrderDetails(orderDetails);
		//Update user
		int orderID = orderDetails.getOrder().getOrderID();
		int itemID = orderDetails.getItem().getItemID();

		assertEquals(orderID,3);
		assertEquals(itemID,10);
	}
	@Test // 6 add a site user then remove all users then check there are no users
	public void testThatWhenIAddOrderDetailsToAnEmptyListThenRemoveAllOrderDetails() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		orderDetails.setItem(item);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(3);
		item.setItemID(10);
		order.setOrderID(10);

		orderDetailsDAO.addOrderDetails(orderDetails);
		orderDetailsDAO.removeAllOrderDetails();

		List<OrderDetails> listOrderDetails = orderDetailsDAO.listOrderDetails();
		int listSize = listOrderDetails.size();
		assertEquals(0,listSize);
	}
	@Test //7     // add a user with username a, remove a user with username b:
	public void testThatWhenIAddOrderDetailsAThenRemoveOrderDetailsB() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		item.setItemID(10);
		order.setOrderID(10);
		orderDetails.setItem(item);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(23);
		orderDetailsDAO.addOrderDetails(orderDetails);

		orderDetailsDAO.removeOrderDetails(24);
		List<OrderDetails> listOrderDetails = orderDetailsDAO.listOrderDetails();
		int listSize = listOrderDetails.size();
		assertEquals(1,listSize);
	}
	@Test //8    // add a site user with username a, add the same user again:
	public void testThatWhenIAddOrderDetailsAThenAddsAgain() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);

		OrderDetails orderDetails = new OrderDetails();
		Order order = new Order();
		Item item = new Item();
		item.setItemName("cup");
		item.setItemID(3);
		order.setOrderID(4);
		orderDetails.setItem(item);
		orderDetails.setOrder(order);
		orderDetails.setOrderQty(23);


		OrderDetails copyOrderDetails = new OrderDetails();
		copyOrderDetails.setOrderQty(323);
		Order ordercopy = new Order();
		Item itemcopy = new Item();
		itemcopy.setItemID(1);
		ordercopy.setOrderID(2);
		itemcopy.setItemName("sdjkfskjd");
		copyOrderDetails.setItem(itemcopy);
		copyOrderDetails.setOrder(ordercopy);

		orderDetailsDAO.addOrderDetails(orderDetails);
		orderDetailsDAO.addOrderDetails(copyOrderDetails);

		List<OrderDetails> listOrderDetails = orderDetailsDAO.listOrderDetails();
		OrderDetails checkOrderDetails = listOrderDetails.get(0);

		assertEquals(orderDetails.toString(),checkOrderDetails.toString());
	}
	@Test //9  // get a user with a username of a -> returns null
	public void testThatWhenIRemoveOrderDetailsNullIsReturned() {
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		orderDetailsDAO.setEntityManager(entityManager);
		assertNull(null, entityManager.find(OrderDetails.class, 1));	
	}
}
