package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.AttendeeTicket;
import com.sp.conferenceendpoint.repositories.AttendeeTicketRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Dictionary;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendeesTickets")
@Tag(name = "AttendeeTicketsAPIs")
public class AttendeeTicketsController {
    public AttendeeTicketRepository attendeeTicketRepository;

    @RequestMapping(value = "/{id}/AttendeeTickets", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Attendee Tickets",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "USer Not Found")}
    )
    public ResponseEntity<?> getAttendeeTickets(@PathVariable Long id){
        if(attendeeTicketRepository.existsById(id)) {
            return new ResponseEntity<>(attendeeTicketRepository.findById(id).get(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping( method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Attendee Tickets",responses = {
            @ApiResponse(responseCode = "201", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "USer Not Found")}
    )
    public ResponseEntity<?> createAttendeeTickets(@RequestBody AttendeeTicket attendeeTicket){
        if(attendeeTicket.getClass() == AttendeeTicket.class) {
            return new ResponseEntity<>(attendeeTicketRepository.saveAndFlush(attendeeTicket),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/{id}/AttendeeTickets", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Attendee Tickets",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "USer Not Found")}
    )
    public ResponseEntity<?> updateAttendeeTickets(@PathVariable Long id, @RequestBody AttendeeTicket attendeeTicket){
        Field[] attributes =  attendeeTicket.getClass().getDeclaredFields();

        if(attendeeTicketRepository.existsById(id)) {
            AttendeeTicket existingAttendeeTicket = attendeeTicketRepository.findById(id).get();
            for(Field att:attributes){
                if(att == null){
                    return ResponseEntity.badRequest().body("Invalid Request-- Fill all fields");
                }
            }
            BeanUtils.copyProperties(attendeeTicket, existingAttendeeTicket, "attendeeTicket_id");


            return new ResponseEntity<>(attendeeTicketRepository.saveAndFlush(attendeeTicket),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
