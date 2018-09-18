package com.mobilunity.test.user.registration.repository;

import com.mobilunity.test.user.registration.exception.UserAlreadyExistsException;
import com.mobilunity.test.user.registration.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User createUser( User user ) throws UserAlreadyExistsException;
}
