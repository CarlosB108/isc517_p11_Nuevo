package com.neuronageek.Services;

import com.neuronageek.Entities.Event;
import com.neuronageek.Repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AcMined on 10/26/2016.
 */
@Service
@Transactional
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public List< Event > listAll() {
        return ( List<  Event  > ) eventRepository.findAll( );
    }

    public void save(Event event) {
        eventRepository.save( event );
    }
}