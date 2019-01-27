package com.kits.project.integration.controller;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.ProjectApplication;
import com.kits.project.model.Pricelist;
import com.kits.project.model.TicketType;
import com.kits.project.repositories.PricelistRepository;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricelistControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    PricelistRepository pricelistRepository;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    //----------------------before-----------------------------------------------

    @Before
    public void setUp(){

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    }

    //----------------------get current-------------------------------------------

    @Test
    public void testGetCurrentPriceList() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ParameterizedTypeReference<ArrayList<Pricelist>> myBean =
                new ParameterizedTypeReference<ArrayList<Pricelist>>() {};

        ResponseEntity<ArrayList<Pricelist>> response = restTemplate.exchange(
                createURLWithPort("api/pricelist/getcurrent"),
                HttpMethod.GET, entity, myBean);

        assertEquals(1, response.getBody().size());
    }

    //----------------------add new-----------------------------------------------

    @Test
    public void testCreatePriceList(){
        PricelistDTO plDTO1 = new PricelistDTO(new GregorianCalendar(2022, Calendar.JANUARY, 1).getTime(), new GregorianCalendar(2022, Calendar.FEBRUARY, 2).getTime(), TicketType.SINGLE, 50);

        HttpEntity<PricelistDTO> entity = new HttpEntity<PricelistDTO>(plDTO1, headers);

        ResponseEntity<Pricelist> response = restTemplate.exchange(
                createURLWithPort("/api/pricelist/create"),
                HttpMethod.POST, entity, Pricelist.class);

        assertEquals(plDTO1.startDate, response.getBody().getStartDate());
        assertEquals(plDTO1.endDate, response.getBody().getEndDate());
        assertEquals(plDTO1.ticketType, response.getBody().getTicketType());

    }

    //----------------------get all------------------------------------------------

    @Test
    public void tesGetAllPriceLists(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ParameterizedTypeReference<ArrayList<Pricelist>> myBean =
                new ParameterizedTypeReference<ArrayList<Pricelist>>() {};

        ResponseEntity<ArrayList<Pricelist>> response = restTemplate.exchange(
                createURLWithPort("api/pricelist/getall"),
                HttpMethod.GET, entity, myBean);

        assertEquals(5, response.getBody().size());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
