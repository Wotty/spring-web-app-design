package com.fdmgroup.entities;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
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
	public void setOrderDate(long orderDate) {
		this.orderDate = new Date(orderDate);
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
