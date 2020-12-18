package com.revature.goshopping.model;

import java.util.ArrayList;
import java.util.List;

public class Item {
  private int id;

  private Float price;

  private String description;

  private String name;
  
  private List<Tag> tags = new ArrayList<>();

  // for jackson
  protected Item() {

  }

  public Item(int id, Float price, String description, String name,
      List<Tag> tags) {
    this.id = id;
    this.price = price;
    this.description = description;
    this.name = name;
    this.tags = tags;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
  }
}
