package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.Workshop;
import com.sp.conferenceendpoint.repositories.WorkshopRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("api/v1/workshops")
@Tag(name = "Workshops", description = "Workshop API")
public class WorkshopController {

    @Autowired
    private WorkshopRepository workshopRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all workshops", description = "Get all workshops", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Workshops found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Workshops not found",
                            content = @Content)
            })
    public ResponseEntity<?> getAllWorkshops(){
        return new ResponseEntity<>(workshopRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get workshop by id", description = "Get workshop by id", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Workshop found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Workshop not found",
                            content = @Content)
            })
    public ResponseEntity<?> getWorkshopById(@PathVariable Long id){
        if (workshopRepository.existsById(id)){
            return new ResponseEntity<>(workshopRepository.findById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/attendees")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get attendees of a workshop", description = "Get attendees of a workshop", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Attendees found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Attendees not found",
                            content = @Content)
            })
    public ResponseEntity<?> getAttendeesOfAWorkshop(@PathVariable Long id){
        if (workshopRepository.existsById(id)){
            return new ResponseEntity<>(workshopRepository.findById(id).get().getAttendee_tickets(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/speakers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get speakers of a workshop", description = "Get speakers of a workshop", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Speakers found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Speakers not found",
                            content = @Content)
            })
    public ResponseEntity<?> getSpeakersOfAWorkshop(@PathVariable Long id){
        if (workshopRepository.existsById(id)){
            return new ResponseEntity<>(workshopRepository.findById(id).get().getSpeakers(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a workshop", description = "Create a workshop", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Workshop created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "400", description = "Workshop not created",
                            content = @Content)
            })
    public ResponseEntity<?> createWorkshop(@RequestBody Workshop workshop){
        return new ResponseEntity<>(workshopRepository.saveAndFlush(workshop), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a workshop", description = "Update a workshop", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Workshop updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Workshop not updated",
                            content = @Content)
            })
    public ResponseEntity<?> updateWorkshop(@PathVariable Long id, @RequestBody Workshop workshop){
        if (workshopRepository.existsById(id)){
            Workshop existingWorkshop = workshopRepository.findById(id).get();
            BeanUtils.copyProperties(workshop, existingWorkshop, "workshop_id");
            return new ResponseEntity<>(workshopRepository.saveAndFlush(workshop), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a workshop", description = "Update a workshop", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Workshop updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Workshop not updated",
                            content = @Content)
            })
    public ResponseEntity<?> patchWorkshop(@PathVariable Long id, @RequestBody Workshop workshop){
        if (workshopRepository.existsById(id)){
            Workshop existingWorkshop = workshopRepository.findById(id).get();
            if (workshop.getWorkshop_name() != null){
                existingWorkshop.setWorkshop_name(workshop.getWorkshop_name());
            }
            if (workshop.getCapacity() != null){
                existingWorkshop.setCapacity(workshop.getCapacity());
            }
            if (workshop.getRoom() != null){
                existingWorkshop.setRoom(workshop.getRoom());
            }
            if(workshop.getDescription() != null){
                existingWorkshop.setDescription(workshop.getDescription());
            }
            if (workshop.getSpeakers() != null){
                existingWorkshop.setSpeakers(workshop.getSpeakers());
            }
            if (workshop.getAttendee_tickets() != null){
                existingWorkshop.setAttendee_tickets(workshop.getAttendee_tickets());
            }
            if (workshop.getRequirements() != null){
                existingWorkshop.setRequirements(workshop.getRequirements());
            }

            return new ResponseEntity<>(workshopRepository.saveAndFlush(workshop), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a workshop", description = "Delete a workshop", tags = {"Workshops"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Workshop deleted",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Workshop.class))),
                    @ApiResponse(responseCode = "404", description = "Workshop not deleted",
                            content = @Content)
            })
    public ResponseEntity<?> deleteWorkshop(@PathVariable Long id){
        if (workshopRepository.existsById(id)){
            workshopRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
