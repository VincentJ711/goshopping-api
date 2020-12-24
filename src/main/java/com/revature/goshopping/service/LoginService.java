package com.revature.goshopping.service;

import com.revature.goshopping.dao.UserDao;
import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.LoginResponse;
import com.revature.goshopping.entity.UserEntity;
import com.revature.goshopping.exception.ServiceException;
import com.revature.goshopping.utility.JwtUtility;
import com.revature.goshopping.utility.PasswordUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  @Autowired
  private UserDao userDao;

  /**
   * @return a not null LoginResponse.
   */
  public LoginResponse login(String username, String password)
      throws Exception {
    UserEntity dbUser = userDao.getUserByUsername(username);

    if (dbUser == null) {
      throw new ServiceException(HttpStatus.NOT_FOUND);
    } else if (!PasswordUtility.doesMatch(password, dbUser.getPassword())) {
      throw new ServiceException(HttpStatus.UNAUTHORIZED, "wrong password!");
    }

    Auth auth = new Auth(dbUser.getId(), dbUser.isAdmin());
    return new LoginResponse(JwtUtility.create(auth));
  }
}
