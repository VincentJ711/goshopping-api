package com.revature.goshopping.template;

public class OrderItemTemplate {
  private int itemID;

  private int quantity;

  public OrderItemTemplate(int itemID, int quantity) {
    this.itemID = itemID;
    this.quantity = quantity;
  }

  public int getItemID() {
    return itemID;
  }

  public void setItemID(int itemID) {
    this.itemID = itemID;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
