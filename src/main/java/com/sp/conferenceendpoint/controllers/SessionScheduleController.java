package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.SessionSchedule;
import com.sp.conferenceendpoint.repositories.SessionScheduleRepository;
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
@RequestMapping("/session_schedule")
@Tag(name = "Session Schedule", description = "Session Schedule API")
public class SessionScheduleController {
    @Autowired
    private SessionScheduleRepository sessionScheduleRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Session Schedule",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getAllSessionSchedule(){
        return new ResponseEntity<>(sessionScheduleRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/session_schedule",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Session Schedule By Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getSessionScheduleById(@PathVariable Long id){
        if (sessionScheduleRepository.existsById(id)){
            return new ResponseEntity<>(sessionScheduleRepository.findById(id),HttpStatus.OK);
        }else return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Session Schedule",responses = {
            @ApiResponse(responseCode = "202", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> createSessionSchedule(@RequestBody SessionSchedule sessionSchedule){
        return new ResponseEntity<>(sessionScheduleRepository.saveAndFlush(sessionSchedule),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/session_schedule",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete Session Schedule By Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> deleteSessionScheduleById(@PathVariable Long id){
        if (sessionScheduleRepository.existsById(id)){
            sessionScheduleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/session_schedule",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Session Schedule By Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> updateSessionScheduleById(@PathVariable Long id,@RequestBody SessionSchedule sessionSchedule) {
        if (sessionScheduleRepository.existsById(id)) {
            SessionSchedule existingSessionSchedule = sessionScheduleRepository.findById(id).get();

            BeanUtils.copyProperties(sessionSchedule, existingSessionSchedule, "schedule_id");
            return new ResponseEntity<>(sessionScheduleRepository.saveAndFlush(existingSessionSchedule),HttpStatus.OK);
        } else return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/session_schedule",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Session Schedule By Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> patchSessionScheduleById(@PathVariable Long id,@RequestBody SessionSchedule sessionSchedule) {
        if (sessionScheduleRepository.existsById(id)) {
            SessionSchedule existingSessionSchedule = sessionScheduleRepository.findById(id).get();
            if (sessionSchedule.getSchedule_id() != null) {
                existingSessionSchedule.setSchedule_id(sessionSchedule.getSchedule_id());
            }
            if (sessionSchedule.getSession() != null) {
                existingSessionSchedule.setSession(sessionSchedule.getSession());
            }
            if (sessionSchedule.getTimeSlot() != null) {
                existingSessionSchedule.setTimeSlot(sessionSchedule.getTimeSlot());
            }
            if (sessionSchedule.getRoom() != null) {
                existingSessionSchedule.setRoom(sessionSchedule.getRoom());
            }

            return new ResponseEntity<>(sessionScheduleRepository.saveAndFlush(existingSessionSchedule),HttpStatus.OK);
        } else return ResponseEntity.notFound().build();
    }


}
