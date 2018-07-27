package com.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.Application;
import com.application.repositories.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	ApplicationRepository ApplicationRepo;
	
	
	@Override
	public Application saveApplication(Application app) {
		return ApplicationRepo.save(app);
	}

	@Override
	public Application getApplication(Integer id) {
		 Optional<Application> app = ApplicationRepo.findById(id);
		 return app.get();
	}

	@Override
	public List<Application> getAllApplication() {
		List<Application> applications = (List<Application>) ApplicationRepo.findAll();
		return applications;
	}

}
