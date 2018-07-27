package com.ticket.controllers;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.model.Ticket;
import com.ticket.service.TicketService;


@BasePathAwareController
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	TicketService ticketService;
	
	private static final Logger LOG = LoggerFactory.getLogger(TicketController.class);
	
	@RequestMapping(path="/create",method=RequestMethod.POST,produces="application/hal+json")
	public @ResponseBody Ticket createTicket(@RequestBody Ticket ticket) {
		
		Ticket savedTicket = ticketService.SaveTicket(ticket);
		LOG.info("ticket title is "+savedTicket.getTitle());
		return savedTicket;
	}

	@RequestMapping(path="/{id}",method=RequestMethod.GET,produces="application/hal+json")
	public @ResponseBody ResponseEntity<?> getTicket(@PathVariable(value = "id") Integer id) {
		
		Ticket ticket = ticketService.getTicket(id);
		getApplicationInfo(ticket);
		getUserInfo(ticket);
		
		Resource resource = new Resource(ticket);
		resource.add(linkTo(methodOn(TicketController.class).getTicket(id)).withSelfRel());
		return ResponseEntity.ok(resource);
	}

	@RequestMapping(path="/list",method=RequestMethod.GET,produces="application/hal+json")
	public @ResponseBody ResponseEntity<?> getAllTicket() {
		
		List<Ticket> ticketList = ticketService.getAllTicket();
		Resources<Ticket> resources = new Resources<Ticket>(ticketList);
		resources.add(linkTo(methodOn(TicketController.class).getAllTicket()).withSelfRel());
		return ResponseEntity.ok(resources);
	}
	
	
	private void getUserInfo(Ticket ticket) {
		
		
		  try {
			  	RestTemplate restTemplate = new RestTemplate();
				String userActivityService = "http://localhost:8081/user/"+ticket.getUser_id();
				ResponseEntity<String> response = restTemplate.getForEntity(userActivityService, String.class);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = null;
				 root = mapper.readTree(response.getBody());
				 JsonNode name = root.path("name");
				 ticket.setUser_name(name.asText());
		} catch (IOException e) {
			ticket.setUser_name("undefined");
			e.printStackTrace();
		}
		
	}

	private void getApplicationInfo(Ticket ticket) {

		try {
		  	RestTemplate restTemplate = new RestTemplate();
			String applicationService = "http://localhost:8080/applications/"+ticket.getAplication_id();
			ResponseEntity<String> response = restTemplate.getForEntity(applicationService, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = null;
			 root = mapper.readTree(response.getBody());
			 JsonNode name = root.path("name");
			 ticket.setApplication_name(name.asText());
	} catch (IOException e) {
		ticket.setApplication_name("undefined");
		e.printStackTrace();
	}
		
	}

	
}
