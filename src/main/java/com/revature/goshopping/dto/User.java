package com.revature.goshopping.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
  private Integer id;

  private String username;

  private Boolean admin;

  private String password;

  // for jackson
  public User() {

  }

  public User(Integer id, String username, Boolean admin, String password) {
    this.id = id;
    this.username = username;
    this.admin = admin;
    this.password = password;
  }

  @JsonIgnore
  public boolean isValid() {
		try {
			if( this.username.equals("") || this.password.equals(""))
				return false;
			return true;
		} catch(NullPointerException e) {
			return false;
		}
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

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", admin=" + admin +
        '}';
  }
}
