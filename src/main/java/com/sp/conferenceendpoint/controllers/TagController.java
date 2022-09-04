package com.sp.conferenceendpoint.controllers;

import com.sp.conferenceendpoint.models.SessionTag;
import com.sp.conferenceendpoint.repositories.TagRepository;
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
@RequestMapping("api/v1/tags")
@Tag(name = "Tag", description = "Tag API")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Tags",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getAllTags() {
        return new ResponseEntity<>(tagRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Tag by Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getTagById(@PathVariable Long id) {
        if (tagRepository.existsById(id)) {
            return new ResponseEntity<>(tagRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tag not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/sessions")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Sessions by Tag Id",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> getSessionsByTagId(@PathVariable Long id) {
        if (tagRepository.existsById(id)) {
            return new ResponseEntity<>(tagRepository.findById(id).get().getSessions(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tag not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Tag",responses = {
            @ApiResponse(responseCode = "201", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> createTag(@RequestBody SessionTag tag) {
        return new ResponseEntity<>(tagRepository.saveAndFlush(tag), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete Tag",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> deleteTag(@PathVariable Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return new ResponseEntity<>("Tag deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tag not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Tag",responses = {
            @ApiResponse(responseCode = "200", description = "Successful Response"
                    ,content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody SessionTag tag) {
        SessionTag existingTag = tagRepository.findById(id).get();
        if (tagRepository.existsById(id)) {
            BeanUtils.copyProperties(tag, existingTag, "tag_id");
            return new ResponseEntity<>(tagRepository.saveAndFlush(existingTag), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tag not found", HttpStatus.NOT_FOUND);
        }
    }

}
