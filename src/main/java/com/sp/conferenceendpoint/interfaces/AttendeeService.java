package com.sp.conferenceendpoint.interfaces;

import com.sp.conferenceendpoint.models.Session;
import com.sp.conferenceendpoint.models.Workshop;

import java.util.List;

public interface AttendeeService {
    public List<Session> getAttendeeSessions(Long id);
    public List<Workshop> getAttendeeWorkshops(Long id);

}
