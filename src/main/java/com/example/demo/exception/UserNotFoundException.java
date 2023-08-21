package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(Long id) {
		super("Could not found with user id: "+id);
	}

}
