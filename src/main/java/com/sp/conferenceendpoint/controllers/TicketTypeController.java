package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.TicketType;
import com.sp.conferenceendpoint.repositories.TicketTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketTypes")
@Tag(name = "Ticket Types", description = "Ticket Types API")
public class TicketTypeController {

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all ticket types", description = "Get all ticket types", tags = {"Ticket Types"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ticket types found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TicketType.class))),
                    @ApiResponse(responseCode = "404", description = "Ticket types not found",
                            content = @Content)
            })
    public ResponseEntity<?> getTicketTypes() {
        return new ResponseEntity<>(ticketTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get ticket type by id", description = "Get ticket type by id", tags = {"Ticket Types"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ticket type found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TicketType.class))),
                    @ApiResponse(responseCode = "404", description = "Ticket type not found",
                            content = @Content)
            })
    public ResponseEntity<?> getTicketTypeById(@PathVariable Character id) {
        if (ticketTypeRepository.existsById(id)) {
            return new ResponseEntity<>(ticketTypeRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
