package com.revature.goshopping.dao;

import com.revature.goshopping.entity.UserEntity;

public class UserDaoForLoginService {

  /**
   * im not sure if hibernate throws an error if user is not found. if it does,
   * catch that SPECIFIC error, and return null. dont catch any other errors.
   *
   * @return nullable UserEntity
   */
  public UserEntity fromUsername(String username) throws Exception {
    return null;
  }
}
