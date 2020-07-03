package com.example.CarManagement.response;

import com.example.CarManagement.beans.CarBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarAvailabilityResponse {

    private List<CarBean> cars;
    private String message;
    private Boolean success;

    public static CarAvailabilityResponse failure(String message) {
        CarAvailabilityResponse response = new CarAvailabilityResponse();
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }

    public static CarAvailabilityResponse success(List<CarBean> carBeans) {
        CarAvailabilityResponse response = new CarAvailabilityResponse();
        response.setCars(carBeans);
        return response;
    }


}
