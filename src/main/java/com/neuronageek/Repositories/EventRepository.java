package com.neuronageek.Repositories;

import com.neuronageek.Entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by AcMined on 10/27/2016.
 */
public interface EventRepository extends CrudRepository< Event, Long>  {
    Event findById( Integer id );

    @Query( "SELECT e FROM Event e WHERE e.start BETWEEN ?1 AND ?2 AND e.sended = false" )
    List< Event > findInterval(Date begin, Date end );
}
