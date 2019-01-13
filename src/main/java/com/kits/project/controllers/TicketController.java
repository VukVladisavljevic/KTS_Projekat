package com.kits.project.controllers;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.model.TimeSchedule;
import com.kits.project.services.interfaces.TicketServiceInterface;
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
    private TicketServiceInterface ticketService;

    @RequestMapping(value = "/ticket/createOneUse", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> createOneUseTicket(@RequestBody TicketDTO ticket) {
        Ticket newTicket = ticketService.createOneUseTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket/createMultipleUse", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> create(@RequestBody TicketDTO ticket) {
        Ticket newTicket = ticketService.activateTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.OK);
    }

    @RequestMapping(value = "/ticket/activate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> createMultipleUseTicket(@RequestBody TicketDTO ticket) {
        Ticket newTicket = ticketService.createMultipleUseTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.OK);
    }

}