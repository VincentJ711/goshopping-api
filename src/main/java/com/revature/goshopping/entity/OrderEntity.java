package com.revature.goshopping.entity;

import com.revature.goshopping.dto.Order;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private int id;

  private Date date;

  @OneToMany(
      mappedBy = "order",
      cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
  )
  private Set<ItemOrderEntity> itemOrders = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  public OrderEntity() {
    super();
  }

  public OrderEntity(Date date, UserEntity user) {
    super();
    this.date = date;
    this.user = user;
  }

  public void addItem(ItemEntity item, int quantity) {
    itemOrders.add(new ItemOrderEntity(item, this, quantity));
  }

  public int getId() {
    return id;
  }

  public void setId(int order_id) {
    this.id = order_id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Set<ItemOrderEntity> getItemOrders() {
    return itemOrders;
  }

  public void setItemOrders(Set<ItemOrderEntity> itemOrders) {
    this.itemOrders = itemOrders;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
