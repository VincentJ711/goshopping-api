package com.revature.goshopping.dto;

public class SwapPassword {
  private String newPass;

  private String oldPass;

  private SwapPassword() {

  }

  public SwapPassword(String newPass, String oldPass) {
    super();
    this.newPass = newPass;
    this.oldPass = oldPass;
  }

  public String getNewPass() {
    return newPass;
  }

  public void setNewPass(String newPass) {
    this.newPass = newPass;
  }

  public String getOldPass() {
    return oldPass;
  }

  public void setOldPass(String oldPass) {
    this.oldPass = oldPass;
  }
}
