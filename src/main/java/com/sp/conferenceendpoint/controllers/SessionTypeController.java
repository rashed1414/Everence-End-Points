package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.repositories.SessionTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sessionTypes")
@Tag(name = "Session Types", description = "Session Types API")
public class SessionTypeController {

    @Autowired
    private SessionTypeRepository sessionTypeRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Session Types", description = "Get all Session Types", responses = {
            @ApiResponse(responseCode = "200", description = "Session Types found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Session Types not found", content = @Content)
    })
    public ResponseEntity<?> getAllSessionTypes() {
        return new ResponseEntity<>(sessionTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Session By Type",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getAllSessionByType(@PathVariable Long id){
        if (sessionTypeRepository.existsById(id)){
            return new ResponseEntity<>(sessionTypeRepository.findById(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Session Type Not Found",HttpStatus.NOT_FOUND);
        }
    }


}
