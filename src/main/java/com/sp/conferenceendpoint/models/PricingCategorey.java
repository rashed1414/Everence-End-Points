package com.sp.conferenceendpoint.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "pricing_categories")
public class PricingCategorey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pricing_category_code;

    private String pricing_category_name;
    // TODO complete the class Entities
}
