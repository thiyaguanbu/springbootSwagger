package com.ticket.service;

import java.util.List;

import com.ticket.model.Ticket;

public interface TicketService {

	Ticket SaveTicket(Ticket ticket);
	Ticket getTicket(Integer id);
	List<Ticket> getAllTicket();
}
