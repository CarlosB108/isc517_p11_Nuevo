package com.neuronageek.Views;

import com.neuronageek.Services.EventService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AcMined on 10/27/2016.
 */

@Component
@UIScope
@SpringUI
public class EventView extends Event {
    @Autowired
    EventService eventService;

    private com.neuronageek.Entities.Event event_object;

    private Main main;

    public EventView( ){
        super( );
        setSizeUndefined();
        setMargin(true);
        setSpacing(true);


        //https://vaadin.com/forum#!/thread/1925788/1930888
        create_button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        create_button.addClickListener((Button.ClickListener) event -> {
            event_object.setStyleName("blue");
            eventService.save( event_object );
            main.calendarView.calendar.addEvent( event_object );
            Notification.show("Evento Agregado", Notification.Type.HUMANIZED_MESSAGE);


            ((PopupView)getParent()).setVisible(false);
            setParent( null );
        } );

        cancel_button.addClickListener( ( Button.ClickListener ) event -> {
            ((PopupView)getParent()).setVisible(false);
            setParent( null );
        } );

    }

    public void setEvent( com.neuronageek.Entities.Event e ){
        this.event_object = e;
        BeanFieldGroup.bindFieldsUnbuffered( event_object, this );
    }

    public void setMain( Main main ){
        this.main = main;
    }
}
