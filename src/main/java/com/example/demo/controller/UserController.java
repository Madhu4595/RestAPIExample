package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@RestController
@CrossOrigin(origins  = "http://localhost:3000/")
public class UserController {

	@Autowired private UserRepo userRepo;
	
	@PostMapping("/user")
	public User newUser(@RequestBody User newUser) {
		return userRepo.save(newUser);
	}
	@GetMapping("users")
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/user/{id}")
	User getUser(@PathVariable Long id) {
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
	}
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable Long id) {
		User user = userRepo.findById(id).get();
		user.setName(newUser.getName());
		user.setUsername(newUser.getUsername());
		user.setEmail(newUser.getEmail());
		userRepo.save(user);
		
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepo.deleteById(id);
		return "User with id"+ id +"deletion success.";
	}
}
