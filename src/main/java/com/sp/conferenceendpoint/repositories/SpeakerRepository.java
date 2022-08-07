package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {

}
