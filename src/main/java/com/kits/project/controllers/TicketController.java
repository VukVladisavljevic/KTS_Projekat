package com.kits.project.controllers;

import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.model.TimeSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api")
@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/ticket/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.addTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Ticket>> index() {
        ArrayList<Ticket> tickets = ticketService.getAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket/{ticketId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket, @PathVariable Long ticketId) {
        Ticket updatedTicket = ticketService.update(ticketId, ticket);
        if (updatedTicket != null) {
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/tickets/user/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Ticket>> getTicketsForUser(@PathVariable Long userId) {
        ArrayList<Ticket> tickets = ticketService.getTicketsForUser(userId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}