package com.activity.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.activity.model.User;
import com.activity.repositories.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public User saveUser(User user) {
	
		if(null != user) {
			User user1 = userRepo.save(user);
			return user1;
		}
		return null;

	}

	@Override
	public List<User> readUser() {
		List<User> users = (List<User>) userRepo.findAll();
		if(users.size()>0) {
			return users;
		}

		return null;
	}

	@Override
	public User updateUser(User user) {
		Optional<User> users = userRepo.findById(user.getUserId());
		//String updatedUser = saveUser(users.get());
		User u1 = users.get();
		u1.setName(user.getName());
		u1.setRole(user.getRole());
		saveUser(u1);
		return u1;
	}

	@Override
	public User getUser(Long id) {
		Optional<User> users = userRepo.findById(id);
		return users.get();
	}

}
