package com.kits.project.services.interfaces;


import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Ticket;
import com.kits.project.model.TicketType;

import java.util.Date;
import java.util.List;


public interface TicketServiceInterface {

    public Ticket createOneUseTicket(TicketDTO ticketDTO);

    public Ticket createMultipleUseTicket(TicketDTO ticketDTO);

    public Ticket activateTicket(TicketDTO ticketDTO);

    public boolean archiveTicket(String id);

    public List<Ticket> getOwnedTickets(String username);

    public List<Ticket> getAllTickets();

    public List<Ticket> getActiveTicketsForUser(String username);

    public List<Ticket> getAllTicketsBetween(Date startDate, Date endDate);

    public List<Ticket> getAllTypeTicketsBetween(Date startDate, Date endDate, TicketType ticketType);
}
