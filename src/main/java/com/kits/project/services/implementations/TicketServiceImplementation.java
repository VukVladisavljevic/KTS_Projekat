package com.kits.project.services.implementations;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.model.TicketType;
import com.kits.project.model.User;
import com.kits.project.repositories.TicketRepository;
import com.kits.project.repositories.UserRepository;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.interfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class TicketServiceImplementation implements TicketServiceInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public Ticket createOneUseTicket(TicketDTO ticketDTO) {
        String username = jwtUtils.getUsernameFromToken(ticketDTO.token);
        User user = userRepository.findByUsername(username);
        Ticket newTicket = new Ticket(null, null, null, false);
        newTicket.setTicketType(TicketType.SINGLE);

        user.addTicket(newTicket);
        userRepository.flush();
        ticketRepository.save(newTicket);
        return newTicket;
    }

    @Override
    public Ticket createMultipleUseTicket(TicketDTO ticketDTO) {
        String username = jwtUtils.getUsernameFromToken(ticketDTO.token);
        User user = userRepository.findByUsername(username);
        Ticket newTicket;

        if(ticketDTO.ticketType.equalsIgnoreCase("monthly")) {
            Date startTime = new Date();
            Date endTime = Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());
            newTicket = new Ticket(user, startTime, endTime, false);
            newTicket.setTicketType(TicketType.MONTHLY);

        } else if (ticketDTO.ticketType.equalsIgnoreCase("yearly")){
            Date startTime = new Date();
            Date endTime = Date.from(LocalDate.now().plusDays(365).atStartOfDay(ZoneId.systemDefault()).toInstant());
            newTicket = new Ticket(user, startTime, endTime, false);
            newTicket.setTicketType(TicketType.YEARLY);

        } else {
            newTicket = null;
        }

        user.addTicket(newTicket);
        userRepository.flush();
        ticketRepository.save(newTicket);
        return newTicket;
    }

    @Override
    public Ticket activateTicket(TicketDTO ticketDTO) {

        Ticket ticket = ticketRepository.getOne(Long.valueOf(ticketDTO.id));

        //set to active
        ticket.setActive(true);

        //set time
        Date startTime = new Date();
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 60);
        Date endTime = now.getTime();

        ticket.setStartTime(startTime);
        ticket.setEndTime(endTime);

        ticketRepository.save(ticket);
       // ticketRepository.save(newTicket);
        return ticket;
    }

    @Override
    public boolean archiveTicket(String id) {
        Long ticketID = Long.valueOf(id);
        Ticket toRemove = ticketRepository.getOne(ticketID);
        User user = toRemove.getUser();
        user.getTickets().remove(toRemove);

       // ticketRepository.delete(toRemove);
        userRepository.flush();

        return true;

    }

    @Override
    public List<Ticket> getOwnedTickets(String token) {
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }
        return user.getTickets();
    }
}
