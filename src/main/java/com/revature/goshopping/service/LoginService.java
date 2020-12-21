package com.revature.goshopping.service;

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
	
	//@Autowired
  UserDaoForLoginService dao = new UserDaoForLoginService();

  /**
   * @return a not null LoginResponse.
   */
  public LoginResponse login(String username, String password)
      throws ServiceException {
    try {
      UserEntity dbUser = dao.fromUsername(username);

      if (dbUser == null) {
        throw new ServiceException(HttpStatus.NOT_FOUND);
      } else if (!PasswordUtility.doesMatch(password, dbUser.getPassword())) {
        throw new ServiceException(HttpStatus.UNAUTHORIZED, "wrong password!");
      }

      Auth auth = new Auth(dbUser.getId(), dbUser.isAdmin());
      return new LoginResponse(JwtUtility.create(auth));
    } catch (Exception e) {
      throw new ServiceException(e);
    }
  }
}
