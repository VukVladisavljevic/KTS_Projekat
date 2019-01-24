package com.kits.project.unit;

import com.kits.project.DTOs.UserDTO;
import com.kits.project.exception.BadRequestException;
import com.kits.project.exception.NotFoundException;
import com.kits.project.model.User;
import com.kits.project.repositories.UserRepository;
import com.kits.project.services.implementations.UserServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImplementation userService;

    @Before
    public void setUp() {
        User user = new User("username", "password", "name", "last name");
        User user2 = new User("username", "password1", "name", "last name");

        Mockito.when(userRepository.findByUsername("username")).thenReturn(user);
        Mockito.when(userRepository.findByUsername("username1")).thenReturn(null);
        Mockito.when(userRepository.findByUsernameAndPassword("username", "password")).thenReturn(user);
        Mockito.when(userRepository.findByUsernameAndPassword("username1", "password1")).thenReturn(null);
        Mockito.when(userRepository.findByUsernameAndPassword("username", "password1")).thenReturn(user2);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user2);
        Mockito.when(userRepository.findByActivationId("activation_id")).thenReturn(user);
        Mockito.when(userRepository.findByActivationId("activation_id_1")).thenReturn(null);
    }

    @Test(expected = NotFoundException.class)
    public void findByUsernameNullTest() {
        userService.findByUsername("username1");
        verify(userRepository, times(1)).findByUsername("username1");

    }

    @Test
    public void findByUsernameTest() {
        User result = userService.findByUsername("username");
        assertEquals(result.getUsername(), "username");
        assertEquals(result.getFirstName(), "name");
        verify(userRepository, times(1)).findByUsername("username");

    }

    @Test
    public void loginTest() {
        UserDTO user = new UserDTO("username", "password", "name", "last name");
        User result = userService.login(user);
        assertEquals(result.getUsername(), "username");
        assertEquals(result.getFirstName(), "name");
        verify(userRepository, times(1)).findByUsernameAndPassword("username", "password");
    }

    @Test
    public void loginUserNotFoundTest() {
        UserDTO user = new UserDTO("username1", "password1", "name1", "last name1");
        User result = userService.login(user);
        assertEquals(result, null);
        verify(userRepository, times(1)).findByUsernameAndPassword("username1", "password1");

    }

    @Test
    public void loginWrongPasswordTest() {
        UserDTO user = new UserDTO("username", "password1321", "name", "last name");
        User result = userService.login(user);
        assertEquals(result, null);
        verify(userRepository, times(1)).findByUsernameAndPassword("username", "password1321");

    }

    @Test
    public void registerUsernameTakenTest() {
        UserDTO user = new UserDTO("username", "password1321", "name", "last name");
        User result = userService.register(user);
        assertEquals(result, null);
        verify(userRepository, times(1)).findByUsername("username");

    }

    @Test
    public void registerTest() {
        UserDTO user = new UserDTO("username1", "password1321", "name", "last name");
        User result = userService.register(user);
        assertEquals(result.getUsername(), "username");
        verify(userRepository, times(1)).findByUsername("username1");
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void isUsernameTakenTest() {
        boolean result = userService.isUsernameTaken("username");
        assertEquals(result, true);
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test
    public void usernameNotTakenTest() {
        boolean result = userService.isUsernameTaken("username1");
        assertEquals(result, false);
        verify(userRepository, times(1)).findByUsername("username1");
    }

    @Test(expected = BadRequestException.class)
    public void checkUsernameTest() {
        userService.checkUsername("username");
        verify(userRepository, times(1)).findByUsername("username");
    }

    @Test
    public void checkUsernameFreeTest() {
        userService.checkUsername("username1");
        verify(userRepository, times(1)).findByUsername("username1");
    }

    @Test
    public void saveUserTest() {
        User user = new User("new", "pass", "John", "John");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        user = userService.save(user);
        assertEquals(user.getUsername(), "new");
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void findByActivationIdTest() {
        User user = userService.findByActivationId("activation_id");
        assertEquals(user.getUsername(), "username");
        verify(userRepository, times(1)).findByActivationId("activation_id");
    }

    @Test(expected = NotFoundException.class)
    public void findByActivationIdNotFoundTest() {
        User user = userService.findByActivationId("activation_id_1");
        assertEquals(user.getUsername(), "username");
        verify(userRepository, times(1)).findByActivationId("activation_id_1");
    }
}
