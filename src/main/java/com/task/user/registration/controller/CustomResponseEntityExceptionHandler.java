package com.task.user.registration.controller;

import com.task.user.registration.dto.ErrorResponse;
import com.task.user.registration.exception.UserAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    public final ResponseEntity<Object> handleUserAlreadyExists(UserAlreadyExistsException ex, WebRequest request) {
               return new ResponseEntity<>(new ErrorResponse(ex.getCode(), ex.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        return handleExceptionInternal(
                ex, new ErrorResponse("Invalid " + result.getObjectName(),
                        result.getAllErrors().toString()), headers, HttpStatus.BAD_REQUEST, request);
    }
}
