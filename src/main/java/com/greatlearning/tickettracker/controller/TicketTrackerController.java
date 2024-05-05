package com.greatlearning.tickettracker.controller;

import com.greatlearning.tickettracker.entity.Ticket;
import com.greatlearning.tickettracker.service.TicketTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TicketTrackerController {

    @Autowired
    TicketTrackerService ticketTrackerService;

    @RequestMapping("/tickets")
    public String listAllTickets(Model model) {

        List<Ticket> tickets = ticketTrackerService.list();

        model.addAttribute("tickets", tickets);
        return "list-tickets";
    }

    @RequestMapping("/ticket/search")
    public String searchTicket(@RequestParam(value = "searchText", required = false) String searchText, Model theModel) {

        List<Ticket> tickets = ticketTrackerService.list();
        tickets = tickets.stream().filter(e -> e.getShortDescription().toLowerCase().contains(searchText) || e.getTitle().toLowerCase().contains(searchText)).collect(Collectors.toList());

        theModel.addAttribute("tickets", tickets);

        return "list-tickets";
    }

    @RequestMapping("/ticket/add")
    public String addTicket(Model theModel) {

        // create model attribute to bind form data
        Ticket ticket = new Ticket();

        theModel.addAttribute("ticket", ticket);

        return "ticket-form";
    }

    @PostMapping("/ticket/save")
    public String saveTicket(
            @ModelAttribute("ticket") Ticket ticket) {

        // save the ticket
        ticket.setCreatedOn(LocalDate.now());
        ticketTrackerService.save(ticket);

        // use a redirect to prevent duplicate submissions
        return "redirect:/tickets";
    }

    @RequestMapping("/ticket/update")
    public String updateTicket(
            @RequestParam("ticketId") Long ticketId,
            Model theModel) {

        // get the ticket from the service
        Ticket ticket = ticketTrackerService.findById(ticketId);

        // set Employee as a model attribute to pre-populate the form
        theModel.addAttribute("ticket", ticket);

        // send over to our form
        return "ticket-form";
    }


    @RequestMapping("/ticket/delete")
    public String delete(
            @RequestParam("ticketId") Long ticketId) {

        // delete the Employee
        ticketTrackerService.deleteById(ticketId);

        // redirect to tickets-Listing page
        return "redirect:/tickets";
    }

}
