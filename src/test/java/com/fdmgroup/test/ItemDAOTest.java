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

import com.fdmgroup.daos.ItemDAO;
import com.fdmgroup.entities.Item;

public class ItemDAOTest {
	// include JUnit 4 in the pom
	// add the Item to the persistence.xml
	// generate the tables

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectweek4");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();


	@Before
	public void setup() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<Item> queryDelete = entityManager.createQuery("DELETE FROM Item",Item.class);
		queryDelete.executeUpdate();
		entityTransaction.commit();
	}
	@Test
	public void testThatWhenICallThelistItemsMethodInItemDAOItReturnsAnEmptyList() {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		List<Item> listItems = itemDAO.listItems();
		int listSize = listItems.size();
		assertEquals(0,listSize);
	}
	@Test // 2
	public void testThatWhenIAddItemToAnEmptyListThatTheListContainsOneItem() {



		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(1);
		itemDAO.addItem(item);

		List<Item> listItems = itemDAO.listItems();
		int listSize = listItems.size();
		assertEquals(1,listSize);
	}

	@Test // 3
	public void testThatWhenIAddItemToAnEmptyListThatTheGetItemMethodReturnsTheItem() {



		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(10);
		itemDAO.addItem(item);

		Item itemInDB = itemDAO.listItems().get(0);
		int itemID = itemInDB.getItemID();
		assertEquals(10, itemID);
	}

	@Test // 4
	public void testThatWhenIAddItemToAnEmptyListWhenICallRemoveItemTheListIsEmpty() {
		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(1);
		itemDAO.addItem(item);

		itemDAO.removeItem(1);

		List<Item> listItems = itemDAO.listItems();
		int listSize = listItems.size();
		assertEquals(0,listSize);
	}
	@Test //5
	public void testThatWhenIAddItemToAnEmptyListWhenICallUpdateItemTheItemIsUpdated() {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(1);
		item.setDescription("this is an item");
		itemDAO.addItem(item);
		
		item.setDescription("this is not an item");
		itemDAO.updateItem(item);
		//Update user

		Item itemInDB = itemDAO.getItem(1);
		String descInDB = itemInDB.getDescription();
		assertEquals("this is not an item",descInDB);
	}
	@Test // 6 add a site user then remove all users then check there are no users
	public void testThatWhenIAddItemToAnEmptyListThenRemoveAllItems() {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(13);
		item.setDescription("this is an item");
		itemDAO.addItem(item);
		itemDAO.removeAllItems();

		List<Item> listItems = itemDAO.listItems();
		int listSize = listItems.size();
		assertEquals(0,listSize);
	}
	@Test //7     // add a user with item a, remove a user with item b:
	public void testThatWhenIAddItemAThenRemoveItemB() {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(1);
		itemDAO.addItem(item);

		itemDAO.removeItem(2);
		List<Item> listItems = itemDAO.listItems();
		int listSize = listItems.size();
		assertEquals(1,listSize);
	}
	@Test //8    // add a site user with item a, add the same item again:
	public void testThatWhenIAddItemAThenAddsAgain() {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);

		Item item = new Item();
		item.setItemID(1);
		itemDAO.addItem(item);

		Item copyItem = new Item();
		copyItem.setItemID(1);
		itemDAO.addItem(copyItem);

		List<Item> listItems = itemDAO.listItems();
		Item checkItem = listItems.get(0);
		assertEquals(item,checkItem);
	}
	@Test //9        // get a user with a username of a -> returns null
	public void testThatWhenIRemoveItemNullIsReturned() {

		ItemDAO itemDAO = new ItemDAO();
		itemDAO.setEntityManager(entityManager);
		assertNull(null, entityManager.find(Item.class, 1));	
	}
}
