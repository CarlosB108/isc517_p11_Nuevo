package com.neuronageek.Repositories;

import com.neuronageek.Entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AcMined on 10/26/2016.
 */
public interface UserRepo extends CrudRepository<User, Long> {
    User findByEmail( String email );
    User findByEmailAndPassword(String email, String password);
    User findById( Integer id );
}
