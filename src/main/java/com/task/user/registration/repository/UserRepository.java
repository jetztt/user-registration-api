package com.task.user.registration.repository;

import com.task.user.registration.exception.UserAlreadyExistsException;
import com.task.user.registration.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User createUser(User user ) throws UserAlreadyExistsException;
}
