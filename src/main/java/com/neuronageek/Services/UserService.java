package com.neuronageek.Services;

import com.neuronageek.Entities.User;
import com.neuronageek.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AcMined on 10/26/2016.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepo userRepository;

    public User login(String email, String password ) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    public boolean existe( String email ) {
        User users = userRepository.findByEmail( email );
        return users != null;
    }

    public User getUserByEmail( String email ){
        return userRepository.findByEmail( email );
    }
    public User getUserById( Integer id ){
        return userRepository.findById( id );
    }

    public List< User > listAll() {
        return ( List<  User  > ) userRepository.findAll( );
    }

    public void save(User user) {
        userRepository.save( user );
    }

    public void delete( Integer id) {
        User found = userRepository.findOne( (long)id );

        if(found != null) {
            userRepository.delete(found);
        }
    }
}