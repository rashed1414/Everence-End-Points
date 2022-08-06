/*
 * FIle Name: TicketType.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

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
    private String ticket_type_code;

    private String ticket_type_name;
    private String description;
    private Boolean includes_workshop;

    @OneToMany(mappedBy = "ticket_type")
    private List<TicketPrice> prices;

    public TicketType(){

    }
}
