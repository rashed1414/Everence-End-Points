package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {

}
