package com.application.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.application.model.Application;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer>{
	

}
