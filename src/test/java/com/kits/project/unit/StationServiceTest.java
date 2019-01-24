package com.kits.project.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Optional;

import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Station;
import com.kits.project.repositories.StationRepository;
import com.kits.project.services.implementations.StationServiceImplementation;
import com.sun.tools.javac.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class StationServiceTest {

    @MockBean
    private StationRepository stationRepository;

    @Autowired
    private StationServiceImplementation stationService;

    @Before
    public void setUp() {

        StationDTO station1 = new StationDTO();
        station1.name = "station_name";
        station1.address = "address";
        station1.lat = (float)12.12;
        station1.lng = (float)13.13;
        station1.id = 1L;

        Station station2 = new Station(2L);

        Station station3 = new Station(3L);
        station3.setName("name1");

        Mockito.when(stationRepository.findByName("name1")).thenReturn(station3);
        Mockito.when(stationRepository.findByName("")).thenReturn(null);
        Mockito.when(stationRepository.findByName("station_name")).thenReturn(null);

        Mockito.when(stationRepository.save(station2)).thenReturn(null);

        ArrayList<Station> stations = new ArrayList<Station>();
        stations.add(station2);
        stations.add(station3);
        Mockito.when(stationRepository.findAll()).thenReturn(stations);

    }

    @Test
    public void addNewStationStationExistsTest() {
        StationDTO newStation= new StationDTO();
        newStation.name = "name1";
        Station result = stationService.addNewStation(newStation);
        assertEquals(result, null);
        verify(stationRepository, times(1)).findByName("name1");
    }

    @Test
    public void addNewStationSuccessfulTest() {
        StationDTO newStation= new StationDTO();
        newStation.name = "station_name";
        newStation.address = "address";
        newStation.lat = (float)12.12;
        newStation.lng = (float)13.13;
        newStation.id = 1L;
        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(new Station(newStation));
        Station result = stationService.addNewStation(newStation);
        assertEquals(result.getName(), "station_name");
        assertEquals(result.getAddress(), "address");

        verify(stationRepository, times(1)).findByName("station_name");
        verify(stationRepository, times(1)).save(Mockito.any(Station.class));
    }

    @Test
    public void emptyNameAddNewStationTest(){
        StationDTO newStation= new StationDTO();
        newStation.name = "";
        Station result = stationService.addNewStation(newStation);
        assertEquals(result, null);
    }

    @Test
    public void getStationsSuccessfully(){
        ArrayList<Station> result = (ArrayList) stationService.getStations();
        assertEquals(result.size(), 2);
        verify(stationRepository, times(1)).findAll();
    }

}