package com.greatlearning.tickettracker.service;

import com.greatlearning.tickettracker.entity.Ticket;

import java.util.List;

public interface TicketTrackerService {

    List<Ticket> list();

    public void save(Ticket ticket);

    public Ticket findById(Long ticketId);

    public void deleteById(Long ticketId);
}
