package com.revature.goshopping.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_orders")
public class ItemOrder {
	@Id
	@ManyToOne
	@JoinColumn(name = "item_id")
	private int item_id;
	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private int order_id;
	private int quantity;
	
	public ItemOrder() {
		super();
	}

	public ItemOrder(int item_id, int order_id, int quantity) {
		super();
		this.item_id = item_id;
		this.order_id = order_id;
		this.quantity = quantity;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
