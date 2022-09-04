package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Character> {

}

