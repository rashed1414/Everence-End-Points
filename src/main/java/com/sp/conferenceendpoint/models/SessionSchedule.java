/*
 * FIle Name: SessionSchedule.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "session_schedule")
public class SessionSchedule {

    /*
       Class Name: SessionSchedule
       Description: Model Class to modeling the "sessions" table
       Attributes: ==> scheduleId: Primary Key
                   ==> sessionId: Foreign Key
                   ==>time_slot_id: Foreign Key
                   ==> room
       Methods: Constructor,Getters and Setters
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @OneToOne
    @JoinColumn(name = "session_id")
    private Session session;


    @OneToMany(mappedBy = "schedule")
    private List<TimeSlot> timeSlot;

    private String room;

    public SessionSchedule(){

    }

    public Long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }




    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<TimeSlot> getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(List<TimeSlot> timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
