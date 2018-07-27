package com.application.services;

import java.util.List;

import com.application.model.Application;

public interface ApplicationService {
	
	Application saveApplication(Application app);
	Application getApplication(Integer id);
	List<Application> getAllApplication();

}
