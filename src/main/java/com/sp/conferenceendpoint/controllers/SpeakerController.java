package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.Session;
import com.sp.conferenceendpoint.models.Speaker;
import com.sp.conferenceendpoint.models.Workshop;
import com.sp.conferenceendpoint.repositories.SpeakerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Speakers",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @Operation(summary = "Get Speaker ById",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public Speaker get(@PathVariable Long id){
        return speakerRepository.findById(id).get();
    }

    @RequestMapping(value="/{id}/sessions",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Speaker Sessions",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public List<Session> getSessions(@PathVariable Long id){
        return speakerRepository.findById(id).get().getSessions();
    }

    @RequestMapping(value="/{id}/workshops",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Speaker Workshops",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public List<Workshop> getWorkshops(@PathVariable Long id){
        return speakerRepository.findById(id).get().getWorkshops();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "Create Speaker",responses = {
            @ApiResponse(responseCode = "201", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public Speaker post(@ModelAttribute final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @Operation(summary = "Delete Speaker",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public void delete(@PathVariable Long id){
        // TODO https://thorben-janssen.com/hibernate-tips-the-best-way-to-remove-entities-from-a-many-to-many-association/
        //https://thorben-janssen.com/association-mappings-bag-list-set/
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update Speaker",responses = {
            @ApiResponse(responseCode = "202", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public Speaker update(@PathVariable Long id,@RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepository.findById(id).get();
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update Speaker",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Speaker Not Found")}
    )
    public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody Speaker speaker){

        if(speakerRepository.findById(id).isPresent()){
            Speaker existingSpeaker = speakerRepository.findById(id).get();

            if (speaker.getFirst_name() != null)
                existingSpeaker.setFirst_name(speaker.getFirst_name());

            if (speaker.getLast_name() != null)
                existingSpeaker.setLast_name(speaker.getLast_name());

            if (speaker.getSpeaker_bio() != null)
                existingSpeaker.setSpeaker_bio(speaker.getSpeaker_bio());

            if (speaker.getCompany() != null)
                existingSpeaker.setCompany(speaker.getCompany());

            if (speaker.getSessions() != null)
                existingSpeaker.setSessions(speaker.getSessions());

            if (speaker.getTitle() != null)
                existingSpeaker.setTitle(speaker.getTitle());

            if (speaker.getWorkshops() != null)
                existingSpeaker.setWorkshops(speaker.getWorkshops());

            speakerRepository.saveAndFlush(existingSpeaker);

            return ResponseEntity.ok("Speaker updated successfully");
        }
        else
            return ResponseEntity.badRequest().body("Speaker not found");
    }
}
