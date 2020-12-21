package com.revature.goshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.goshopping.exception.ServiceException;
import com.revature.goshopping.model.Auth;
import com.revature.goshopping.model.LoginForm;
import com.revature.goshopping.model.LoginResponse;
import com.revature.goshopping.service.LoginService;
import com.revature.goshopping.utility.ControllerUtility;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping
	public ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm) {
		return ControllerUtility.handle(() -> loginService.login(loginForm.getUsername(), loginForm.getPassword()));

	}

}
