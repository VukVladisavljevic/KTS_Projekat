package com.kits.project.integration.controller;

import com.kits.project.DTOs.DepartureDTO;
import com.kits.project.DTOs.LineDTO;
import com.kits.project.DTOs.StationDTO;
import com.kits.project.DTOs.TimeScheduleDTO;
import com.kits.project.ProjectApplication;
import com.kits.project.model.TimeSchedule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TimeScheduleControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateTimeSchedule(){
        DepartureDTO departureDTO = new DepartureDTO("12:10", "2", "Line2");
        HttpEntity<DepartureDTO> entity = new HttpEntity<>(departureDTO, headers);

        ResponseEntity<TimeSchedule> response = restTemplate.exchange(
                createURLWithPort("api/time-schedule/create"),
                HttpMethod.POST, entity, TimeSchedule.class);

        assertEquals(response.getBody(), null);
    }

    @Test
    public void testDelete(){
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<TimeSchedule> response = restTemplate.exchange(
                createURLWithPort("api/time-schedule/line/Line2/1/0"),
                HttpMethod.DELETE, entity, TimeSchedule.class);

        assertEquals("1", response.getBody().getId().toString());

    }
/*
    @Test

*/
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
