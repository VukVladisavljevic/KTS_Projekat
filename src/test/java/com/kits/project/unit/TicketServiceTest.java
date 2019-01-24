package com.kits.project.unit;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Ticket;
import com.kits.project.model.User;
import com.kits.project.repositories.TicketRepository;
import com.kits.project.repositories.UserRepository;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.implementations.TicketServiceImplementation;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketServiceTest {

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JWTUtils jwtUtils;

    @Autowired
    private TicketServiceImplementation ticketService;

    @Before
    public void setUp() {
        User u = new User("username", "password", "name", "last name");
        ArrayList<Ticket> tickets = new ArrayList<>();
        Ticket ticket1 = new Ticket(u, new Date(), new Date(), false);
        Ticket ticket2 = new Ticket(u, new Date(), new Date(), true);
        Ticket ticket3 = new Ticket(u, new Date(), new Date(), true);
        ticket1.setId(1L);
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        u.setTickets(tickets);
        ticket1.setUser(u);
        Mockito.when(userRepository.findByUsername("username")).thenReturn(u);
        Mockito.when(ticketRepository.getOne(1L)).thenReturn(ticket1);
        Mockito.when(jwtUtils.getUsernameFromToken("token")).thenReturn("username");
        Mockito.when(jwtUtils.getUsernameFromToken("token1")).thenReturn("null");
        Mockito.when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(ticket1);

    }

    @Test
    public void getOwnedTicketsTest() {
        List<Ticket> result = ticketService.getOwnedTickets("token");
        assertEquals(result.size(), 3);
        int numberOfActivated = 0;

        for(Ticket item : result) {
            if(item.isActive()){
                numberOfActivated+=1;
            }
            assertEquals(item.getUser().getUsername(), "username");
        }
        assertEquals(numberOfActivated, 2);

        verify(jwtUtils, times(1)).getUsernameFromToken("token");
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test
    public void getOwnedTicketsWrongTokenTest() {
        List<Ticket> result = ticketService.getOwnedTickets("token1");
        assertEquals(result, null);
        verify(jwtUtils, times(1)).getUsernameFromToken("token1");
        verify(userRepository, times(1)).findByUsername(Mockito.any(String.class));
    }

    @Test
    public void archiveTicketTest() {
        boolean result = ticketService.archiveTicket("1");
        assertEquals(result, true);
        verify(userRepository, times(1)).flush();
        verify(ticketRepository, times(1)).getOne(1L);
    }

    @Test
    public void activateTicketTest() {
        TicketDTO dto = new TicketDTO("token", "monthly", new Date(), new Date(), "1", false);
        Ticket result= ticketService.activateTicket(dto);
        assertEquals(result.isActive(), true);
        assertEquals(result.getId(), (Long)1L);
        verify(ticketRepository, times(1)).save(Mockito.any(Ticket.class));
        verify(ticketRepository, times(1)).getOne(1L);
    }

    @Test(expected = NullPointerException.class)
    public void activateTicketNullPointer() {
        TicketDTO dto = new TicketDTO("token", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.activateTicket(dto);
        verify(ticketRepository, times(1)).getOne(2L);
    }

    @Test
    public void createMultipleUseTicketsTest() {
        TicketDTO dto = new TicketDTO("token", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.activateTicket(dto);
        verify(ticketRepository, times(1)).getOne(2L);
    }
}
