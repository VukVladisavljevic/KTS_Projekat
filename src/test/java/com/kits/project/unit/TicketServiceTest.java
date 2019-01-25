package com.kits.project.unit;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.model.Ticket;
import com.kits.project.model.TicketType;
import com.kits.project.model.User;
import com.kits.project.repositories.TicketRepository;
import com.kits.project.repositories.UserRepository;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.implementations.TicketServiceImplementation;

import java.util.Calendar;
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

import static org.junit.Assert.*;
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
        Mockito.when(ticketRepository.getOne(1L)).thenReturn(ticket1);
        Mockito.when(jwtUtils.getUsernameFromToken("token")).thenReturn("username");
        Mockito.when(jwtUtils.getUsernameFromToken("token1")).thenReturn(null);
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
        verify(userRepository, times(1)).findByUsername(null);
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

    @Test
    public void activateTicketNullTest() {
        TicketDTO dto = new TicketDTO("token", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.activateTicket(dto);
        assertEquals(result, null);
        verify(ticketRepository, times(1)).getOne(2L);
    }

    @Test
    public void createOneUseTicketTest() {
        TicketDTO dto = new TicketDTO("token", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createOneUseTicket(dto);
        assertEquals(result.getTicketType(), TicketType.SINGLE);
        assertEquals(result.getStartTime(), null);
        assertEquals(result.getEndTime(), null);
        assertEquals(result.isActive(), false);
        verify(jwtUtils, times(1)).getUsernameFromToken("token");
        verify(ticketRepository, times(1)).save(Mockito.any(Ticket.class));
        verify(userRepository, times(1)).findByUsername("username");
        verify(userRepository, times(1)).flush();
    }

    @Test
    public void createOneUseTicketUserNotFoundTest() {
        TicketDTO dto = new TicketDTO("token1", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createOneUseTicket(dto);
        assertEquals(result, null);
        verify(jwtUtils, times(1)).getUsernameFromToken("token1");
        verify(ticketRepository, times(0)).save(Mockito.any(Ticket.class));
        verify(userRepository, times(1)).findByUsername(null);
        verify(userRepository, times(0)).flush();
    }

    @Test
    public void createMultipleUseTicketUserNotFoundTest() {
        TicketDTO dto = new TicketDTO("token1", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createMultipleUseTicket(dto);
        assertEquals(result, null);
        verify(jwtUtils, times(1)).getUsernameFromToken("token1");
        verify(ticketRepository, times(0)).save(Mockito.any(Ticket.class));
        verify(userRepository, times(1)).findByUsername(null);
        verify(userRepository, times(0)).flush();
    }

    @Test
    public void createMultipleUseTicketDailyTicketTest() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        TicketDTO dto = new TicketDTO("token", "daily", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createMultipleUseTicket(dto);
        start.setTime(result.getStartTime());
        end.setTime(result.getEndTime());
        tomorrow.add(Calendar.DATE, 1);

        assertEquals(result.getTicketType(), TicketType.DAILY);
        assertEquals(now.getTime().getDate(), start.getTime().getDate());
        assertEquals(tomorrow.getTime().getDate(), end.getTime().getDate());
        assertEquals(result.isActive(), false);

        verify(jwtUtils, times(1)).getUsernameFromToken("token");
        verify(ticketRepository, times(1)).save(Mockito.any(Ticket.class));
        verify(userRepository, times(1)).findByUsername("username");
        verify(userRepository, times(1)).flush();
    }

    @Test
    public void createMultipleUseTicketYearTicketTest() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        TicketDTO dto = new TicketDTO("token", "yearly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createMultipleUseTicket(dto);
        start.setTime(result.getStartTime());
        end.setTime(result.getEndTime());
        tomorrow.add(Calendar.DATE, 365);

        assertEquals(result.getTicketType(), TicketType.YEARLY);
        assertEquals(now.getTime().getDate(), start.getTime().getDate());
        assertEquals(tomorrow.getTime().getDate(), end.getTime().getDate());
        assertEquals(result.isActive(), false);

        verify(jwtUtils, times(1)).getUsernameFromToken("token");
        verify(ticketRepository, times(1)).save(Mockito.any(Ticket.class));
        verify(userRepository, times(1)).findByUsername("username");
        verify(userRepository, times(1)).flush();
    }

    @Test
    public void createMultipleUseTicketMonthlyTicketTest() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        Calendar tomorrow = Calendar.getInstance();
        TicketDTO dto = new TicketDTO("token", "monthly", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createMultipleUseTicket(dto);
        start.setTime(result.getStartTime());
        end.setTime(result.getEndTime());
        tomorrow.add(Calendar.DATE, 30);

        assertEquals(result.getTicketType(), TicketType.MONTHLY);
        assertEquals(now.getTime().getDate(), start.getTime().getDate());
        assertEquals(tomorrow.getTime().getDate(), end.getTime().getDate());
        assertEquals(result.isActive(), false);

        verify(jwtUtils, times(1)).getUsernameFromToken("token");
        verify(ticketRepository, times(1)).save(Mockito.any(Ticket.class));
        verify(userRepository, times(1)).findByUsername("username");
        verify(userRepository, times(1)).flush();
    }

    @Test
    public void createMultipleUseTicketWrongTicketTypeTest() {
        TicketDTO dto = new TicketDTO("token", "single", new Date(), new Date(), "2", false);
        Ticket result= ticketService.createMultipleUseTicket(dto);
        assertEquals(result, null);
        verify(jwtUtils, times(1)).getUsernameFromToken("token");
        verify(userRepository, times(1)).findByUsername("username");
    }
}
