package com.kits.project.unit;

import com.kits.project.exception.NotFoundException;
import com.kits.project.model.Authority;
import com.kits.project.repositories.AuthorityRepository;
import com.kits.project.services.implementations.AuthorityServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorityServiceTest {

    @MockBean
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityServiceImplementation authorityService;

    @Before
    public void setUp() {
        Authority authority = new Authority("authority1" );
        Mockito.when(authorityRepository.findByName("authority1")).thenReturn(authority);
    }

    @Test
    public void findByNameTest() {
        Authority result = authorityService.findByName("authority1");
        assertEquals(result.getName(), "authority1");
        verify(authorityRepository, times(1)).findByName("authority1");
    }

    @Test(expected = NotFoundException.class)
    public void findByNameNotFoundTest() {
        Authority result = authorityService.findByName("authority1123");
        verify(authorityRepository, times(1)).findByName("authority1123");
    }

}
