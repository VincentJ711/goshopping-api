package com.revature.goshopping.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.goshopping.dao.UserDaoForLoginService;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.exception.ServiceException;
import com.revature.goshopping.model.Auth;
import com.revature.goshopping.model.LoginResponse;
import com.revature.goshopping.utility.JwtUtility;
import com.revature.goshopping.utility.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
  UserDaoForLoginService dao;

  /**
   * @return a not null LoginResponse.
   */
  public LoginResponse login(String username, String password)
      throws ServiceException {
    UserEntity dbUser;

    try {
      dbUser = dao.fromUsername(username);
    } catch (Exception e) {
      throw new ServiceException(e);
    }

    if (dbUser == null) {
      throw new ServiceException(HttpStatus.NOT_FOUND);
    } else if (!PasswordUtility.doesMatch(password, dbUser.getPassword())) {
      throw new ServiceException(HttpStatus.UNAUTHORIZED, "wrong password!");
    }

    Auth auth = new Auth(dbUser.getId(), dbUser.isAdmin());

    try {
      return new LoginResponse(JwtUtility.create(auth));
    } catch (JsonProcessingException e) {
      throw new ServiceException(e);
    }
  }
}
