package com.hsbc.mann.restcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.mann.entity.User;
import com.hsbc.mann.service.IUserService;

@RestController
//@RequestMapping("/user")
public class UserRestcontroller {

	@Autowired
	private IUserService service;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		Integer registerUserId = service.registerUser(user);
		return ResponseEntity.ok("User with Id: '" + registerUserId + "' created successfully!");
	}

	@GetMapping("/success")
	public ResponseEntity<String> susscessfullyLoggedIn(Principal principal) {

		return ResponseEntity.ok("Successfully loggedIn as user: '"+principal.getName()+"'");
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutApp() {

		return new ResponseEntity<String>("Logout successful!",HttpStatus.OK);
	}
	
	@GetMapping("/error")
	public ResponseEntity<String> loginFailure() {

		return new ResponseEntity<String>("Dont have the Authority!",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/user")
	public Authentication getUserDetails() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome after Login!!";
	}
}
