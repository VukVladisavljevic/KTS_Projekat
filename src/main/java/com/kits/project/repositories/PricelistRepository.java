package com.kits.project.repositories;

import com.kits.project.model.Pricelist;

import com.kits.project.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Date;



public interface PricelistRepository  extends JpaRepository<Pricelist, Long> {
    @Query(value = "SELECT * FROM pricelist WHERE (CURTIME() BETWEEN startdate AND enddate) ORDER BY FIELD(ticket_type, 'SINGLE','MONTHLY', 'YEARLY') ASC", nativeQuery = true)
    ArrayList<Pricelist> getCurrent(Date current);

    @Query("SELECT p FROM Pricelist p WHERE (?1 BETWEEN p.startDate AND p.endDate OR ?2 BETWEEN p.startDate AND p.endDate) AND ?3 = p.ticketType")
    ArrayList<Pricelist> checkIfUnique(Date startDate, Date endDate, TicketType ticketType);

    @Query("SELECT p FROM Pricelist p ORDER BY p.startDate ASC")
    ArrayList<Pricelist> getAll();
}
