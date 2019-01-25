package com.kits.project.unit;

import com.kits.project.model.AccountAuthority;
import com.kits.project.model.Authority;
import com.kits.project.model.User;
import com.kits.project.repositories.AccountAuthorityRepository;
import com.kits.project.services.implementations.AccountAuthorityServiceImplementation;
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
public class AccountAuthorityServiceTest {

    @MockBean
    private AccountAuthorityRepository accountAuthorityRepository;

    @Autowired
    private AccountAuthorityServiceImplementation accountAuthorityService;

    @Before
    public void setUp() {
        User user = new User("username", "pass", "Name", "Surname");
        Authority authority = new Authority("authority1");
        AccountAuthority accountAuthority = new AccountAuthority(user, authority);
        Mockito.when(accountAuthorityRepository.save(Mockito.any(AccountAuthority.class))).thenReturn(accountAuthority);
        Mockito.when(accountAuthorityRepository.AuthorityByAccId(1L)).thenReturn(-1);
        Mockito.when(accountAuthorityRepository.AuthorityByAccId(2L)).thenReturn(1);
    }

    @Test
    public void saveTest() {
        User user = new User("username", "pass", "Name", "Surname");
        Authority authority = new Authority("authority1");
        AccountAuthority accountAuthority = new AccountAuthority(user, authority);
        AccountAuthority result = accountAuthorityService.save(accountAuthority);

        assertEquals(result.getAuthority().getName(), "authority1");
        assertEquals(result.getAccount().getFirstName(), "Name");
        assertEquals(result.getAccount().getUsername(), "username");
        assertEquals(result.getAccount().getLastName(), "Surname");
        assertEquals(result.getAccount().getPassword(), "pass");

        verify(accountAuthorityRepository, times(1)).save(Mockito.any(AccountAuthority.class));
    }

    @Test
    public void removeTest() {
        accountAuthorityService.remove(1L);
        verify(accountAuthorityRepository, times(1)).deleteById(1L);
    }

    @Test
    public void authorityByAccIdNotFoundTest() {
        int result = accountAuthorityService.AuthorityByAccId(1L);
        assertEquals(result, -1);
        verify(accountAuthorityRepository, times(1)).AuthorityByAccId(1L);
    }

    @Test
    public void authorityByAccIdTest() {
        int result = accountAuthorityService.AuthorityByAccId(2L);
        assertEquals(result, 1);
        verify(accountAuthorityRepository, times(1)).AuthorityByAccId(2L);
    }
}
