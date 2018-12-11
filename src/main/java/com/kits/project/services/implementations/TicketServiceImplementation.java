package com.kits.project.services.implementations;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.services.interfaces.TicketServiceInterface;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImplementation implements TicketServiceInterface {
    @Override
    public Ticket generateNewTicket(TicketDTO ticketDTO) {
        return null;
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
