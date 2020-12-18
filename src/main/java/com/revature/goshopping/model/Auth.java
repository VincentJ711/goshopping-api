package com.revature.goshopping.model;

public class Auth {
  private int id;

  private boolean admin;

  private Auth() {

  }

  public Auth(int id, boolean admin) {
    this.id = id;
    this.admin = admin;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
