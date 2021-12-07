package com.fdmgroup.entities;

import java.sql.Date;
import javax.persistence.*;

@Entity

public class Payment {

	@Id
	@GeneratedValue
	private int paymentID;
	private Date paymentDate;
	private String cardNumber;
	private String cardCVC;
	private Date cardExpiry;
	@ManyToOne
	private SiteUser siteUser;
	
	public int getPaymentID() {
		return paymentID;
	}
	public int setPaymentID() {
		return paymentID;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.cardExpiry = Date.valueOf(paymentDate);
	}
	public void setPaymentDate(long timestamp) {
		this.paymentDate = new Date(timestamp);
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardCVC() {
		return cardCVC;
	}
	public void setCardCSV(String cardCVC) {
		this.cardCVC = cardCVC;
	}
	public Date getCardExpiry() {
		return cardExpiry;
	}
	public void setCardExpiry(Date cardExpiry) {
		this.cardExpiry = cardExpiry;
	}
	public void setCardExpiry(String cardExpiry) {
		this.cardExpiry = Date.valueOf(cardExpiry);
	}
	public SiteUser getSiteUser() {
		return siteUser;
	}
	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
	}

}
