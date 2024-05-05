package com.greatlearning.tickettracker.service.impl;

import com.greatlearning.tickettracker.entity.Ticket;
import com.greatlearning.tickettracker.repository.TicketRepository;
import com.greatlearning.tickettracker.service.TicketTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketTrackerServiceImpl implements TicketTrackerService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> list() {

        return ticketRepository.findAll();
    }


    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }


    @Override
    public Ticket findById(Long ticketId) {
        return ticketRepository.findById(ticketId).get();
    }


    @Override
    public void deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}