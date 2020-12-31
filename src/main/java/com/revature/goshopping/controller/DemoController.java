package com.revature.goshopping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.exception.ServiceException;
import com.revature.goshopping.utility.ControllerUtility;
import com.revature.goshopping.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demos")
public class DemoController {
  @Autowired
  private JwtUtility jwtUtility;

  /**
   * demonstrates a very simple way to write your controller methods. also
   * shows how a service method interaction is handled.
   */
  @GetMapping("/callback")
  public ResponseEntity<List<Demo>> cbDemo(
      @RequestHeader Map<String, String> headers,
      @RequestParam(required = false) Integer qp
  ) {
    // im hardcoding the auth for demo purposes but you really want to
    // use this commented line in your controllers.
    // Auth auth = JwtUtility.getAuth(headers);
    Auth auth = new Auth(10, true);
    return ControllerUtility.handle(() -> aServiceMethod(auth, qp));
  }

  /**
   * this is simulating a method in the service layer
   */
  private List<Demo> aServiceMethod(Auth auth, Integer num)
      throws ServiceException {
    if (auth == null) {
      throw new ServiceException(HttpStatus.UNAUTHORIZED);
    } else if (!auth.isAdmin()) {
      throw new ServiceException(HttpStatus.FORBIDDEN);
    } else if (num != null && num > 10) {
      throw new ServiceException(HttpStatus.BAD_REQUEST, "num must be <= 10!");
    }
    return Arrays.asList(new Demo("you provided query param qp=" + num));
  }

  /**
   * @return a jwt containing the created auth object.
   */
  @GetMapping("/login")
  public ResponseEntity<LoginResponse> loginDemo(
      @RequestParam String password) {
    return ControllerUtility.handle(() -> {
      if (!"password".equals(password)) {
        throw new ServiceException(HttpStatus.BAD_REQUEST, "invalid password!");
      }

      Auth auth = new Auth(10, true);
      String jwt = null;

      try {
        jwt = jwtUtility.create(auth);
      } catch (JsonProcessingException e) { }

      return new LoginResponse(jwt);
    });
  }

  /**
   * how you'd actually parse the auth object out of the jwt in the headers
   * the jwt should be on the Authorization header which has value
   * "Bearer {JWT}"
   */
  @GetMapping("/parse_jwt")
  public String gettingTheAuth(@RequestHeader Map<String, String> headers) {
    Auth auth = jwtUtility.getAuth(headers);
    return "i parsed from the jwt in the headers an auth object = " + auth;
  }

  private static class Demo {
    private String content;

    private Demo() { }

    public Demo(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }

  private static class LoginResponse {
    private String jwt;

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
}
