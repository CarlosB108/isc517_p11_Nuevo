package com.neuronageek.Entities;

import com.vaadin.ui.components.calendar.event.CalendarEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AcMined on 10/25/2016.
 */
@Entity
@Table(name="events")
public class Event implements CalendarEvent, EditableCalendarEvent, CalendarEvent.EventChangeNotifier, Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;

    @ManyToOne( fetch=FetchType.EAGER )
    @JoinColumn( name="user_id" )
    private User user;

    @Column
    String name;

    @Column
    @DateTimeFormat
    Date start;

    @Column
    @DateTimeFormat
    Date end;

    @Column
    boolean sended;

    @Column
    private String caption;

    @Column
    private String styleName;

    @Column
    private boolean isAllDay;

    @Column
    String description;

    private transient List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();

    public Event( ){
        sended = false;
        caption = "";
        description = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    @Override
    public void addEventChangeListener(EventChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeEventChangeListener(EventChangeListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setCaption(String caption) {
        this.caption = caption;
        fireEventChange();
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
        fireEventChange();
    }

    @Override
    public void setEnd(Date end) {
        this.end = end;
        fireEventChange();
    }

    @Override
    public void setStart(Date start) {
        this.start = start;
    }

    @Override
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @Override
    public void setAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
        fireEventChange();
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getStyleName() {
        return styleName;
    }

    @Override
    public boolean isAllDay() {
        return isAllDay;
    }

    protected void fireEventChange() {
        EventChangeEvent event = new EventChangeEvent(this);

        for (EventChangeListener listener : listeners) {
            listener.eventChange(event);
        }
    }
}
