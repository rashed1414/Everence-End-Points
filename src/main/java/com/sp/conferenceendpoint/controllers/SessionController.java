package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.Session;
import com.sp.conferenceendpoint.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }
    @PutMapping("/{id}")
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

}
