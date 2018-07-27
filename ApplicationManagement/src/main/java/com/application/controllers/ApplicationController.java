package com.application.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.application.model.Application;
import com.application.services.ApplicationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@BasePathAwareController
@RequestMapping(value="/application")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);
	
	
	
	@RequestMapping(path="/create", method=RequestMethod.POST,produces = "application/hal+json")
	public @ResponseBody Application createApplicationDetails(@RequestBody Application application) {
		Application app = applicationService.saveApplication(application);
		LOG.info("application name is : "+app.getName());
		return app;
	}

	
	@RequestMapping(path="/{id}", method=RequestMethod.GET,produces = "application/hal+json")
	public @ResponseBody ResponseEntity<?> getApplicationById(@PathVariable(value = "id") Integer id) {
		Application application = applicationService.getApplication(id);
		getUserInfo(application);
		Resource resource = new Resource(application);
		resource.add(linkTo(methodOn(ApplicationController.class).getApplicationById(id)).withSelfRel());
		LOG.info("application name is : "+application.getName());
		return ResponseEntity.ok(resource);
	}
	
	@RequestMapping(path="/list", method=RequestMethod.GET, produces="application/hal+json")
	public @ResponseBody ResponseEntity<?> getAllApplications(){
		
		List<Application> appList = applicationService.getAllApplication();
		
		Resources<Application> resources = new Resources<Application>(appList);

		resources.add(linkTo(methodOn(ApplicationController.class).getAllApplications()).withSelfRel());
		return ResponseEntity.ok(resources);
		
	}

	public void getUserInfo(Application app) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			String userActivityService = "http://localhost:8081/user/" + app.getOwnerId();
			ResponseEntity<String> response = restTemplate.getForEntity(userActivityService, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = null;
			root = mapper.readTree(response.getBody());
			JsonNode name = root.path("name");
			JsonNode role = root.path("role");
			app.setOwnerName(name.asText());
			app.setOwnerRole(role.asText());
		} catch (IOException e) {
			app.setOwnerName("undefined");
			app.setOwnerRole("undefined");
			e.printStackTrace();
		}

	}	
	
}
