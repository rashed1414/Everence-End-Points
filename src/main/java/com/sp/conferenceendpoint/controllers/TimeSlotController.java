package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.TimeSlot;
import com.sp.conferenceendpoint.repositories.TimeSlotRepository;
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

@RestController
@RequestMapping("/api/v1/timeSlots")
@Tag(name = "Time Slots", description = "Time Slots API")
public class TimeSlotController {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Time Slots", description = "Get all Time Slots", tags = {"Time Slots"},
    responses = {
            @ApiResponse(responseCode = "200", description = "Time Slots found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))),
            @ApiResponse(responseCode = "404", description = "Time Slots not found",
                    content = @Content)
    })
    public ResponseEntity<?> getAllTimeSlots(){
        return new ResponseEntity<>(timeSlotRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Time Slot by Id", description = "Get Time Slot by Id", tags = {"Time Slots"},
    responses = {
            @ApiResponse(responseCode = "200", description = "Time Slot found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))),
            @ApiResponse(responseCode = "404", description = "Time Slot not found",
                    content = @Content)
    })
    public ResponseEntity<?> getTimeSlotById(@PathVariable Long id){
        if (timeSlotRepository.existsById(id)){
            return new ResponseEntity<>(timeSlotRepository.findById(id), HttpStatus.OK);
    }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Time Slot", description = "Create Time Slot", tags = {"Time Slots"},
    responses = {
            @ApiResponse(responseCode = "201", description = "Time Slot created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<?> createTimeSlot(@RequestBody TimeSlot timeSlot){
        return new ResponseEntity<>(timeSlotRepository.save(timeSlot), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Time Slot", description = "Update Time Slot", tags = {"Time Slots"},
    responses = {
            @ApiResponse(responseCode = "200", description = "Time Slot updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<?> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlot timeSlot){
        if (timeSlotRepository.existsById(id)){
            TimeSlot existingtimeSlot = timeSlotRepository.findById(id).get();
            BeanUtils.copyProperties(timeSlot, existingtimeSlot, "time_slot_id");
            return new ResponseEntity<>(timeSlotRepository.saveAndFlush(existingtimeSlot), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete Time Slot", description = "Delete Time Slot", tags = {"Time Slots"},
    responses = {
            @ApiResponse(responseCode = "200", description = "Time Slot deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))),
            @ApiResponse(responseCode = "404", description = "Time Slot not found",
                    content = @Content)
    })
    public ResponseEntity<?> deleteTimeSlot(@PathVariable Long id){
        if (timeSlotRepository.existsById(id)){
            timeSlotRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Time Slot", description = "Update Time Slot", tags = {"Time Slots"},
    responses = {
            @ApiResponse(responseCode = "200", description = "Time Slot updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    public ResponseEntity<?> patchTimeSlot(@PathVariable Long id, @RequestBody TimeSlot timeSlot){
        if (timeSlotRepository.existsById(id)){
            TimeSlot existingtimeSlot = timeSlotRepository.findById(id).get();
            if (timeSlot.getTime_slot_date() != null){
                existingtimeSlot.setTime_slot_date(timeSlot.getTime_slot_date());
            }
            if (timeSlot.getIs_keynote_time_slot() != null){
                existingtimeSlot.setIs_keynote_time_slot(timeSlot.getIs_keynote_time_slot());
            }
            if (timeSlot.getEnd_time() != null){
                existingtimeSlot.setEnd_time(timeSlot.getEnd_time());
            }
            if (timeSlot.getStart_time() != null){
                existingtimeSlot.setStart_time(timeSlot.getStart_time());
            }
            if (timeSlot.getSchedule() != null){
                existingtimeSlot.setSchedule(timeSlot.getSchedule());
            }
            return new ResponseEntity<>(timeSlotRepository.saveAndFlush(existingtimeSlot), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
