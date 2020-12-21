package com.revature.goshopping.model;

public class LoginResponse {
  private String jwt;

  private LoginResponse() {
    
  }

  public LoginResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
