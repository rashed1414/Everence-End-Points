package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.TicketPrice;
import com.sp.conferenceendpoint.repositories.TicketPriceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ticketPrice")
@Tag(name = "Ticket Price", description = "Ticket Price API")
public class TicketPriceController {

    @Autowired
    private TicketPriceRepository ticketPriceRepository;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Ticket Prices",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public ResponseEntity<?> getAllTicketPrices(){
        return new ResponseEntity<>(ticketPriceRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Ticket Price By Id",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public ResponseEntity<?> getTicketPriceById(@PathVariable Long id) {
        if (ticketPriceRepository.existsById(id)) {
            return new ResponseEntity<>(ticketPriceRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket Price Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Ticket Price",responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public ResponseEntity<?> createTicketPrice(@RequestBody TicketPrice ticketPrice) {
        return new ResponseEntity<>(ticketPriceRepository.saveAndFlush(ticketPrice), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Ticket Price",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public ResponseEntity<?> updateTicketPrice(@PathVariable Long id, @RequestBody TicketPrice ticketPrice) {
        if (ticketPriceRepository.existsById(id)) {
            TicketPrice existingTicketPrice = ticketPriceRepository.findById(id).get();
            BeanUtils.copyProperties(ticketPrice, existingTicketPrice, "ticket_price_id");
            return new ResponseEntity<>(ticketPriceRepository.saveAndFlush(ticketPrice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket Price Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete Ticket Price",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public ResponseEntity<?> deleteTicketPrice(@PathVariable Long id) {
        if (ticketPriceRepository.existsById(id)) {
            ticketPriceRepository.deleteById(id);
            return new ResponseEntity<>("Ticket Price Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket Price Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Ticket Price",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public ResponseEntity<?> patchTicketPrice(@PathVariable Long id, @RequestBody TicketPrice ticketPrice) {
        if (ticketPriceRepository.existsById(id)) {
            TicketPrice existingTicketPrice = ticketPriceRepository.findById(id).get();
            if (ticketPrice.getBase_price() != null) {
                existingTicketPrice.setBase_price(ticketPrice.getBase_price());
            }
            if (ticketPrice.getPricing_category() != null) {
                existingTicketPrice.setPricing_category(ticketPrice.getPricing_category());
            }
            if (ticketPrice.getTicket_type() != null) {
                existingTicketPrice.setTicket_type(ticketPrice.getTicket_type());
            }
            BeanUtils.copyProperties(ticketPrice, existingTicketPrice, "ticket_price_id");
            return new ResponseEntity<>(ticketPriceRepository.saveAndFlush(ticketPrice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ticket Price Not Found", HttpStatus.NOT_FOUND);
        }
    }



}
