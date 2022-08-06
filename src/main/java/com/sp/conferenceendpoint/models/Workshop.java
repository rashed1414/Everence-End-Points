/*
 * FIle Name: Workshop.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "workshop")
public class Workshop {

    /*
     Class Name: Workshop
     Description: Model Class to modeling the table
     Attributes: ==> workshop_id: Primary Key
                 ==> workshop_name: String
                 ==> description: String
                 ==> requirements: String
                 ==> room: String
                 ==> capacity: Integer
     Methods: Constructor,Getters and Setters
  */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workshop_id;

    private String workshop_name;
    private String description;
    private String requirements;
    private String room;
    private Integer capacity;

    @ManyToMany
    @JoinTable(name = "workshop_speakers",
            joinColumns = @JoinColumn(name = "workshop_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    @ManyToMany
    @JoinTable(name = "workshop_registratins",
            joinColumns = @JoinColumn(name = "workshop_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_ticket__id"))
    private List<AttendeeTicket> attendee_tickets;

    public Workshop(){

    }

    public Long getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(Long workshop_id) {
        this.workshop_id = workshop_id;
    }

    public String getWorkshop_name() {
        return workshop_name;
    }

    public void setWorkshop_name(String workshop_name) {
        this.workshop_name = workshop_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<AttendeeTicket> getAttendee_tickets() {
        return attendee_tickets;
    }

    public void setAttendee_tickets(List<AttendeeTicket> attendee_tickets) {
        this.attendee_tickets = attendee_tickets;
    }
}
