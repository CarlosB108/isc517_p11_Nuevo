package com.neuronageek.Components;

import com.neuronageek.Entities.Event;
import com.neuronageek.Services.EventService;
import com.neuronageek.Services.UserService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by AcMined on 10/27/2016.
 */
@Component
public class Mailer {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 5000)
    public void sendEmails( ) {

        Date begin = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        cal.add(Calendar.HOUR, 1);
        Date end = cal.getTime();
        List< Event > pendingEvents = eventService.findInterval( begin, end) ;

        SendGrid sg = new SendGrid( "" );
        Request request = new Request();

        if ( pendingEvents != null && pendingEvents.size() > 0) {
            for( Event event : pendingEvents) {
                if ( event.isSended( ) ) continue;

                String email_content = "Howdy " + event.getUser( ).getNames( ) + "! \n Your event: " + event.getName( ) + " is just about to start!";

                Email from = new Email( "no-reply@servidoresactivos.com" );
                Email to   = new Email( event.getUser( ).getEmail( ) );
                Content content = new Content( "text/plain", email_content );
                String subject = event.getName() + " is coming!";

                Mail mail = new Mail( from, subject, to, content );

                try {
                    request.method = Method.POST;
                    request.endpoint = "mail/send";
                    request.body = mail.build();
                    Response response = sg.api(request);
                    event.setSended( true );

                } catch ( java.io.IOException e ) {
                    e.getStackTrace( );
                }
            }
        }
    }
}
