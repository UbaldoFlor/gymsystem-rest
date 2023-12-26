package com.gymsystem.rest.service.impl;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.rest.dao.UserDao;
import com.gymsystem.rest.model.User;
import com.gymsystem.rest.requests.LoginRequest;
import com.gymsystem.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void createUser(User user) {
		user = generateUsername(user);
		user = generatePassword(user);
		userDao.createUser(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(Long id) {
	    userDao.deleteUser(id);;
	}

	@Override
	public List<User> getAllUsers() {
	    return userDao.getAllUsers();
	}
	
	private User generateUsername(User user) {
		String firstName = user.getFirstName().toLowerCase();
		String lastName = user.getLastName().toLowerCase();
		String username = firstName + "." + lastName;
		user.setUsername(username);
		
		return user;
	}
	
	private User generatePassword(User user) {
		int passwordLength = 10;
		String password = RandomStringUtils.randomAlphabetic(passwordLength);
		user.setPassword(password);
		return user;
	}

	@Override
	public boolean checkCredentials(LoginRequest loginRequest) {
		return userDao.checkCredentials(loginRequest.getUsername(), loginRequest.getPassword());
	}

	@Override
	public boolean changePassword(String username, String password, String newPassword) {
		return userDao.changePassword(username, password, newPassword);
	}
}