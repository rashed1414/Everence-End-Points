package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.Speaker;
import com.sp.conferenceendpoint.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker post(@ModelAttribute final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //TODO check for children records before deleting
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        //TODO add validation for received all item
        Speaker existingSpeaker=speakerRepository.findById(id).get();
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return existingSpeaker;
    }
}
