package com.neuronageek.Views;

import com.neuronageek.Entities.User;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AcMined on 10/26/2016.
 */

@SpringUI(path = "/")
@Theme("valo")
public class Main extends UI {

    @Autowired
    LoginView loginView;

    @Autowired
    CalendarView calendarView;

    @Override
    protected void init(VaadinRequest request) {
        User actual = (User) VaadinService.getCurrentRequest( ).getWrappedSession( ).getAttribute( "current_user" );

        //https://vaadin.com/forum#!/thread/1149367/1697779
        Page.getCurrent( ).setTitle( "Calendarlin" );
        calendarView.setUpMain( Main.this );
        if ( actual == null ){
            setContent( loginView );
        }
        else {
            setContent( calendarView );
        }

    }
}
