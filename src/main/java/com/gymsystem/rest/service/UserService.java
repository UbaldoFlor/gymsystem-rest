package com.gymsystem.rest.service;

import java.util.List;

import com.gymsystem.rest.model.User;
import com.gymsystem.rest.requests.LoginRequest;

public interface UserService {
	
	public void createUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(Long id);
	
	public User getUserById(Long id);
	
	public List<User> getAllUsers();

	public boolean checkCredentials(LoginRequest loginRequest);
	
	public boolean changePassword(String username, String password, String newPassword);
}