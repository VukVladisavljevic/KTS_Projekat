package com.kits.project.integration;

import com.kits.project.DTOs.TicketDTO;
import com.kits.project.ProjectApplication;
import com.kits.project.model.Ticket;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TicketControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    /*@Test
    public void testCreateTicket(){
        TicketDTO ticketDTO = new TicketDTO("789", "SINGLE", new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(), "789", false);

        HttpEntity<TicketDTO> entity = new HttpEntity<>(ticketDTO, headers);

        ResponseEntity<Ticket> response = restTemplate.exchange(
                createURLWithPort("/api/ticket/create"),
                HttpMethod.POST, entity, Ticket.class);

        assertEquals(ticket.getUser(), response.getBody().getUser());
    }*/

    @Test
    public void testActivateTicket(){
        TicketDTO ticketDTO = new TicketDTO("789", "SINGLE", new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(), new GregorianCalendar(2019, Calendar.FEBRUARY, 1).getTime(), "2", false);

        HttpEntity<TicketDTO> entity = new HttpEntity<>(ticketDTO, headers);

        ResponseEntity<Ticket> response = restTemplate.exchange(
                createURLWithPort("/api/ticket/activate"),
                HttpMethod.POST, entity, Ticket.class);

        assertEquals(ticketDTO.id, response.getBody().getId().toString());
    }

    @Test
    public void testArchiveTicket(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                createURLWithPort("/api/ticket/2"),
                HttpMethod.DELETE, entity, Boolean.class);

        assertEquals(true, response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
