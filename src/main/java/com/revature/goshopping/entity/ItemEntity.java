package com.revature.goshopping.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
public class ItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private int id;

  private float price;

  private String name;

  private String description;

  @ManyToMany(cascade = {CascadeType.PERSIST})
  @JoinTable(
      name = "item_tag",
      joinColumns = @JoinColumn(name = "item_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<TagEntity> tags = new HashSet<>();

  public ItemEntity() {
    super();
  }

  public ItemEntity(float price, String name, String description) {
    super();
    this.price = price;
    this.name = name;
    this.description = description;
  }

  public void addTag(TagEntity tag) {
    tags.add(tag);
  }

  public int getId() {
    return id;
  }

  public void setId(int itemID) {
    this.id = itemID;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<TagEntity> getTags() {
    return tags;
  }

  public void setTags(Set<TagEntity> tags) {
    this.tags = tags;
  }
}