package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.SessionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionScheduleRepository extends JpaRepository<SessionSchedule, Long> {

}

