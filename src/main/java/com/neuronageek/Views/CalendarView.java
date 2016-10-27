package com.neuronageek.Views;


import com.neuronageek.Entities.User;
import com.neuronageek.Services.EventService;
import com.neuronageek.Services.UserService;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    //private EventView eventView;
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
        loadContent( );
        loadCalendar( );

        set_up_popup_user( );
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

    public void loadContent( ) {
    }

    public void loadCalendar( ){
        calendar.setLocale( Locale.US );
        calendar.setSizeFull( );

        this.setSizeFull( );

        BasicEventProvider provider = new BasicEventProvider( );
    }

    public void setUpMain( Main main ) {
        this.main = main;
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
        });
    }

    private void openModalView( String title, FormLayout form ) {
        Window modalView = new Window( title );
        modalView.center( );
        modalView.setResizable( false );
        modalView.setModal( true );
        modalView.setClosable( true );
        modalView.setDraggable( false );
        modalView.setContent( form );

        main.addWindow( modalView );
    }
}
