package com.sp.conferenceendpoint.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "attendee_tickets")
public class AttendeeTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendee_tickets_id;

    // TODO Complete AttendeeTickets Class Entities
    public AttendeeTicket(){

    }
}
