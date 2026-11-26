package com.project.customerserviceRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.customerserviceEntity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

}
