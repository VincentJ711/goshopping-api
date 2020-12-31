package com.revature.goshopping.controller;

import com.revature.goshopping.dto.Auth;
import com.revature.goshopping.dto.SwapPassword;
import com.revature.goshopping.dto.User;
import com.revature.goshopping.service.UserService;
import com.revature.goshopping.utility.ControllerUtility;
import com.revature.goshopping.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;

	@Autowired
	private JwtUtility jwtUtility;

	@GetMapping("")
	public ResponseEntity<List<User>> getUsers(@RequestHeader Map<String, String> headers) {

		Auth auth = jwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			return userservice.getUserFromService(auth);
		});
	}

	@PostMapping("")
	public ResponseEntity<User> postUser(@RequestHeader Map<String, String> headers,
			@RequestBody User user) {
		Auth auth = jwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			return userservice.postUserFromService(user, auth);
		});
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@RequestHeader Map<String, String> headers, @PathVariable int id) {
		Auth auth = jwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			return userservice.findUserFromService(auth, id);
		});
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@RequestHeader Map<String, String> headers, @PathVariable int id) {
		Auth auth = jwtUtility.getAuth(headers);

		return ControllerUtility.handle(() -> {
			userservice.deleteUserFromService(auth, id);
			return null;
		});
	}

	@PutMapping("")
	public ResponseEntity<User> updatePassword(@RequestHeader Map<String, String> headers,
			@RequestBody SwapPassword newPass) {
		Auth auth = jwtUtility.getAuth(headers);
		return ControllerUtility.handle(() -> {
			return userservice.updateUserFromService(auth, newPass);
		});

	}
}
