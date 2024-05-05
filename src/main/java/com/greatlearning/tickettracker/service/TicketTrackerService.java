package com.greatlearning.tickettracker.service;

import java.util.List;

import com.greatlearning.tickettracker.entity.Ticket;

public interface TicketTrackerService {

	List<Ticket> list();
	
	public void save(Ticket ticket);

	public Ticket findById(Long ticketId);

	public void deleteById(Long ticketId);
}
