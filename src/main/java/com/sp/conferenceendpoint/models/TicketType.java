/*
 * FIle Name: TicketType.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="ticket_types")
public class TicketType {
    /*
     Class Name: TicketType
     Description: Model Class to modeling the table
     Attributes: ==> ticket_type_code: Primary Key
                 ==> description: String
                 ==> includes_workshops: Boolean
                 ==> ticket_type: Foreign Key
     Methods: Constructor,Getters and Setters
  */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Character ticket_type_code;

    private String ticket_type_name;
    private String description;
    private Boolean includes_workshop;

    @JsonIgnore

    @OneToMany(mappedBy = "ticket_type")
    private List<TicketPrice> prices;

    public TicketType(){

    }

    public Character getTicket_type_code() {
        return ticket_type_code;
    }

    public void setTicket_type_code(Character ticket_type_code) {
        this.ticket_type_code = ticket_type_code;
    }

    public String getTicket_type_name() {
        return ticket_type_name;
    }

    public void setTicket_type_name(String ticket_type_name) {
        this.ticket_type_name = ticket_type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIncludes_workshop() {
        return includes_workshop;
    }

    public void setIncludes_workshop(Boolean includes_workshop) {
        this.includes_workshop = includes_workshop;
    }

    public List<TicketPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<TicketPrice> prices) {
        this.prices = prices;
    }
}
