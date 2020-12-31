package com.revature.goshopping.dto;

/**
 * meant to be sent back to the client on POST /login in the body
 */
public class LoginResponse {
  private String jwt;

  public LoginResponse() {

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
