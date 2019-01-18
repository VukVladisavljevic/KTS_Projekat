package com.kits.project.services.implementations;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Station;
import com.kits.project.model.Ticket;
import com.kits.project.model.User;
import com.kits.project.repositories.TicketRepository;
import com.kits.project.repositories.UserRepository;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.interfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        System.out.println(ticketDTO.token);
        String username = jwtUtils.getUsernameFromToken(ticketDTO.token);
        System.out.println(username);
        User user = userRepository.findByUsername(username);
      //  User user = new User("vv","pass", "vv", "VV");
        Ticket newTicket = new Ticket(null, null, null, true);

        user.addTicket(newTicket);
        System.out.println(newTicket.getUser().getEmail());
        userRepository.flush();
        return newTicket;
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

    @Override
    public List<Ticket> getOwnedTickets(String username) {
        return null;
    }
}
