package com.example.CarManagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarRegistrationResponse {

    private String message;
    private boolean success;

    public static CarRegistrationResponse failure(String message) {
        CarRegistrationResponse response = new CarRegistrationResponse();
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }

    public static CarRegistrationResponse success(String message) {
        CarRegistrationResponse response = new CarRegistrationResponse();
        response.setMessage(message);
        response.setSuccess(true);
        return response;
    }


}
