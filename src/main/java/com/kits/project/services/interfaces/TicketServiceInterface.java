package com.kits.project.services.interfaces;


import com.kits.project.DTOs.StationDTO;
import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import org.springframework.stereotype.Service;


public interface TicketServiceInterface {

    public Ticket createOneUseTicket(TicketDTO ticketDTO);

    public Ticket createMultipleUseTicket(TicketDTO ticketDTO);

    public Ticket activateTicket(TicketDTO ticketDTO);

    public boolean archiveTicket(TicketDTO ticketDTO);
}
