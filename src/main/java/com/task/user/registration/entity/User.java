package com.task.user.registration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.user.registration.utils.HashUtil;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String plainTextPassword;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String hashedPassword;

    public User(){

    }

    public User(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String userName, @NotBlank String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.plainTextPassword = password;
        this.hashedPassword =  HashUtil.hash(password);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", plainTextPassword='" + plainTextPassword + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName);
    }
}
