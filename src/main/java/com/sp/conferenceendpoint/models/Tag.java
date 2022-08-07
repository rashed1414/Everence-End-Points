/*
 * FIle Name: Tag.java
 * Model Class File
 */
package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tags")
public class Tag {
    /*
      Class Name: SessionSchedule
      Description: Model Class to modeling the "sessions" table
      Attributes: ==> tagId: Primary Key
                  ==> description: String
                  ==> sessions: List of sessions
      Methods: Constructor,Getters and Setters
   */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tag_id;

    private String description;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Session> sessions;


    public Tag(){

    }

    public Long getTag_id() {
        return tag_id;
    }

    public void setTag_id(Long tag_id) {
        this.tag_id = tag_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
