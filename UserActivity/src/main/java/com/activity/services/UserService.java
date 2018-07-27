package com.activity.services;

import java.util.List;

import com.activity.model.User;

public interface UserService {
	
	public User saveUser(User user);
	public List<User> readUser();
	public User updateUser(User user);
	public User getUser(Long id);

}
