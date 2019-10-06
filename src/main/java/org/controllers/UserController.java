package org.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.entities.AppUser;
import org.repositories.UserRepository;
import org.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
//@RequestMapping("/user")
public class UserController {
	@Autowired
	private AccountService userService;
	@Autowired
	private UserRepository appUserRepository;

	@PostMapping("/register")
	public AppUser register(@RequestBody AppUser user) throws NoSuchAlgorithmException {
		return userService.saveUser(user.getUsername(), user.getPassword());
	}

	@GetMapping("/users")
	public List<AppUser> getUsers() {
		List<AppUser> ListeUsers = new ArrayList<AppUser>();
		ListeUsers = appUserRepository.findAll();
		return ListeUsers;
	}

}
