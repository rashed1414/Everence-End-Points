/*
 * FIle Name: PricingCategorey.java
 * Model Class File
 */

package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "pricing_categories")
public class PricingCategorey {

    /*
       Class Name: PricingCategorey
       Description: Model Class to modeling the "discount_codes" table
       Attributes: ==> pricing_category_cod,pricing_category_name,
       pricing_start_date,pricing_end_date
       Methods: Constructor,Getters and Setters
    */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Character pricing_category_code;

    private String pricing_category_name;
    private Date pricing_start_date;
    private Date pricing_end_date;

    @JsonIgnore
    @OneToMany(mappedBy = "pricing_category")
    private List<TicketPrice> prices;

    public PricingCategorey(){

    }



    public String getPricing_category_name() {
        return pricing_category_name;
    }

    public Character getPricing_category_code() {
        return pricing_category_code;
    }

    public void setPricing_category_code(Character pricing_category_code) {
        this.pricing_category_code = pricing_category_code;
    }

    public void setPricing_category_name(String pricing_category_name) {
        this.pricing_category_name = pricing_category_name;
    }

    public Date getPricing_start_date() {
        return pricing_start_date;
    }

    public void setPricing_start_date(Date pricing_start_date) {
        this.pricing_start_date = pricing_start_date;
    }

    public Date getPricing_end_date() {
        return pricing_end_date;
    }

    public void setPricing_end_date(Date pricing_end_date) {
        this.pricing_end_date = pricing_end_date;
    }

    public List<TicketPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<TicketPrice> prices) {
        this.prices = prices;
    }


}
