package com.kits.project.integration;

import com.kits.project.DTOs.StationDTO;
import com.kits.project.ProjectApplication;
import com.kits.project.model.Station;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StationControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    //----------------before-------------------------------
    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    //----------------create-------------------------------
    @Test
    public void testCreateStation(){
        StationDTO stationDTO = new StationDTO(1, "address3", "name3", null, 45, 19, true);

        Station station = new Station(stationDTO);
        HttpEntity<Station> entity = new HttpEntity<Station>(station, headers);

        ResponseEntity<Station> response = restTemplate.exchange(
                createURLWithPort("api/station"),
                HttpMethod.POST, entity, Station.class);

        assertEquals(stationDTO.name, response.getBody().getName());
    }

    //----------------index--------------------------------
    @Test
    public void testIndexStation() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ParameterizedTypeReference<List<Station>> myBean =
                new ParameterizedTypeReference<List<Station>>() {};

        ResponseEntity<List<Station>> response = restTemplate.exchange(
                createURLWithPort("api/stations"),
                HttpMethod.GET, entity, myBean);

        assertEquals(9, response.getBody().size());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
