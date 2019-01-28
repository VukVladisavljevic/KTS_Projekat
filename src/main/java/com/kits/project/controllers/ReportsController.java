package com.kits.project.controllers;

import com.kits.project.DTOs.ReportDTO;
import com.kits.project.DTOs.RequestDateDTO;
import com.kits.project.exception.BadRequestException;
import com.kits.project.model.Pricelist;
import com.kits.project.model.Ticket;
import com.kits.project.model.TicketType;
import com.kits.project.services.interfaces.PricelistServiceInterface;
import com.kits.project.services.interfaces.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api")
public class ReportsController {

    @Autowired
    private PricelistServiceInterface pricelistServiceInterface;

    @Autowired
    private TicketServiceInterface ticketServiceInterface;

    @RequestMapping(
            value = "/reports/get_total",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReportDTO> getTotal(@RequestBody RequestDateDTO requestDateDTO) {
        float total = 0;
        float totalSingle = 0;
        float totalDaily = 0;
        float totalMonthly = 0;
        float totalYearly = 0;
        List<Ticket> listSingle =this.ticketServiceInterface.getAllTypeTicketsBetween(requestDateDTO.startDate, requestDateDTO.endDate, TicketType.SINGLE);
        List<Ticket> listMonthly =this.ticketServiceInterface.getAllTypeTicketsBetween(requestDateDTO.startDate, requestDateDTO.endDate, TicketType.MONTHLY);
        List<Ticket> listDaily =this.ticketServiceInterface.getAllTypeTicketsBetween(requestDateDTO.startDate, requestDateDTO.endDate, TicketType.DAILY);
        List<Ticket> listYearly =this.ticketServiceInterface.getAllTypeTicketsBetween(requestDateDTO.startDate, requestDateDTO.endDate, TicketType.YEARLY);
        float single = listSingle.size();
        float daily = listDaily.size();
        float monthly = listMonthly.size();
        float yearly = listYearly.size();
        float noTotal = single + daily + monthly + yearly;

        for(Ticket t : listSingle) {
            total += t.getPrice();
            totalSingle += t.getPrice();
        }

        for(Ticket t : listMonthly) {
            total += t.getPrice();
            totalMonthly += t.getPrice();
        }

        for(Ticket t : listDaily) {
            total += t.getPrice();
            totalDaily += t.getPrice();
        }

        for(Ticket t : listYearly) {
            total += t.getPrice();
            totalYearly += t.getPrice();
        }

        float avgPerDay = total/noTotal;

        ReportDTO report = new ReportDTO(noTotal, total, avgPerDay, single, totalSingle, daily, totalDaily, monthly, totalMonthly, yearly, totalYearly);

        return new ResponseEntity(report, HttpStatus.OK);
    }
}
