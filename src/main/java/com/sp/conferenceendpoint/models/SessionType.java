package com.sp.conferenceendpoint.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "session_type")
public class SessionType {

    @Id
    private Long session_type_id;
    private String type;

    @OneToMany(mappedBy = "session_type")
    @JsonIgnore
    private List<Session> sessions;

    public SessionType(){}

    public Long getSession_type_id() {
        return session_type_id;
    }

    public void setSession_type_id(Long session_type_id) {
        this.session_type_id = session_type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}

