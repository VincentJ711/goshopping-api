package com.revature.goshopping.template;

import java.util.List;

public class OrderTemplate {
  private int userID;

  private List<OrderItemTemplate> items;

  public OrderTemplate(int userID,
      List<OrderItemTemplate> items) {
    this.userID = userID;
    this.items = items;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public List<OrderItemTemplate> getItems() {
    return items;
  }

  public void setItems(
      List<OrderItemTemplate> items) {
    this.items = items;
  }
}
