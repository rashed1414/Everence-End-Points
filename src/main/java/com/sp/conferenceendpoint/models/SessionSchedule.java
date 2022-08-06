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
    @JoinColumn(name="time_slot_id")
    private TimeSlot time_slot;

    private String room;

    public SessionSchedule(){

    }

    public Long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public TimeSlot getTime_slot() {
        return time_slot;
    }

    public void setTime_slot(TimeSlot time_slot) {
        this.time_slot = time_slot;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
