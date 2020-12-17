package com.revature.goshopping.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_orders")
public class ItemOrder implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	private int quantity;
	
	public ItemOrder() {
		super();
	}

	public ItemOrder(Item item, Order order, int quantity) {
		super();
		this.item = item;
		this.order = order;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
