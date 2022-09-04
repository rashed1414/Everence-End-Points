/*
 * FIle Name: SessionSchedule.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import javax.persistence.*;

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


    @OneToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    private String room;

    public SessionSchedule(){

    }

    public Long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlots) {
        this.timeSlot = timeSlots;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }



    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
