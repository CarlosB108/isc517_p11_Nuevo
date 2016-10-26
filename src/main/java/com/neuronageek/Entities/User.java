package com.neuronageek.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AcMined on 10/25/2016.
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @Column
    String email;

    @Column
    String password;

    @Column
    String names;

    @Column
    String last_names;

    @OneToMany( mappedBy = "user" )
    List< Event > events = new ArrayList<Event>();

    public User( ){
        //
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLast_names() {
        return last_names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
