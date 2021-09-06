package com.fdmgroup.entities;

import javax.persistence.*;

@Entity
public class OrderDetails {
	@Id
	@ManyToOne
	private Order order;
	@Id
	@ManyToOne
	private Item item;
	private int orderQty;

	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return this.item.getItemName()+" "+this.getOrder().getOrderID();
	}
}
