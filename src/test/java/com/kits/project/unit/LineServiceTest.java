package com.kits.project.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import com.kits.project.DTOs.LineDTO;
import com.kits.project.DTOs.MapLinesDTO;
import com.kits.project.DTOs.StationDTO;
import com.kits.project.model.Line;
import com.kits.project.model.LineStationsOrder;
import com.kits.project.model.Station;
import com.kits.project.repositories.LineRepository;
import com.kits.project.repositories.LineStationsOrderRepository;
import com.kits.project.repositories.StationRepository;
import com.kits.project.repositories.TransportRepository;
import com.kits.project.services.implementations.LineServiceImplementation;
import com.kits.project.services.implementations.StationServiceImplementation;
import java.util.*;
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
public class LineServiceTest {
    @MockBean
    private StationRepository stationRepository;

    @MockBean
    private LineRepository lineRepository;

    @MockBean
    private LineStationsOrderRepository lineStationsRepository;

    @MockBean
    private TransportRepository transportRepository;

    @Autowired
    private LineServiceImplementation linesService;

    @Before
    public void setUp() {
        Line existingLine = new Line(1L);

        Line addedLine = new Line("added_line");
        addedLine.setIdLine(2L);
        Station station2 = new Station(2L);
        Station station3 = new Station(3L);
        ArrayList<Station> stations = new ArrayList<Station>();
        station3.setName("name1");
        station2.setName("name2");
        stations.add(station2);
        stations.add(station3);
        addedLine.setStations(stations);
        LineStationsOrder lso = new LineStationsOrder();

        ArrayList<Line> lines = new ArrayList<Line>();
        lines.add(addedLine);
        lines.add(existingLine);

        ArrayList<LineStationsOrder> orderedStations = new ArrayList<LineStationsOrder>();
        LineStationsOrder l1 = new LineStationsOrder();
        LineStationsOrder l2 = new LineStationsOrder();
        l1.setStation(station2);
        l2.setStation(station3);
        orderedStations.add(l1);
        orderedStations.add(l2);

        Mockito.when(lineRepository.findByName("name")).thenReturn(existingLine);
        Mockito.when(lineRepository.findByName("name1")).thenReturn(null);
        Mockito.when(stationRepository.findById(2L)).thenReturn(Optional.of(station2));
        Mockito.when(stationRepository.findById(3L)).thenReturn(Optional.of(station3));
        Mockito.when(lineRepository.save(Mockito.any(Line.class))).thenReturn(addedLine);
        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(station2);
        Mockito.when(lineStationsRepository.save(Mockito.any(LineStationsOrder.class))).thenReturn(lso);
        Mockito.when(lineRepository.findAll()).thenReturn(lines);
        Mockito.when(lineStationsRepository.findByLineOrderByStationOrderAsc(Mockito.any(Line.class))).thenReturn(orderedStations);
    }

    @Test
    public void addNewLineLineExistsTest() {
        LineDTO newLine = new LineDTO();
        newLine.name = "name";
        Line result = linesService.addNewLine(newLine);
        assertEquals(result, null);
        verify(lineRepository, times(0)).save(new Line(newLine));
        verify(stationRepository, times(0)).findById(1L);
        verify(stationRepository, times(0)).save(new Station());
        verify(lineStationsRepository, times(0)).save(new LineStationsOrder());
    }

    @Test
    public void addNewLineLineSuccessfulTest() {
        LineDTO newLine = new LineDTO();
        newLine.name = "name1";
        StationDTO station2 = new StationDTO();
        StationDTO station3 = new StationDTO();
        station2.id = 2L;
        station3.id = 3L;
        newLine.stations = new HashSet<>();
        newLine.stations.add(station2);
        newLine.stations.add(station3);
        Line result = linesService.addNewLine(newLine);
        assertEquals(result.getName(), "added_line");

        verify(lineRepository, times(1)).save(Mockito.any(Line.class));
        verify(stationRepository, times(2)).findById(Mockito.any(Long.class));
        verify(stationRepository, times(2)).save(Mockito.any(Station.class));
        verify(lineStationsRepository, times(2)).save(Mockito.any(LineStationsOrder.class));
    }

    @Test(expected = NullPointerException.class)
    public void addNewLineLineWithoutStationsTest() {
        LineDTO newLine = new LineDTO();
        newLine.name = "name1";
        Line result = linesService.addNewLine(newLine);
        assertEquals(result, null);
    }

    @Test
    public void getAllLinesTest() {
        List<Line> result = linesService.getAllLines();
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "added_line");
        verify(lineRepository, times(1)).findAll();
    }

    @Test
    public void deleteLineTest() {
        boolean result = linesService.deleteLine(1L);
        assertEquals(result, true);
        verify(lineRepository, times(1)).delete(Mockito.any(Line.class));
        verify(lineRepository, times(1)).deleteLineStations(1L);
        verify(lineStationsRepository, times(1)).deleteLineStations(1L);
    }

    @Test
    public void getLinesForMapTest() {
        ArrayList<MapLinesDTO> result = linesService.getLinesForMap();
        assertEquals(result.size(), 2);
        for(MapLinesDTO item: result) {
            assertEquals(item.source.getName(), "name2");
            assertEquals(item.destination.getName(), "name1");
            assertEquals(item.waypoints.size(), 0);
        }
        verify(lineRepository, times(1)).findAll();
        verify(stationRepository, times(2)).findById(2L);
        verify(stationRepository, times(2)).findById(3L);
        verify(lineStationsRepository, times(2)).findByLineOrderByStationOrderAsc(Mockito.any(Line.class));
    }

    @Test
    public void getStationForLineTest() {
        ArrayList<Station> result = linesService.getStationForLine(2L);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "name2");
        assertEquals(result.get(1).getName(), "name1");
        verify(stationRepository, times(1)).findById(2L);
        verify(stationRepository, times(1)).findById(3L);
        verify(lineStationsRepository, times(1)).findByLineOrderByStationOrderAsc(Mockito.any(Line.class));
    }

}
