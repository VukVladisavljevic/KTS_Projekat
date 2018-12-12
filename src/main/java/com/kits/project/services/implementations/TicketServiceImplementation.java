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
    public Ticket generateNewTicket(TicketDTO ticketDTO) {

        return ticketRepository.save(new Ticket(ticketDTO));
    }

    @Override
    public Ticket updateTicket(Long ticketID, TicketDTO ticketDTO) {
        return null;
    }

    @Override
    public boolean archiveTicket(Long ticketID) {
        return false;
    }
}
