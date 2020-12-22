package com.revature.goshopping.dto;

import java.util.List;

public class OrderItem extends Item {
  private int quantity;

  // for jackson
  private OrderItem() {

  }

  public OrderItem(int id, float price, String description, String name,
      List<Tag> tags, int quantity) {
    super(id, price, description, name, tags);
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
