package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.Attendee;
import com.sp.conferenceendpoint.repositories.AttendeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("api/v1/attendees")
@Tag(name = "AttendeeAPIs")
public class AttendeeController {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Attendees",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Attendee ById",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "USer Not Found")}
    )
    public Attendee getAttendee(@PathVariable Long id){
        return attendeeRepository.findById(id).get();
    }

    @RequestMapping(value = "/{id}/AttendeeTickets", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Attendee Tickets",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "USer Not Found")}
    )
    public ResponseEntity<?> getAttendeeTickets(@PathVariable Long id){
        if(attendeeRepository.existsById(id)) {
            return ResponseEntity.ok(attendeeRepository.findById(id).get().getAttendee_tickets());
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete Attendee ById",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "USer Not Found")}
    )
    public void deleteAttendee(@PathVariable Long id){
        attendeeRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Attendee",responses = {
            @ApiResponse(responseCode = "202", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "USer Not Found")}
    )
    public Attendee createAttendee(@RequestBody Attendee attendee){
        return attendeeRepository.saveAndFlush(attendee);
    }

    @RequestMapping(value="/{id}/",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update Attendee",responses = {
            @ApiResponse(responseCode = "202", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "USer Not Found")}
    )
    public ResponseEntity<?> updateAttendee(@PathVariable Long id, @RequestBody Attendee attendee){

        if(!attendeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(attendee.getAttendee_tickets()!=null && attendee.getCompany()!=null &&
                 attendee.getEmail()!=null && attendee.getFirst_name()!=null &&
                 attendee.getLast_name()!=null &&
                 attendee.getTitle()!=null && attendee.getPhone_number()!=null){
            Attendee existingAttendee = attendeeRepository.findById(id).get();

            BeanUtils.copyProperties(attendee, existingAttendee, "attendee_id");
            attendeeRepository.saveAndFlush(existingAttendee);

            return ResponseEntity.ok("Updated Successfully" + existingAttendee);
        }else {
            return ResponseEntity.badRequest().body("Insert All Fields with proper value types");
        }
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Patch Attendee",responses = {
            @ApiResponse(responseCode = "202", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "USer Not Found")}
    )
    public ResponseEntity<?> patchAttendee(@PathVariable Long id,@RequestBody Attendee attendee) {
        if (!attendeeRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            Attendee existingAttendee = attendeeRepository.findById(id).get();
            if(attendee.getAttendee_tickets()!=null){
                existingAttendee.setAttendee_tickets(attendee.getAttendee_tickets());
            }
            if(attendee.getCompany()!=null){
                existingAttendee.setCompany(attendee.getCompany());
            }
            if(attendee.getEmail()!=null){
                existingAttendee.setEmail(attendee.getEmail());
            }
            if(attendee.getFirst_name()!=null){
                existingAttendee.setFirst_name(attendee.getFirst_name());
            }
            if(attendee.getLast_name()!=null){
                existingAttendee.setLast_name(attendee.getLast_name());
            }
            if(attendee.getTitle()!=null){
                existingAttendee.setTitle(attendee.getTitle());
            }
            if(attendee.getPhone_number()!=null){
                existingAttendee.setPhone_number(attendee.getPhone_number());
            }
            attendeeRepository.saveAndFlush(existingAttendee);
            return ResponseEntity.ok("Updated Successfully" + existingAttendee);
        }
    }


}



