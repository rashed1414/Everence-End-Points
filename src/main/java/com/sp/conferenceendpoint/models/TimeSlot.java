/*
 * FIle Name: TimeSlot.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity(name="time_slot")
public class TimeSlot {

     /*
     Class Name: TimeSlot
     Description: Model Class to modeling the table
     Attributes: ==> time_slot_id: Primary Key
                 ==> time_slot_date: Date
                 ==> start_time: Time
                 ==> end_time: Time
                 ==> is_keynote_time_slot: Boolean
     Methods: Constructor,Getters and Setters
  */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long time_slot_id;

    private Date time_slot_date;
    private Time start_time;
    private Time end_time;
    private Boolean is_keynote_time_slot;

    @OneToOne(mappedBy = "time_slot")
    private SessionSchedule session;
    public TimeSlot(){

    }

    public Long getTime_slot_id() {
        return time_slot_id;
    }

    public void setTime_slot_id(Long time_slot_id) {
        this.time_slot_id = time_slot_id;
    }

    public Date getTime_slot_date() {
        return time_slot_date;
    }

    public void setTime_slot_date(Date time_slot_date) {
        this.time_slot_date = time_slot_date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public Boolean getIs_keynote_time_slot() {
        return is_keynote_time_slot;
    }

    public void setIs_keynote_time_slot(Boolean is_keynote_time_slot) {
        this.is_keynote_time_slot = is_keynote_time_slot;
    }

    public SessionSchedule getSession() {
        return session;
    }

    public void setSession(SessionSchedule session) {
        this.session = session;
    }
}
