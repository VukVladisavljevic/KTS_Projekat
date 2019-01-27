package com.kits.project.integration.controller;

import com.kits.project.DTOs.*;
import com.kits.project.ProjectApplication;
import com.kits.project.model.*;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class LineControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    //-----------------before--------------------------------------------
    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    //-----------------create--------------------------------------------
    @Test
    public void testCreateLine(){
        LineDTO lineDTO = new LineDTO("Line12", true, new HashSet<StationDTO>() , null, new ArrayList<TicketDTO>());
        Line line = new Line(lineDTO);

        HttpEntity<Line> entity = new HttpEntity<Line>(line, headers);

        ResponseEntity<Line> response = restTemplate.exchange(
                createURLWithPort("api/line/create"),
                HttpMethod.POST, entity, Line.class);

        assertEquals(line.getIdLine(), response.getBody().getIdLine());
        //assertEquals(line.getName(), response.getBody().getName());
    }

    //-----------------delete--------------------------------------------
    @Test
    public void testDeleteLine(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                createURLWithPort("api/line/1"),
                HttpMethod.DELETE, entity,Boolean.class);
        assertEquals(true, response.getBody());
    }
/*
    //-----------------get all-------------------------------------------
    @Test
    public void testGetAllLines() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ParameterizedTypeReference<ArrayList<Line>> myBean =
                new ParameterizedTypeReference<ArrayList<Line>>() {};

        ResponseEntity<ArrayList<Line>> response = restTemplate.exchange(
                createURLWithPort("api/lines"),
                HttpMethod.GET, entity, myBean);

        assertEquals(3, response.getBody().size());

    }*/

    //-----------------get stations for line-----------------------------
    @Test
    public void testGetStationsForLine(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ParameterizedTypeReference<ArrayList<Station>> myBean =
                new ParameterizedTypeReference<ArrayList<Station>>() {};

        ResponseEntity<ArrayList<Station>> response = restTemplate.exchange(
                createURLWithPort("api/line/2/stations"),
                HttpMethod.GET, entity, myBean);

        assertEquals(0, response.getBody().size());
    }
/*
    //-----------------get lines for map---------------------------------
    @Test
    public void testGetLinesForMap(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/line/map"),
                HttpMethod.GET, entity, String.class);
        System.out.println(response);

    // nezavrsen
        //assertEquals(HttpStatus.OK, response.getStatusCodeValue());
    }
*//*
    //-----------------update--------------------------------------------
    @Test
    public void testUpdate(){
        LineDTO lineDTO = new LineDTO("Line56", true, null, null, null);
        HttpEntity<LineDTO> entity = new HttpEntity<LineDTO>(lineDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/line/2"),
                HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCodeValue());
    }*/
/*
    //-----------------add station---------------------------------------
    @Test
    public void testAddStation(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/line/2/add-station/1"),
                HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCodeValue());
    }

    //-----------------add time schedule---------------------------------
    @Test
    public void testAddTimeSchedule(){

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/line/2/add-time-schedule/1"),
                HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCodeValue());
    }

    //-----------------add ticket----------------------------------------
    @Test
    public void testAddTicket(){
        HttpEntity<Ticket> entity = new HttpEntity<Ticket>(ticket, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/line/2/add-ticket/1"),
                HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCodeValue());
    }

    //-----------------archive line--------------------------------------
    @Test
    public void testArchiveLine(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/line/6/archive"),
                HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCodeValue());
    }
*/
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
