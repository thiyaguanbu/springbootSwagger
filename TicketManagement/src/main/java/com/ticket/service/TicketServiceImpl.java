package com.ticket.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.model.Ticket;
import com.ticket.repository.TicketRepository;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TicketServiceImpl  implements TicketService{

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	TicketRepository ticketRepo;
	
	
	@Override
	public Ticket SaveTicket(Ticket ticket) {
		if(null!=ticket) {
			Ticket savedTicket = ticketRepo.save(ticket);
			return savedTicket;
		}
		return null;
	}

	@Override
	public Ticket getTicket(Integer id) {
		Optional<Ticket> ticket = ticketRepo.findById(id);
		return ticket.get();
	}

	@Override
	public List<Ticket> getAllTicket() {
		List<Ticket> ticketList = (List<Ticket>) ticketRepo.findAll();
		return ticketList;
	}

}
