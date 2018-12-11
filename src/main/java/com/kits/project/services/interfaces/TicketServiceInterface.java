package com.kits.project.services.interfaces;


import com.kits.project.DTOs.StationDTO;
import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public interface TicketServiceInterface {

    public Ticket generateNewTicket(TicketDTO ticketDTO);


    public Ticket updateTicket(Long ticketID, TicketDTO ticketDTO);


    public boolean archiveTicket(Long ticketID);
}
