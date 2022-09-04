package com.sp.conferenceendpoint.repositories;

import com.sp.conferenceendpoint.models.SessionTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<SessionTag, Long> {

}

