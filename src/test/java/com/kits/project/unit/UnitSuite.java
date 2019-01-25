package com.kits.project.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountAuthorityServiceTest.class,
        AuthorityServiceTest.class,
        LineServiceTest.class,
        PricelistServiceTest.class,
        StationServiceTest.class,
        TicketServiceTest.class,
        UserDetailsServiceTest.class,
        UserServiceTest.class
})
public class UnitSuite {
}
