package com.gymsystem.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gymsystem.rest.model.User;

@Repository
public interface UserDao {

	List<User> getAllUsers();

	User getUserById(long id);

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(long id);
	
	public boolean checkCredentials(String username, String password);
	
	public boolean changePassword(String username, String password, String newPassword);
}