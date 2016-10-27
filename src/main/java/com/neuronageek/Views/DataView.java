package com.neuronageek.Views;

import com.neuronageek.Entities.User;
import com.neuronageek.Services.UserService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.PopupView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AcMined on 10/27/2016.
 */

@Component
@UIScope
@SpringUI
public class DataView extends DataForm {
    @Autowired
    UserService userService;

    private User user;

    public DataView( ){
        super( );
        setSizeUndefined();
        setMargin(true);
        setSpacing(true);

        //https://vaadin.com/forum#!/thread/1925788/1930888
        update_button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        update_button.addClickListener((Button.ClickListener) event -> {
            userService.save( user );
            VaadinService.getCurrentRequest( ).getWrappedSession( ).setAttribute("current_user", user);


            ((PopupView)getParent()).setVisible(false);
            setParent( null );
        } );

        cancel_button.addClickListener( ( Button.ClickListener ) event -> {
            ((PopupView)getParent()).setVisible(false);
            setParent( null );
        } );

    }

    public void setUser( User user ) {
        this.user = user;
        BeanFieldGroup.bindFieldsUnbuffered( user, this );
    }
}
