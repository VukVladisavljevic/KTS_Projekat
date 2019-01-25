package com.kits.project.controllers;

import com.kits.project.DTOs.RequestDateDTO;
import com.kits.project.exception.BadRequestException;
import com.kits.project.model.Pricelist;
import com.kits.project.model.Ticket;
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
    public ResponseEntity<Float> getTotal(@RequestBody RequestDateDTO requestDateDTO) {
        float total = 0;
        Date sDate = requestDateDTO.startDate;
        Date eDate = requestDateDTO.endDate;
        ArrayList<Pricelist> allPricelist = pricelistServiceInterface.getAllPricelists();
        List<Ticket> allTickets = ticketServiceInterface.getAllTickets();


        long diffInMillies = Math.abs(eDate.getTime() - sDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        Calendar cal = Calendar.getInstance();

        for(int i = 0; i < diff+1; i++) {
            ArrayList<Pricelist> currentPricelist = new ArrayList<>();
            for(Pricelist p : allPricelist) {
                if(p.getStartDate().compareTo(sDate) <= 0 && p.getEndDate().compareTo(sDate) >= 0)
                    currentPricelist.add(p);
            }

            for(Ticket t : allTickets) {
                for(Pricelist cp : currentPricelist) {
                    if(cp.getTicketType().equals(t.getTicketType()) && t.getStartTime().compareTo(sDate) == 0)
                        total += cp.getPrice();
                }
            }

            cal.setTime(sDate);
            cal.add(Calendar.DATE, 1);
            sDate = cal.getTime();
        }

        return new ResponseEntity(total, HttpStatus.OK);
    }
}
