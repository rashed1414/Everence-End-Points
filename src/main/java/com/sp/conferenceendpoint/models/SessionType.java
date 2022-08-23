package com.sp.conferenceendpoint.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "session_type")
public class SessionType {

    @Id
    private Integer session_type_id;
    private String type;
/*
    @OneToMany(mappedBy = "session_type")
    private List<Session> sessions;
*/
    public SessionType(){}
}
