package com.task.user.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    private final String CODE;

    public UserAlreadyExistsException() {
        super("A user with the given username already exists");
        this.CODE = ExceptionCode.USER_ALREADY_EXISTS.name();
    }

    public String getCode() {
        return this.CODE;
    }
}
