package com.mobilunity.test.user.registration.dto;

import com.mobilunity.test.user.registration.entity.User;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserRequest implements Serializable {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public User toUser(){
        return new User(getFirstName(),
                getLastName(),
                getUserName(),
                getPassword());
    }
}
