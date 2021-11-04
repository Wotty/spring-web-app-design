package com.fdmgroup.entities;

import java.sql.Date;

import javax.persistence.*;

@Entity(name = "order_table")
public class Order {

	@Id
	@SequenceGenerator(name = "orderIdSeq", sequenceName = "ORDER_ID_SEQ", initialValue = 100, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdSeq")
	private int orderID;
	@ManyToOne
	private SiteUser siteUser;
	private Date orderDate;
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = Date.valueOf(orderDate);
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public SiteUser getSiteUser() {
		return siteUser;
	}
	public void setSiteUser(SiteUser siteUser) {
		this.siteUser = siteUser;
	}
}
