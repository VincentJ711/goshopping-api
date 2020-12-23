package com.revature.goshopping.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.LoginForm;
import com.revature.goshopping.dto.LoginResponse;
import com.revature.goshopping.dto.SwapPassword;
import com.revature.goshopping.dto.User;
import com.revature.goshopping.service.UserService;
import com.revature.goshopping.utility.ControllerUtility;
import com.revature.goshopping.utility.JwtUtility;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;

	@GetMapping("")
	public ResponseEntity<List<User>> getUsers(@RequestHeader Map<String, String> headers) {

		Auth auth = JwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			return userservice.getUserFromService(auth);
		});
	}

	@PostMapping("")
	public ResponseEntity<User> postUser(@RequestBody User user) {

		return ControllerUtility.handle(() -> {
			return userservice.postUserFromService(user);
		});
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@RequestHeader Map<String, String> headers, @PathVariable int id) {
		Auth auth = JwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			return userservice.findUserFromService(auth, id);
		});
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@RequestHeader Map<String, String> headers, @PathVariable int id) {
		Auth auth = JwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			userservice.deleteUserFromService(auth, id);
			return null;
		});
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updatePassword(@RequestHeader Map<String, String> headers,
			@RequestBody SwapPassword newPass) {
		Auth auth = JwtUtility.getAuth(headers);
		return ControllerUtility.handle(() -> {
			return userservice.updateUserFromService(auth, newPass);
		});

	}
}
