package com.revature.goshopping.entity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @Column(unique = true)
  private String username;

  private String password;

  private boolean admin;

  @OnDelete(action = OnDeleteAction.CASCADE)
  @OneToMany(
      mappedBy = "user",
     //cascade = {CascadeType.PERSIST},
      cascade = CascadeType.REMOVE,
      fetch = FetchType.EAGER
  )
  private Set<OrderEntity> orders = new HashSet<>();

  public UserEntity() {
    super();
  }

  public UserEntity(String username, String password, boolean admin) {
    super();
    this.username = username;
    this.password = password;
    this.admin = admin;
  }

  public void addOrder(OrderEntity order) {
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

  public Set<OrderEntity> getOrders() {
    return orders;
  }

  public void setOrders(Set<OrderEntity> orders) {
    this.orders = orders;
  }

@Override
public String toString() {
	return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin
			+ ", orders=" + orders + "]";
}
  
  
}
