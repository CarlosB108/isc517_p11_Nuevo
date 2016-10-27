package com.neuronageek.Views;


import com.neuronageek.Entities.*;
import com.neuronageek.Services.EventService;
import com.neuronageek.Services.UserService;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

/**
 * Created by AcMined on 10/26/2016.
 */
@Component
@UIScope
@SpringUI
public class CalendarView extends VerticalLayout {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventView eventView;

    @Autowired
    private DataView dataView;

    private Main main;

    Button add_button = new Button( "Add New Event" );
    Button config_button = new Button( "Settings" );
    Button logout_button = new Button( "Logout" );
    Calendar calendar = new Calendar( );

    //https://github.com/vacax/SpringBootVaadin/blob/master/src/main/groovy/edu/pucmm/sbv/vistas/addons/PruebaCalendario.groovy
    public CalendarView( ){
        setSizeFull();
        setHeight("1000px");
        setMargin(true);
        setSpacing(true);


        loadInterface( );
        loadCalendar( );
        loadEvents( );

        set_up_popup_user( );
        set_up_popup_event( );
    }

    public void loadEvents( ){
        try {
            for (com.neuronageek.Entities.Event e : eventService.findAll()) {
                this.calendar.addEvent(e);
            }
        } catch ( Exception e ){
            System.out.println( e.getMessage() );
        }
    }

    public void loadInterface( ){
        HorizontalLayout buttons = new HorizontalLayout();
        HorizontalLayout option_buttons = new HorizontalLayout();
        buttons.setSizeUndefined();
        buttons.setSpacing(true);

        logout_button.addClickListener( new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                VaadinService.getCurrentRequest().getWrappedSession().removeAttribute("current_user");
                getUI().getPage().setLocation("/");
            }
        });

        option_buttons.addComponents( add_button, config_button, logout_button );
        buttons.addComponents( option_buttons );

        addComponent(buttons);
        addComponent(calendar);
        setComponentAlignment(buttons, Alignment.TOP_RIGHT);
        setExpandRatio(calendar, 1);
    }

    public void loadCalendar( ){
        calendar.setLocale( Locale.US );
        calendar.setSizeFull( );

        this.setSizeFull( );

        //MOVE EVENTS FROM DATE
        calendar.setHandler(new BasicEventMoveHandler() {
            private java.util.Calendar javaCalendar;

            public void eventMove(CalendarComponentEvents.MoveEvent event) {
                javaCalendar = event.getComponent().getInternalCalendar();
                super.eventMove(event);
            }

            protected void setDates(EditableCalendarEvent event,
                                    Date start, Date end) {
                com.neuronageek.Entities.Event e = ( com.neuronageek.Entities.Event ) event;
                e.setStart(start);
                e.setEnd( end );
                eventService.save( e );
            }
        });

    }

    public void setUpMain( Main main ) {
        this.main = main;
        eventView.setMain( main );
    }

    private void set_up_popup_user( ) {

        config_button.addClickListener( new Button.ClickListener( ) {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                User user = (User) VaadinService.getCurrentRequest().getWrappedSession().getAttribute( "current_user" );
                dataView.setUser( user );

                PopupView popup = new PopupView( "Modificar información", dataView );
                addComponent( popup );
                popup.setVisible(true);
            }
        } );
    }

    private void set_up_popup_event( ) {

        add_button.addClickListener( new Button.ClickListener( ) {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                com.neuronageek.Entities.Event event_object;

                event_object = new com.neuronageek.Entities.Event( );
                event_object.setStart( new Date( ) );
                event_object.setEnd( new Date( ) );
                event_object.setName( "" );

                eventView.setEvent(event_object);
                PopupView popup = new PopupView( "Crear evento", eventView );
                addComponent( popup );
                popup.setVisible(true);
            }
        } );
    }
}
