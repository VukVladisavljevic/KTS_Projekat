package com.kits.project.services.implementations;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.repositories.TicketRepository;
import com.kits.project.services.interfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImplementation implements TicketServiceInterface {

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public Ticket createOneUseTicket(TicketDTO ticketDTO) {
        return null;
    }

    @Override
    public Ticket createMultipleUseTicket(TicketDTO ticketDTO) {
        return null;
    }

    @Override
    public Ticket activateTicket(TicketDTO ticketDTO) {
        return null;
    }

    @Override
    public boolean archiveTicket(TicketDTO ticketDTO) {
        return false;
    }
}
