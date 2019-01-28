package com.kits.project.integration;

import com.kits.project.DTOs.LoginDTO;
import com.kits.project.DTOs.TokenDTO;
import com.kits.project.ProjectApplication;
import com.kits.project.model.User;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testLogin(){
        LoginDTO lDTO = new LoginDTO("aa", "aa");
        HttpEntity<LoginDTO> entity = new HttpEntity<>(lDTO, headers);

        ResponseEntity<TokenDTO> response = restTemplate.exchange(
                createURLWithPort("/api/login"),
                HttpMethod.POST, entity, TokenDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCheckUsername(){
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                createURLWithPort("/api/check_username?username=rrrrrr"),
                HttpMethod.GET, entity, Boolean.class);
        assertEquals(false, response.getBody());
    }

    @Test
    public void testGetAllUsers(){
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ParameterizedTypeReference<ArrayList<User>> myBean =
                new ParameterizedTypeReference<ArrayList<User>>() {};

        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                createURLWithPort("/api/users/get_all"),
                HttpMethod.GET, entity, myBean);
        assertEquals(3, response.getBody().size());
    }

    @Test
    public void testRemove(){
        HttpEntity<String> entity = new HttpEntity<>("user", headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/users/remove_user"),
                HttpMethod.POST, entity, ResponseEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
