package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.AttendeeTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeTicketRepository extends JpaRepository<AttendeeTicket, Long> {

}

