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

import com.fdmgroup.daos.PaymentDAO;
import com.fdmgroup.entities.Payment;

public class PaymentDAOTest {
	// include JUnit 4 in the pom
	// add the Payment to the persistence.xml
	// generate the tables

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("projectweek4");

	private EntityManager entityManager = entityManagerFactory.createEntityManager();


	@Before
	public void setup() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<Payment> queryDelete = entityManager.createQuery("DELETE FROM Payment",Payment.class);
		queryDelete.executeUpdate();
		entityTransaction.commit();
	}
	@Test
	public void testThatWhenICallThelistPaymentsMethodInPaymentDAOItReturnsAnEmptyList() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		List<Payment> listPayments = paymentDAO.listPayments("Will");
		int listSize = listPayments.size();
		assertEquals(0,listSize);
	}
	@Test // 2
	public void testThatWhenIAddPaymentToAnEmptyListThatTheListContainsOnePayment() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		paymentDAO.addPayment(payment);

		List<Payment> listPayments = paymentDAO.listPayments("Will");
		int listSize = listPayments.size();
		assertEquals(1,listSize);
	}

	@Test // 3
	public void testThatWhenIAddPaymentToAnEmptyListThatTheGetPaymentMethodReturnsThePayment() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		paymentDAO.addPayment(payment);

		Payment paymentInDB = paymentDAO.listPayments("Will").get(0);
		int paymentID = paymentInDB.getPaymentID();
		assertEquals(payment.getPaymentID(), paymentID);
	}

	@Test // 4
	public void testThatWhenIAddPaymentToAnEmptyListWhenICallRemovePaymentTheListIsEmpty() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		paymentDAO.addPayment(payment);

		paymentDAO.removePayment(payment.getPaymentID());

		List<Payment> listPayments = paymentDAO.listPayments("Will");
		int listSize = listPayments.size();
		assertEquals(0,listSize);
	}
	@Test //5
	public void testThatWhenIAddPaymentToAnEmptyListWhenICallUpdatePaymentThePaymentIsUpdated() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		payment.setPaymentDate("01-01-2021");
		paymentDAO.addPayment(payment);

		Date paymentDate = new Date(312345253456L);
		payment.setPaymentDate(paymentDate);
		paymentDAO.updatePayment(payment);
		//Update user

		Payment paymentInDB = paymentDAO.getPayment(payment.getPaymentID());
		Date dateInDB = paymentInDB.getPaymentDate();
		assertEquals(paymentDate,dateInDB);
	}
	@Test // 6 add a site user then remove all users then check there are no users
	public void testThatWhenIAddPaymentToAnEmptyListThenRemoveAllPayments() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		payment.setPaymentDate("01-01-2020");
		paymentDAO.addPayment(payment);
		paymentDAO.removeAllPayments();

		List<Payment> listPayments = paymentDAO.listPayments("Will");
		int listSize = listPayments.size();
		assertEquals(0,listSize);
	}
	@Test //7     // add a user with username a, remove a user with username b:
	public void testThatWhenIAddPaymentAThenRemovePaymentB() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		paymentDAO.addPayment(payment);

		paymentDAO.removePayment(24);
		List<Payment> listPayments = paymentDAO.listPayments("Will");
		int listSize = listPayments.size();
		assertEquals(1,listSize);
	}
	@Test //8    // add a site user with username a, add the same user again:
	public void testThatWhenIAddPaymentAThenAddsAgain() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);

		Payment payment = new Payment();
		payment.setCardCSV("234");
		paymentDAO.addPayment(payment);

		Payment copyPayment = new Payment();
		copyPayment.setCardCSV("234");
		paymentDAO.addPayment(copyPayment);

		List<Payment> listPayments = paymentDAO.listPayments("Will");
		Payment checkPayment = listPayments.get(0);
		assertEquals(payment,checkPayment);
	}
	@Test //9  // get a user with a username of a -> returns null
	public void testThatWhenIRemovePaymentNullIsReturned() {
		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setEntityManager(entityManager);
		assertNull(null, entityManager.find(Payment.class, 1));	
	}
}
