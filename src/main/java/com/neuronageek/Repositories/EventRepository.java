package com.neuronageek.Repositories;

import com.neuronageek.Entities.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AcMined on 10/27/2016.
 */
public interface EventRepository extends CrudRepository< Event, Long>  {
    Event findById( Integer id );
}
