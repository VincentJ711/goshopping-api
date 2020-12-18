package com.revature.goshopping.model;

public class User {
  private Integer id;

  private String username;

  private Boolean admin;

  private String password;

  // for jackson
  private User() {

  }

  public User(Integer id, String username, Boolean admin, String password) {
    this.id = id;
    this.username = username;
    this.admin = admin;
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
