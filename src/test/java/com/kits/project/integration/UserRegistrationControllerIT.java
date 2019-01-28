package com.kits.project.integration;

import com.kits.project.DTOs.AccountCreateDTO;
import com.kits.project.DTOs.LoginDTO;
import com.kits.project.DTOs.UserDTO;
import com.kits.project.ProjectApplication;
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

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserRegistrationControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp(){
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRegister(){
        AccountCreateDTO acc = new AccountCreateDTO(new LoginDTO("pera1", "pera1"), "Pera", "Peric", "mgr36995@gmail.com");
        HttpEntity<AccountCreateDTO> entity = new HttpEntity<>(acc, headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
                createURLWithPort("/api/register"),
                HttpMethod.POST, entity, UserDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testActivateAccount(){
        HttpEntity<AccountCreateDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                createURLWithPort("/api/activate?activationId=JOVA2"),
                HttpMethod.GET, entity, Boolean.class);
        assertEquals(true, response.getBody());
    }

    @Test
    public void testRegisterControllerAccount(){
        AccountCreateDTO acc = new AccountCreateDTO(new LoginDTO("pera123546", "pera123654"), "Pe6r5a", "Peric", "mgr36995@gmail.com");
        HttpEntity<AccountCreateDTO> entity = new HttpEntity<>(acc, headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
                createURLWithPort("/api/register_controller"),
                HttpMethod.POST, entity, UserDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
