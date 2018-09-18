package com.task.user.registration.repository;

import com.task.user.registration.exception.UserAlreadyExistsException;
import com.task.user.registration.entity.User;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserRepositoryImpl implements UserRepository {
    private ConcurrentMap<String, User> map = new ConcurrentHashMap<>();
    private static final AtomicInteger count = new AtomicInteger(0);

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        User found = map.get(user.getUserName());
        if (found != null) {
            throw new UserAlreadyExistsException();
        }
        user.setId(String.valueOf(count.incrementAndGet()));
        map.put(user.getUserName(), user);
        return map.get(user.getUserName());
    }
}
