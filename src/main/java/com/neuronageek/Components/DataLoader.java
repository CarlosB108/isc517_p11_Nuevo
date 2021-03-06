package com.neuronageek.Components;

import com.neuronageek.Entities.User;
import com.neuronageek.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AcMined on 10/26/2016.
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepo userRepository;
    boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if ( alreadySetup )
            return;

        User user = userRepository.findByEmail( "acmined@gmail.com" );
        if( user == null ) {
            user = new User( );
            user.setNames( "Augusto Fideligno" );
            user.setLast_names( "Local" );
            user.setPassword( "1234" );
            user.setEmail( "acmined@gmail.com" );
            userRepository.save( user );
        }

        alreadySetup = true;
    }
}