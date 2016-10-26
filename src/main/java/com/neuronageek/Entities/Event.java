package com.neuronageek.Entities;

import javax.persistence.*;

/**
 * Created by AcMined on 10/25/2016.
 */
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne( fetch=FetchType.LAZY )
    @JoinColumn( name="user_id" )
    private User user;

    public Event( ){
        //
    }
}
