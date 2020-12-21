package com.revature.goshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.goshopping.exception.ServiceException;
import com.revature.goshopping.model.Auth;
import com.revature.goshopping.model.LoginForm;
import com.revature.goshopping.model.LoginResponse;
import com.revature.goshopping.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping
	public LoginResponse login(@RequestBody LoginForm loginForm) {
		try {
			LoginResponse loginResponse = loginService.login(loginForm.getUsername(), loginForm.getPassword());
			return loginResponse;
		}catch (ServiceException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			LoginResponse loginResponse = new LoginResponse(e.getMessage());
			return loginResponse;
		}
		
		
	}

}
