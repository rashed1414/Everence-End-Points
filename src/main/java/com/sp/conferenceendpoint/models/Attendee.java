/*
 * FIle Name: Attendee.java
 * Model Class File
 */

package com.sp.conferenceendpoint.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "attendees")
public class Attendee {

    /*
       Class Name: Attendee
       Description: Model Class to modeling the "attendees" table
       Attributes: ==> attendee_id(Long), first_name(String), last_name(String),
       title(String), company(String),email(String), and phone_number(String)
       Methods: Constructor,Getters and Setters
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendee_id;

    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String email;
    private String phone_number;

    @OneToMany(mappedBy = "attendee")
    private List<AttendeeTicket> attendee_tickets;



    public Attendee(){

    }

    public Long getAttendee_id() {
        return attendee_id;
    }

    public void setAttendee_id(Long attendee_id) {
        this.attendee_id = attendee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<AttendeeTicket> getAttendee_tickets() {
        return attendee_tickets;
    }

    public void setAttendee_tickets(List<AttendeeTicket> attendee_tickets) {
        this.attendee_tickets = attendee_tickets;
    }

}
