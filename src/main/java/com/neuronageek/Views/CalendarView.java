package com.neuronageek.Views;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.VerticalLayout;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by AcMined on 10/26/2016.
 */
@Component
@UIScope
@SpringUI
public class CalendarView extends VerticalLayout {

    Calendar cal;

    //https://github.com/vacax/SpringBootVaadin/blob/master/src/main/groovy/edu/pucmm/sbv/vistas/addons/PruebaCalendario.groovy
    public CalendarView( ){
        cal = new Calendar( "My events" );
        cal.setLocale(Locale.US);
        cal.setFirstVisibleDayOfWeek( 2 );   //Lunes
        cal.setLastVisibleDayOfWeek( 6 );   // Viernes
        cal.setFirstVisibleHourOfDay( 8 ); // 8 am
        cal.setLastVisibleHourOfDay( 17 ); // 5 pm
        cal.setSizeFull( );

        this.setSizeFull( );
        this.addComponent( cal );
    }
}
