package com.mobilunity.test.user.registration.controller;

import com.mobilunity.test.user.registration.dto.UserRequest;
import com.mobilunity.test.user.registration.entity.User;
import com.mobilunity.test.user.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userservice")
public class UserRegistrationController {

    private final UserRepository userRepository;

    @Autowired
    public UserRegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @RequestBody final UserRequest request){
        User user = request.toUser();
       return ResponseEntity.ok(userRepository.createUser(user));
    }
}
