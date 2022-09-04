package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.Session;
import com.sp.conferenceendpoint.models.Speaker;
import com.sp.conferenceendpoint.models.SessionTag;
import com.sp.conferenceendpoint.repositories.SessionRepository;
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
@RequestMapping("api/v1/sessions")
@io.swagger.v3.oas.annotations.tags.Tag(name = "SessionAPIs")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Session",responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json"),
                    description = "Successful Response"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get Session ById",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "USer Not Found")}
    )
    public Session get(@PathVariable Long id){
        return sessionRepository.findById(id).get();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/speakers", method = RequestMethod.GET)
    @Operation(summary = "Get Session Speakers",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "Speakers Not Found")}
    )
    public List<Speaker> getSpeakers(@PathVariable Long id){
        return sessionRepository.findById(id).get().getSpeakers();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/tags", method = RequestMethod.GET)
    @Operation(summary = "Get Session Tags",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "Tags Not Found")}
    )
    public List<SessionTag> getTags(@PathVariable Long id){
        return sessionRepository.findById(id).get().getTags();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Session",responses = {
            @ApiResponse(responseCode = "201", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "Session Already Exists")}
    )
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Session",responses = {
            @ApiResponse(responseCode = "204", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "Session Not Found")}
    )
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Session",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "Session Not Found")}
    )
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Session session){
        if ((session.getSession_name()!=null && session.getSession_description() !=null
                && session.getSession_length() !=null && session.getSession_schedule()!=null
                && session.getSpeakers() !=null && session.getTags()!=null)) {
            Session existingSession = sessionRepository.findById(id).get();
            BeanUtils.copyProperties(session, existingSession, "session_id");
            sessionRepository.saveAndFlush(existingSession);

            return ResponseEntity.ok("resource saved");

        }else return ResponseEntity.ok("insert all session data");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Patch Session",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "409", description = "Session Not Found")}
    )
    public ResponseEntity<String> patch(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepository.findById(id).get();
            if (session.getTags() != null) {
                existingSession.setTags(session.getTags());
                ResponseEntity.ok("tags updated");
            }
            if (session.getSpeakers() != null) {
                existingSession.setSpeakers(session.getSpeakers());
                ResponseEntity.ok("speakers updated");
            }
            if (session.getSession_name() != null) {
                existingSession.setSession_name(session.getSession_name());
                ResponseEntity.ok("session name updated");
            }
            if (session.getSession_description() != null) {
                existingSession.setSession_description(session.getSession_description());
                ResponseEntity.ok("session description updated");
            }
            if (session.getSession_length() != null) {
                existingSession.setSession_length(session.getSession_length());
                ResponseEntity.ok("session length updated");
            }
            if (session.getSession_schedule() != null) {
                existingSession.setSession_schedule(session.getSession_schedule());
                ResponseEntity.ok("session schedule updated");
            }
        sessionRepository.saveAndFlush(existingSession);
        return ResponseEntity.ok("data Patched");
    }

}
