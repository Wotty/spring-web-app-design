package com.fdmgroup.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Order;

public class OrderDAO {

	private EntityManager entityManager;
	private List<Order> listOrders = new ArrayList<>();

	public void setEntityManager(EntityManager entityManager)	{  
		this.entityManager = entityManager;
	}
	public List<Order> listOrders(){

		TypedQuery<Order> queryOrders = entityManager.createQuery("SELECT o from order_table o",Order.class);
		listOrders = queryOrders.getResultList();
		return listOrders;
	}
	public void addOrder(Order order) {
		if(entityManager.find(Order.class, order.getOrderID()) == null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(order);
			entityTransaction.commit();
		}
	}
	public Order getOrder(int orderID) {
		Order order = entityManager.find(Order.class, orderID);
		return order;
	}
	public void removeOrder(int orderID) {
		Order orderInDB = entityManager.find(Order.class, orderID);
		if (orderInDB!=null){
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(orderInDB); // database
			entityTransaction.commit();
		}
	}
	public void removeAllOrders() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<Order> queryOrder = entityManager.createQuery("Delete from Order", Order.class);
		queryOrder.executeUpdate();
		entityTransaction.commit();

	}
	public void updateOrder(Order order) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(order); // database
		entityTransaction.commit();
	}
}
