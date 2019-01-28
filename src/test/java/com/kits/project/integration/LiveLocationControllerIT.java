package com.kits.project.integration;

import com.kits.project.ProjectApplication;
import com.kits.project.model.Transport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class LiveLocationControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testHandle(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ParameterizedTypeReference<ArrayList<Transport>> myBean =
                new ParameterizedTypeReference<ArrayList<Transport>>() {};

        ResponseEntity<ArrayList<Transport>> response = restTemplate.exchange(
                createURLWithPort("/location"),
                HttpMethod.GET, entity, myBean);
        assertEquals(1, response.getBody().size());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
