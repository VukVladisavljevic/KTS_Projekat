package com.kits.project.unit_tests.services;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.exception.TicketNotFoundException;
import com.kits.project.exception.UserNotFoundException;
import com.kits.project.model.Ticket;
import com.kits.project.model.TicketType;
import com.kits.project.model.User;
import com.kits.project.repositories.TicketRepository;
import com.kits.project.repositories.UserRepository;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.implementations.TicketServiceImplementation;
import com.kits.project.services.interfaces.TicketServiceInterface;

import static org.junit.Assert.*;
import static org.testng.Assert.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TicketServiceUnitTest {


    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JWTUtils jwtUtils;

    @Autowired
    private TicketServiceInterface ticketService;

    @Before
    public void setUp() {

        //successful oneWay ticket buy
        User user1 = new User();
        user1.setUsername("user1");

        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        Mockito.when(ticketRepository.getOne(1L)).thenReturn(ticket1);

        user1.addTicket(ticket1);

        Mockito.when(jwtUtils.getUsernameFromToken("token1")).thenReturn("user1");
        Mockito.when(userRepository.findByUsername("user1")).thenReturn(user1);


        //unsuccesful
        Mockito.when(jwtUtils.getUsernameFromToken("token2-fail")).thenReturn(null);
        Mockito.when(ticketRepository.getOne(2L)).thenReturn(null);


    }

    @Test
    public void successfulOneUseTicketBuy() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token1";

        List<Ticket> ticketsOwned = ticketService.getOwnedTickets(ticketDTO.token);
        assertEquals(ticketsOwned.size(), 1);

        Ticket ticketBought = ticketService.createOneUseTicket(ticketDTO);
        assertEquals(ticketBought.getUser().getUsername(), "user1");
        assertEquals(ticketBought.getTicketType(), TicketType.SINGLE);

        assertEquals(ticketsOwned.size(), 2);
    }

    //pokusaj kupovine karte od strane nepostojeceg korisnika
    @Test(expected = UserNotFoundException.class)
    public void unsuccessfulOneUseTicketBuy() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token2";
        Ticket ticketBought = ticketService.createOneUseTicket(ticketDTO);
        //assertEquals(ticketBought.getUser().getUsername(), "user1");
    }

    //uspesna kupovina mesecne karte
    @Test
    public void successfulMonthlyTicketBuy() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token1";
        ticketDTO.ticketType = "monthly";

        List<Ticket> ticketsOwned = ticketService.getOwnedTickets(ticketDTO.token);
        assertEquals(ticketsOwned.size(), 1);

        Ticket ticketBought = ticketService.createMultipleUseTicket(ticketDTO);
        assertEquals(ticketBought.getUser().getUsername(), "user1");
        assertEquals(ticketBought.getTicketType(), TicketType.MONTHLY);

        assertEquals(ticketsOwned.size(), 2);
    }

    //pokusaj kupovine mesecne karte od strane nepostojeceg korisnika
    @Test(expected = UserNotFoundException.class)
    public void unsuccessfulMonthlyTicketBuy() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token2-fail";
        ticketDTO.ticketType = "yearly";

        Ticket ticketBought = ticketService.createMultipleUseTicket(ticketDTO);

    }

    //uspesna kupovina godisnje karte
    @Test
    public void successfulYearlyTicketBuy() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token1";
        ticketDTO.ticketType = "yearly";

        List<Ticket> ticketsOwned = ticketService.getOwnedTickets(ticketDTO.token);
        assertEquals(ticketsOwned.size(), 1);

        Ticket ticketBought = ticketService.createMultipleUseTicket(ticketDTO);
        assertEquals(ticketBought.getUser().getUsername(), "user1");
        assertEquals(ticketBought.getTicketType(), TicketType.YEARLY);

        assertEquals(ticketsOwned.size(), 2);
    }

    //pokusaj kupovine godisnje karte od strane nepostojeceg korisnika
    @Test(expected = UserNotFoundException.class)
    public void unsuccessfulYearlyTicketBuy() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token2-fail";
        ticketDTO.ticketType = "yearly";

        Ticket ticketBought = ticketService.createMultipleUseTicket(ticketDTO);

    }

    //pokusaj kupovine karte neodgovarajuceg tipa
    @Test(expected = UserNotFoundException.class)
    public void invalidTicketTypePassed() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.token = "token2";
        ticketDTO.ticketType = "invalid";

        List<Ticket> ticketsOwned = ticketService.getOwnedTickets(ticketDTO.token);
        assertEquals(ticketsOwned.size(), 1);

        Ticket ticketBought = ticketService.createMultipleUseTicket(ticketDTO);
        assertNull(ticketBought);

        assertEquals(ticketsOwned.size(), 1);
    }

    //uspesna aktivacija karte
    @Test
    public void successfulTicketActivation() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.id = "1";

        Ticket ticketBeforeActivation = ticketRepository.getOne(1L);
        assertNull(ticketBeforeActivation.getEndTime(), null);
        assertNull(ticketBeforeActivation.getStartTime(), null);
        assertFalse(ticketBeforeActivation.isActive());

        Ticket ticketBought = ticketService.activateTicket(ticketDTO);
        assertNotNull(ticketBought.getStartTime());
        assertNotNull(ticketBought.getEndTime());
        assertTrue(ticketBought.isActive());
    }

    //pokusaj aktivacije nepostojece karte
    @Test(expected = TicketNotFoundException.class)
    public void unsuccessfulTicketActivation() {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.id = "2";
        ticketService.activateTicket(ticketDTO);
    }

    //citanje svih postojecih karata za korisnika
    @Test
    public void successfulOwnedTicketsReading() {
        String token = "token1";
        List<Ticket> ticketsOwned = ticketService.getOwnedTickets(token);

        assertEquals(ticketsOwned.size(), 1);
    }

    //pokusaj citanja karata od strane nepostojeceg korisnika
    @Test(expected = UserNotFoundException.class)
    public void unsuccessfulOwnedTicketsReading() {
        String token = "token2-fail";
        List<Ticket> ticketsOwned = ticketService.getOwnedTickets(token);
    }

}