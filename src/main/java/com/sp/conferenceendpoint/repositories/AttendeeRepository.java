package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {


}


