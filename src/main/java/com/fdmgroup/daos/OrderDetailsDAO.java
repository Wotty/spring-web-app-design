package com.fdmgroup.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Item;
import com.fdmgroup.entities.Order;
import com.fdmgroup.entities.OrderDetails;

public class OrderDetailsDAO {

	private EntityManager entityManager;
	private List<OrderDetails> listOrderDetails = new ArrayList<>();

	public void setEntityManager(EntityManager entityManager)	{  
		this.entityManager = entityManager;
	}
	public List<OrderDetails> listOrderDetails(){
		TypedQuery<OrderDetails> queryOrderDetails = entityManager.createQuery("SELECT s from OrderDetails s",OrderDetails.class);
		listOrderDetails = queryOrderDetails.getResultList();
		return listOrderDetails;
	}
	public void addOrderDetails(OrderDetails orderDetails) {
		Order order = orderDetails.getOrder();
		Item item = orderDetails.getItem();

		TypedQuery<OrderDetails> query = entityManager.createQuery("select o from OrderDetails o where o.order.orderID=:orderID and o.item.itemID=:itemID", OrderDetails.class);
		query.setParameter("orderID", order.getOrderID());
		query.setParameter("itemID", item.getItemID());

		List<OrderDetails> listOrderDetails = query.getResultList();

		if(listOrderDetails.size()==0) {

			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(orderDetails);
			entityTransaction.commit();

		}
	}
	public List<OrderDetails> getOrderDetails(int orderID) {

		TypedQuery<OrderDetails> queryOrderDetails = entityManager.createQuery("select o from OrderDetails o where o.order.orderID=:orderID",OrderDetails.class);
		queryOrderDetails.setParameter("orderID", orderID);
		List<OrderDetails> listOrderDetails = queryOrderDetails.getResultList();
		if(listOrderDetails.size()!=0) {
			return listOrderDetails;
		}
		return null;
	}
	public void removeOrderDetails(int orderID) {
		OrderDetails orderDetailsInDB = entityManager.find(OrderDetails.class, orderID);
		if (orderDetailsInDB!=null){
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(orderDetailsInDB); // database
			entityTransaction.commit();
		}
	}
	public void removeAllOrderDetails() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<OrderDetails> queryOrderDetails = entityManager.createQuery("Delete from OrderDetails", OrderDetails.class);
		queryOrderDetails.executeUpdate();
		entityTransaction.commit();

	}
	public void updateOrderDetails(OrderDetails orderDetails) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(orderDetails); // database
		entityTransaction.commit();
	}
}
