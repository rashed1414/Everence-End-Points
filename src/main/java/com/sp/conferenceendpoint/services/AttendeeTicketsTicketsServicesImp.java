package com.sp.conferenceendpoint.services;

import com.sp.conferenceendpoint.interfaces.AttendeeTicketsService;
import com.sp.conferenceendpoint.models.AttendeeTicket;
import com.sp.conferenceendpoint.models.Session;
import com.sp.conferenceendpoint.models.Speaker;
import com.sp.conferenceendpoint.models.Workshop;
import com.sp.conferenceendpoint.repositories.AttendeeRepository;

import java.util.ArrayList;
import java.util.List;

public class AttendeeTicketsTicketsServicesImp implements AttendeeTicketsService {
    private AttendeeRepository attendeeRepository;


    @Override
    public List<Session> getAttendeeSessions(Long id) {
        List<AttendeeTicket> attendeeTicketList=attendeeRepository.findById(id).get().getAttendee_tickets();
        List<List<Workshop>> workshops=new ArrayList<List<Workshop>>();
        List<List<Speaker>> speakers=new ArrayList<List<Speaker>>();
        List<List<Session>> sessions=new ArrayList<List<Session>>();
        List<Session> result=new ArrayList<Session>();


        for(AttendeeTicket attendeeTicket:attendeeTicketList){
            workshops.add(attendeeTicket.getWorkshops());
        }
        for(List<Workshop> workshopList:workshops){
            for(Workshop workshop:workshopList){
                speakers.add(workshop.getSpeakers());
            }
        }
        for(List<Speaker> speakerList:speakers){
            for(Speaker speaker:speakerList){
                sessions.add(speaker.getSessions());
            }
        }
        for(List<Session> sessionList:sessions){
            result.addAll(sessionList);
        }

        return result;
    }

    @Override
    public List<Workshop> getAttendeeWorkshops(Long id) {
        List<AttendeeTicket> attendeeTicketList=attendeeRepository.findById(id).get().getAttendee_tickets();
        List<List<Workshop>> workshops=new ArrayList<List<Workshop>>();
        List<Workshop> result=new ArrayList<Workshop>();
        for(AttendeeTicket attendeeTicket:attendeeTicketList){
            workshops.add(attendeeTicket.getWorkshops());
        }
        for(List<Workshop> workshopList:workshops){
            result.addAll(workshopList);
        }
        return result;
    }

}

