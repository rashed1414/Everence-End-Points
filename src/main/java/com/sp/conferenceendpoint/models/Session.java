/*
* FIle Name: Session.java
* Model Class File
*/
package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name="sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Session {
    /*
       Class Name: Session
       Description: Model Class to modeling the "sessions" table
       Attributes: ==> session_id(Long), session_name(String),
       session_description(String), session_length(Integer), and speakers(List<Speakers>)
       Methods: Constructor,Getters and Setters
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;

    private String session_name;
    private String Session_description;
    private Integer session_length;


    @OneToOne(mappedBy = "session")
    @JsonIgnore
    private SessionSchedule session_schedule;


    @ManyToMany
    @JoinTable(name="session_tags",
                joinColumns=@JoinColumn(name = "session_id"),
                inverseJoinColumns=@JoinColumn(name="tag_id"))
    private List<Tag> tags;



    @ManyToMany
    @JoinTable(
            name="session_speakers",
            joinColumns=@JoinColumn(name="session_id"),
            inverseJoinColumns = @JoinColumn(name="speaker_id")
    )
    private List<Speaker> speakers;

    @ManyToOne
    @JoinColumn(name = "session_type_id")
    private SessionType session_type;



    public Session(){

    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return Session_description;
    }

    public void setSession_description(String session_description) {
        Session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public SessionSchedule getSession_schedule() {
        return session_schedule;
    }

    public void setSession_schedule(SessionSchedule session_schedule) {
        this.session_schedule = session_schedule;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
