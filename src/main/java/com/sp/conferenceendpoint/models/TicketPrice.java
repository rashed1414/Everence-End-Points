/*
 * FIle Name: TicketPrice.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import javax.persistence.*;

@Entity(name="ticket_prices")
public class TicketPrice {

    /*
     Class Name: TicketPrice
     Description: Model Class to modeling the "sessions" table
     Attributes: ==> ticket_price_id: Primary Key
                 ==> ticket_type_code: Foreign Key
                 ==> pricing_category_code: Foreign Key
                 ==> ticket_price: Foreign Key
                 ==>base_price: Integer
     Methods: Constructor,Getters and Setters
  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_price_id;

    @ManyToOne
    @JoinColumn(name="ticket_type_code")
    private TicketType ticket_type;

    @ManyToOne
    @JoinColumn(name ="pricing_category_code")
    private PricingCategorey pricing_category;

    @OneToOne(mappedBy = "ticket_price")
    private AttendeeTicket attendee_ticket;

    private Integer base_price;


    public TicketPrice(){

    }

    public Long getTicket_price_id() {
        return ticket_price_id;
    }

    public void setTicket_price_id(Long ticket_price_id) {
        this.ticket_price_id = ticket_price_id;
    }

    public TicketType getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(TicketType ticket_type) {
        this.ticket_type = ticket_type;
    }

    public PricingCategorey getPricing_category() {
        return pricing_category;
    }

    public void setPricing_category(PricingCategorey pricing_category) {
        this.pricing_category = pricing_category;
    }

    public Integer getBase_price() {
        return base_price;
    }

    public void setBase_price(Integer base_price) {
        this.base_price = base_price;
    }
}
