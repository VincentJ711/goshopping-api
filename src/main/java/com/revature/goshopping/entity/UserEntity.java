package com.revature.goshopping.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  private String username;

  private String password;

  private boolean admin;

  @OneToMany(
      mappedBy = "user",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
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
}