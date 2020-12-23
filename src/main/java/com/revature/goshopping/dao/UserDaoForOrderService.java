package com.revature.goshopping.dao;

import com.revature.goshopping.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoForOrderService {
  /**
   * im not sure if hibernate throws an error if user is not found. if it does,
   * catch that SPECIFIC error, and return null. dont catch any other errors.
   *
   * @return nullable UserEntity
   */
  public UserEntity getById(int userID) throws Exception {
    return null;
  }
}
