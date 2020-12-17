package com.revature.goshopping.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private int user_id;
	@OneToMany(mappedBy = "orders")
	private Set<ItemOrder> items = new HashSet<ItemOrder>();
	
	public Order() {
		super();
	}

	public Order(Date date, int user_id) {
		super();
		this.date = date;
		this.user_id = user_id;
	}
	
	public void addItem(Item item, int quantity) {
		items.add(new ItemOrder(item.getItem_id(), this.order_id, quantity));
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Set<ItemOrder> getItems() {
		return items;
	}
	public void setItems(Set<ItemOrder> items) {
		this.items = items;
	}

}
