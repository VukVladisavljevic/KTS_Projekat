package com.kits.project.unit;

import com.kits.project.DTOs.DepartureDTO;
import com.kits.project.DTOs.TicketDTO;
import com.kits.project.exception.TicketNotFoundException;
import com.kits.project.exception.UserNotFoundException;
import com.kits.project.model.*;
import com.kits.project.repositories.*;
import com.kits.project.security.JWTUtils;
import com.kits.project.services.implementations.TicketServiceImplementation;
import com.kits.project.services.implementations.TimeScheduleServiceImplementation;
import com.kits.project.services.interfaces.TicketServiceInterface;

import static org.junit.Assert.*;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TimeScheduleServiceTest {

    @MockBean
    private TimeScheduleRepository timeScheduleRepository;

    @MockBean
    private LineRepository lineRepository;

    @MockBean
    private TransportRepository transportRepository;

    @Autowired
    private TimeScheduleServiceImplementation timeScheduleService;

    @Before
    public void setUp() {

        Line line1 = new Line();
        Mockito.when(lineRepository.findByName("A1")).thenReturn(line1);

        Transport transport1 = new Transport();
        ArrayList list1 = new ArrayList<>();
        list1.add(transport1);


        Mockito.when(transportRepository.findByLine(line1)).thenReturn(list1);
        Mockito.when(lineRepository.findByName("NON")).thenReturn(null);


    }

    //test successful addDeparture - first Departure - list creation - workingDay
    @Test
    public void successfulAddDeparture(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("1");

        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO);
        assertNotNull(timeSchedule.getLine());
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 1);
    }

    //test successful addDeparture - second Departure - list already exists
    @Test
    public void successfulAddDeparture2(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("1");

        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO);
        assertNotNull(timeSchedule.getLine());
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 1);

        DepartureDTO departureDTO2 = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("1");

        TimeSchedule timeSchedule2 = timeScheduleService.addDeparture(departureDTO);
        assertNotNull(timeSchedule2.getLine());
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 2);
    }

    //test unsuccessful addDeparture - missing line
    @Test(expected = NullPointerException.class)
    public void unsuccessfulAddDeparture(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("NON");
        departureDTO.setDayOfWeek("1");

        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO);
        assertNotNull(timeSchedule.getLine());
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 1);
    }

    //test getDepartures - SUNDAY
    @Test
    public void successfulGetSundayDepartures(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("3");
        departureDTO.setTime("22:00");
        timeScheduleService.addDeparture(departureDTO);

        DepartureDTO departureDTO2 = new DepartureDTO();
        departureDTO2.setLineName("A1");
        departureDTO2.setDayOfWeek("3");
        departureDTO2.setTime("21:00");


        timeScheduleService.addDeparture(departureDTO2);
        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO2);
        System.out.println(timeScheduleService.getDepartures("A1", "3").size());
        assertEquals(timeScheduleService.getDepartures("A1", "3").size(), 3);
    }

    //test getDepartures - Saturday
    @Test
    public void successfulGetSaturdayDepartures(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("2");
        departureDTO.setTime("22:00");
        timeScheduleService.addDeparture(departureDTO);

        DepartureDTO departureDTO2 = new DepartureDTO();
        departureDTO2.setLineName("A1");
        departureDTO2.setDayOfWeek("2");
        departureDTO2.setTime("21:00");


        timeScheduleService.addDeparture(departureDTO2);
        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO2);
        System.out.println(timeScheduleService.getDepartures("A1", "2").size());
        assertEquals(timeScheduleService.getDepartures("A1", "2").size(), 3);
    }
    //test getDepartures - WorkingDay
    @Test
    public void successfulGetWorkingDayDepartures(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("1");
        departureDTO.setTime("22:00");
        timeScheduleService.addDeparture(departureDTO);

        DepartureDTO departureDTO2 = new DepartureDTO();
        departureDTO2.setLineName("A1");
        departureDTO2.setDayOfWeek("1");
        departureDTO2.setTime("21:00");


        timeScheduleService.addDeparture(departureDTO2);
        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO2);
        System.out.println(timeScheduleService.getDepartures("A1", "1").size());
        assertEquals(timeScheduleService.getDepartures("A1", "1").size(), 3);
    }

    //unscussesful get - missingLine
    @Test(expected = NullPointerException.class)
    public void unuccessfulGetDepartures(){
        assertEquals(timeScheduleService.getDepartures("A1", "1").size(), 3);
    }

    //test deleting - success
    @Test
    public void successfulDeleting(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("A1");
        departureDTO.setDayOfWeek("1");

        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO);
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 1);

        timeSchedule = timeScheduleService.deleteDeparture("A1","1", "0");
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 0);
    }

    //test deleting - missing line
    @Test(expected = NullPointerException.class)
    public void unsuccessfulDeleting(){
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setLineName("NON");
        departureDTO.setDayOfWeek("1");

        TimeSchedule timeSchedule = timeScheduleService.addDeparture(departureDTO);
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 1);

        timeSchedule = timeScheduleService.deleteDeparture("A1","1", "0");
        assertEquals(timeSchedule.getWorkingDaySchedule().size(), 0);
    }
}

