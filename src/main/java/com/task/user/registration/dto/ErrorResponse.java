package com.task.user.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    public ErrorResponse(){}

    public ErrorResponse(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
