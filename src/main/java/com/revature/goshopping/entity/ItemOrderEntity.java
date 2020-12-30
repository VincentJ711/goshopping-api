package com.revature.goshopping.entity;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "item_order")
public class ItemOrderEntity implements Serializable {
  @Id
  @ManyToOne
  @JoinColumn(name = "item_id")
  private ItemEntity item;

  @Id
  @ManyToOne
  @JoinColumn(name = "order_id")
  private OrderEntity order;

  private int quantity;

  public ItemOrderEntity() {
    super();
  }

  public ItemOrderEntity(ItemEntity item, OrderEntity order, int quantity) {
    super();
    this.item = item;
    this.order = order;
    this.quantity = quantity;
  }

  public ItemEntity getItem() {
    return item;
  }

  public void setItem(ItemEntity item) {
    this.item = item;
  }

  public OrderEntity getOrder() {
    return order;
  }

  public void setOrder(OrderEntity order) {
    this.order = order;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
