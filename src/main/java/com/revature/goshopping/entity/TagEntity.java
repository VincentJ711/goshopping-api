package com.revature.goshopping.entity;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class TagEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tag_id")
  private int id;

  private String name;

  public TagEntity() {
    super();
  }

  public TagEntity(String name) {
    super();
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
