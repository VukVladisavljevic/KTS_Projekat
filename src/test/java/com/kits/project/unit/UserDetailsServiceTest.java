package com.kits.project.unit;

import com.kits.project.model.User;
import com.kits.project.repositories.UserRepository;
import com.kits.project.services.implementations.UserDetailsServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDetailsServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImplementation userDetailsService;

    @Before
    public void setUp() {
        User user = new User("username", "password", "name", "last name");

        Mockito.when(userRepository.findByUsername("username")).thenReturn(user);
        Mockito.when(userRepository.findByUsername("username1")).thenReturn(null);
    }

    @Test
    public void loadUserByUsernameTest() {
        UserDetails result = userDetailsService.loadUserByUsername("username");
        System.out.println(result);
        assertEquals(result.getUsername(), "username");
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameNotFoundTest() {
        UserDetails result = userDetailsService.loadUserByUsername("username1");
        verify(userRepository, times(1)).findByUsername("username1");
    }
}
