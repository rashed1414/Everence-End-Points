/*
 * FIle Name: AttendeeTicket.java
 * Model Class File
 */

package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "attendee_tickets")
public class AttendeeTicket {

     /*
       Class Name: AttendeeTicket
       Description: Model Class to modeling the "attendees" table
       Attributes: ==> attendee_ticket_id,attendee_id,ticket_price_id,discount_code_id,
       net_price
       Methods: Constructor,Getters and Setters
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendee_ticket_id;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    @NotNull
    private Attendee attendee;

    @OneToOne
    @JoinColumn(name = "ticket_price_id")
    @NotNull
    private TicketPrice ticket_price;


    @ManyToOne
    @JoinColumn(name = "discount_code_id")
    @NotNull
    private DiscountCode discount_code;

    @JsonIgnore
    @ManyToMany(mappedBy = "attendee_tickets")
    private List<Workshop> workshops ;
    @NotNull
    private Integer net_price;



    public AttendeeTicket(){

    }

    public DiscountCode getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(DiscountCode discount_code) {
        this.discount_code = discount_code;
    }

    public Long getAttendee_ticket_id() {
        return attendee_ticket_id;
    }

    public void setAttendee_ticket_id(Long attendee_ticket_id) {
        this.attendee_ticket_id = attendee_ticket_id;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public TicketPrice getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(TicketPrice ticket_price) {
        this.ticket_price = ticket_price;
    }

    public Integer getNet_price() {
        return net_price;
    }

    public void setNet_price(Integer net_price) {
        this.net_price = net_price;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<Workshop> workshops) {
        this.workshops = workshops;
    }
}
