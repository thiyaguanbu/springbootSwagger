package com.activity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.activity.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	
}
