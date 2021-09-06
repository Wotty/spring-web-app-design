package com.fdmgroup.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Item;

public class ItemDAO {

	private EntityManager entityManager;
	private List<Item> listItems = new ArrayList<>();

	public void setEntityManager(EntityManager entityManager)	{  
		this.entityManager = entityManager;
	}
	public List<Item> listItems(){

		TypedQuery<Item> queryItems = entityManager.createQuery("SELECT s from Item s",Item.class);
		listItems = queryItems.getResultList();
		return listItems;
	}
	public void addItem(Item item) {
		if(entityManager.find(Item.class, item.getItemID()) == null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(item);
			entityTransaction.commit();
		}
	}
	public Item getItem(int itemID) {
		Item item = entityManager.find(Item.class, itemID);
		return item;
	}
	public void removeItem(int itemID) {
		Item itemInDB = entityManager.find(Item.class, itemID);
		if (itemInDB!=null){
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(itemInDB); // database
			entityTransaction.commit();
		}
	}
	public void removeAllItems() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<Item> queryItem = entityManager.createQuery("Delete from Item", Item.class);
		queryItem.executeUpdate();
		entityTransaction.commit();

	}
	public void updateItem(Item item) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(item); // database
		entityTransaction.commit();
	}
}
