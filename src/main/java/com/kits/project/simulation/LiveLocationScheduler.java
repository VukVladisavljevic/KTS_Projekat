package com.kits.project.simulation;

import com.kits.project.DTOs.TransportDTO;
import com.kits.project.model.Line;
import com.kits.project.model.LineStationsOrder;
import com.kits.project.model.TimeSchedule;
import com.kits.project.model.Transport;
import com.kits.project.repositories.LineRepository;
import com.kits.project.repositories.LineStationsOrderRepository;
import com.kits.project.repositories.TimeScheduleRepository;
import com.kits.project.repositories.TransportRepository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LiveLocationScheduler {

    private static final Logger log = LoggerFactory.getLogger(LiveLocationScheduler.class);

    @Autowired
    private TimeScheduleRepository timeScheduleRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private LineStationsOrderRepository lineStationsRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime() {
        List<TimeSchedule> schedules = timeScheduleRepository.findCurrentDepartures();
        ArrayList<TransportDTO> allTransporters = new ArrayList<TransportDTO>();
        System.out.println(schedules.size());
        for(TimeSchedule t : schedules) {
            Transport transport = t.getTransport();
            LineStationsOrder nextStation = lineStationsRepository.findNextStation(t.getLine().getIdLine(), transport.getStationOrder() + 1);
            if(nextStation==null){
                LineStationsOrder firstStation = lineStationsRepository.findNextStation(t.getLine().getIdLine(), 0);
                transport.setStation(firstStation.getStation());
                transport.setStationOrder(0);
                allTransporters.add(new TransportDTO(transport.getId(), t.getLine().getIdLine(), transport.getStation()));
                transportRepository.save(transport);
                continue;
            }
            transport.setStationOrder(nextStation.getOrder());
            transport.setStation(nextStation.getStation());
            allTransporters.add(new TransportDTO(transport.getId(), t.getLine().getIdLine(), transport.getStation()));
            transportRepository.save(transport);
        }
        System.out.println("SDAD");
        template.convertAndSend("/live-location", allTransporters);
    }
}
