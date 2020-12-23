package com.revature.goshopping.dto;

import com.revature.goshopping.entity.ItemEntity;
import com.revature.goshopping.entity.ItemOrderEntity;
import com.revature.goshopping.entity.TagEntity;

import java.util.ArrayList;
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

  public OrderItem(ItemOrderEntity ioe) {
    ItemEntity item = ioe.getItem();
    List<Tag> tags = new ArrayList<>();

    for (TagEntity tag : item.getTags()) {
      tags.add(new Tag(tag));
    }

    setId(item.getId());
    setPrice(item.getPrice());
    setDescription(item.getDescription());
    setName(item.getName());
    setTags(tags);
    setQuantity(ioe.getQuantity());
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
