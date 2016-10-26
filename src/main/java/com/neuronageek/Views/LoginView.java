package com.neuronageek.Views;

import com.neuronageek.Entities.User;
import com.neuronageek.Services.UserService;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AcMined on 10/26/2016.
 */
@Component
@UIScope
@SpringUI
public class LoginView extends Login implements ClickListener {
    @Autowired
    UserService userService;

    private User user;

    public LoginView( ){
        super( );
        login_button.addClickListener( this );
    }

    @Override
    public void buttonClick( ClickEvent event ) {
        User user = userService.login( this.user_field.getValue(), this.password_field.getValue( ) );
        if( user != null ){
            this.user = user;
            this.getSession( ).setAttribute( "current_user", user );
            VaadinService.getCurrentRequest( ).getWrappedSession().setAttribute( "current_user", user );
            getUI( ).getPage( ).setLocation( "/" );
        }
        else {
            show_error( "The user doesn't exists!" );
        }
    }

    private void show_error( String message ) {
        Notification.show(message, Notification.Type.ERROR_MESSAGE);
    }
}
