package com.revature.goshopping.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	private Date date;
	@OneToMany(mappedBy = "order")
	private Set<ItemOrder> items = new HashSet<ItemOrder>();
	
	public Order() {
		super();
	}

	public Order(Date date) {
		super();
		this.date = date;
	}
	
	public void addItem(Item item, int quantity) {
		items.add(new ItemOrder(item, this, quantity));
	}
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<ItemOrder> getItems() {
		return items;
	}
	public void setItems(Set<ItemOrder> items) {
		this.items = items;
	}

}
