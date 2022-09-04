package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.DiscountCode;
import com.sp.conferenceendpoint.repositories.DiscountCodeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@RequestMapping("api/v1/discountCodes")
@Tag(name = "DiscountCodeAPIs")
public class DiscountCodeController {
    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Discount Codes",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getDiscountCodes(){
        return new ResponseEntity<>(discountCodeRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/DiscountCode",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete Discount Codes",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> deleteDiscountCode(@PathVariable Long id){
        if (discountCodeRepository.existsById(id)){
            discountCodeRepository.deleteById(id);
            return ResponseEntity.ok("Discount Code Deleted");
        }else return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Discount Codes",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public DiscountCode createDiscountCode(@RequestBody DiscountCode discountCode){
        discountCode.setDiscount_code(RandomStringUtils.randomAlphanumeric(10));
        return discountCodeRepository.saveAndFlush(discountCode);

    }

    @RequestMapping(value = "/{id}/DiscountCode",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update Discount Codes",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> updateDiscountCode(@PathVariable Long id,@RequestBody DiscountCode discountCode){
        if (discountCodeRepository.existsById(id)){
            Field[] attributes =  discountCode.getClass().getDeclaredFields();

            DiscountCode existingDiscountCode = discountCodeRepository.findById(id).get();
            for(Field att:attributes){
                if(att == null){
                    return ResponseEntity.badRequest().body("Invalid Request-- Fill all fields");
                }
            }
            BeanUtils.copyProperties(discountCode,existingDiscountCode, "discount_code_id");
            discountCodeRepository.saveAndFlush(existingDiscountCode);
            return ResponseEntity.ok("Updated Successfully");

        } else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/DiscountCode",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Discount Code Users",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getDiscountCodeUsers(@PathVariable Long id){
        if (discountCodeRepository.existsById(id)){
            return ResponseEntity.ok(discountCodeRepository.findById(id).get().getAttendee_ticket().size());
        }else return ResponseEntity.notFound().build();
    }


}
