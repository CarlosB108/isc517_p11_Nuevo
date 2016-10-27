package com.neuronageek.Entities;

import com.neuronageek.Services.EventService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import com.vaadin.ui.components.calendar.event.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AcMined on 10/27/2016.
 */
@SpringUI
public class EventProvider extends BasicEventProvider {

    @Autowired
    EventService eventService;

    List<CalendarEvent> events;

    public EventProvider() {
        events = new ArrayList<>();
    }

    @Override
    public void addEvent(CalendarEvent event) {
        super.addEvent(event);
        Event e = (Event) event;
        eventService.save(e);
    }

    @Override
    public List<CalendarEvent> getEvents(Date begin, Date end ) {
        events = new ArrayList<>( eventService.findInterval( begin, end ) );
        return events;
    }
}
