/*
 * FIle Name: DiscountCode.java
 * Model Class File
 */

package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="discount_codes")
public class DiscountCode {

    /*
       Class Name: DiscountCode
       Description: Model Class to modeling the "discount_codes" table
       Attributes: ==> discount_code_id(Long), discount_code(String), discount_name(String),
       discount_type(String), and discount_amount(Integer)
       Methods: Constructor,Getters and Setters
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discount_code_id;

    private String discount_code;
    private String discount_name;
    private String discount_type;
    private Double discount_amount;

    @JsonIgnore
    @OneToMany(mappedBy = "discount_code")
    private List<AttendeeTicket> attendee_ticket;

    public DiscountCode(){

    }

    public Long getDiscount_code_id() {
        return discount_code_id;
    }

    public void setDiscount_code_id(Long discount_code_id) {
        this.discount_code_id = discount_code_id;
    }

    public String getDiscount_code() {
        return discount_code;
    }

    public void setDiscount_code(String discount_code) {
        this.discount_code = discount_code;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public Double getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(Double discount_amount) {
        this.discount_amount = discount_amount;
    }

    public List<AttendeeTicket> getAttendee_ticket() {
        return attendee_ticket;
    }

    public void setAttendee_ticket(List<AttendeeTicket> attendee_ticket) {
        this.attendee_ticket = attendee_ticket;
    }
}
