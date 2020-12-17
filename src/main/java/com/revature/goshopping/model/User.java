package com.revature.goshopping.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	private String username;
	private String password;
	private boolean admin;
	@OneToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Order> orders = new HashSet<Order>();
	
	public User() {
		super();
	}
	
	
	public User(String username, String password, boolean admin) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	

}
