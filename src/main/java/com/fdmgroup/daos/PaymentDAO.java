package com.fdmgroup.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.entities.Payment;

public class PaymentDAO {

	private EntityManager entityManager;
	private List<Payment> listPayments = new ArrayList<>();

	public void setEntityManager(EntityManager entityManager) {  
		this.entityManager = entityManager;
	}
	public List<Payment> listPayments(String user){
		TypedQuery<Payment> queryPayments = entityManager.createQuery("SELECT p FROM Payment p where p.siteUser.username=:username",Payment.class);
		queryPayments.setParameter("username", user);
		listPayments = queryPayments.getResultList();
		
		return listPayments;
	}
	public void addPayment(Payment sitePayment) {
		if(entityManager.find(Payment.class, sitePayment.getPaymentID()) == null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(sitePayment);
			entityTransaction.commit();
		}
	}
	public Payment getPayment(int paymentID) {
		Payment sitePayment = entityManager.find(Payment.class, paymentID);
		return sitePayment;
	}
	public void removePayment(int paymentID) {
		Payment sitePaymentInDB = entityManager.find(Payment.class, paymentID);
		if (sitePaymentInDB!=null){
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(sitePaymentInDB); // database
			entityTransaction.commit();
		}else {
			System.err.println("Error");
		}
	}
	public void removeAllPayments() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		TypedQuery<Payment> queryPayment = entityManager.createQuery("Delete from Payment", Payment.class);
		queryPayment.executeUpdate();
		entityTransaction.commit();

	}
	public void updatePayment(Payment sitePayment) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(sitePayment); // database
		entityTransaction.commit();
	}
}
