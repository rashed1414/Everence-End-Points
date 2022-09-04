package com.sp.conferenceendpoint.controllers;


import com.sp.conferenceendpoint.models.PricingCategorey;
import com.sp.conferenceendpoint.repositories.PricingCategoreyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@RequestMapping("api/v1/pricingcategory")
@Tag(name = "Pricing Category", description = "Pricing Category API")
public class PricingCategoreyController {
    @Autowired
    private PricingCategoreyRepository pricingCategoreyRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Pricing Categories",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getAllPricingCategorey() {
        return new ResponseEntity<>(pricingCategoreyRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Pricing Category By Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getPricingCategoreyById(@PathVariable("id") char id) {
        return new ResponseEntity<>(pricingCategoreyRepository.findById(id), HttpStatus.OK);
    }



    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Pricing Category",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> updatePricingCategorey(@PathVariable Character id, @RequestBody PricingCategorey pricingCategorey) {
    if(pricingCategoreyRepository.existsById(id)){
        Field[] fields = pricingCategorey.getClass().getDeclaredFields();
        for(Field field:fields){
            if (field == null) {
                return new ResponseEntity<>("Field is null", HttpStatus.BAD_REQUEST);
            }
        }
        PricingCategorey existingPricingCategorey = pricingCategoreyRepository.findById(id).get();
        BeanUtils.copyProperties(pricingCategorey, existingPricingCategorey, "pricing_category_id");
        return new ResponseEntity<>(pricingCategoreyRepository.saveAndFlush(existingPricingCategorey), HttpStatus.OK);
    }else return new ResponseEntity<>("Pricing Category Not Found", HttpStatus.NOT_FOUND);
    }

}
