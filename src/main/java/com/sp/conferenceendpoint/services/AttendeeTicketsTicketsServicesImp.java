package com.sp.conferenceendpoint.services;

import com.sp.conferenceendpoint.interfaces.AttendeeTicketsService;
import com.sp.conferenceendpoint.models.AttendeeTicket;
import com.sp.conferenceendpoint.models.Session;
import com.sp.conferenceendpoint.models.Speaker;
import com.sp.conferenceendpoint.models.Workshop;
import com.sp.conferenceendpoint.repositories.AttendeeTicketRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttendeeTicketsTicketsServicesImp implements AttendeeTicketsService {
    private AttendeeTicketRepository attendeeTicketRepository;

    @Override
    public List<Session> getAttendeeSessions(Long id) {
        List<Workshop> workshops = new ArrayList<>();
        List<Session> sessions = new ArrayList<>();
        if(attendeeTicketRepository.existsById(id)){
            AttendeeTicket attendeeTicket = attendeeTicketRepository.findById(id).get();
            workshops = attendeeTicket.getWorkshops();
            for(Workshop workshop : workshops){
                List<Speaker> speakers = workshop.getSpeakers();
                for(Speaker speaker : speakers){
                    List<List<Session>> sessionsList = Collections.singletonList(speaker.getSessions());
                    for(List<Session> sessionList : sessionsList){
                        sessions.addAll(sessionList);
                    }

                }
            }
        }
        return sessions;
    }

    @Override
    public List<Workshop> getAttendeeWorkshops(Long id) {
        if (attendeeTicketRepository.existsById(id)) {
            AttendeeTicket attendeeTicket = attendeeTicketRepository.findById(id).get();
            return attendeeTicket.getWorkshops();
        }else return null;
    }

}

