package com.sp.conferenceendpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EverenceBase {

    @GetMapping("/Wellcom_to_Everence")
    public String getHello() {
        return "Wellcom to Everence Get API DOCS By Visiting : ";
    }
}
