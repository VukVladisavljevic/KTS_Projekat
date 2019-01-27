package com.kits.project.repositories;


import com.kits.project.model.Ticket;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM tickets WHERE (CURTIME() BETWEEN start_time AND end_time) AND ?1 = user_id", nativeQuery = true)
    List<Ticket> getActiveTicketsForUser(Long id);
}
