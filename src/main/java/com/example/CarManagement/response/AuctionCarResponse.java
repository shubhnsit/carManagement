package com.example.CarManagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionCarResponse {

    private int carId;
    private String message;
    private Boolean success;

    public static AuctionCarResponse failure(String message,int carId) {
        AuctionCarResponse response = new AuctionCarResponse();
        response.setMessage(message);
        response.setSuccess(false);
        response.setCarId(carId);
        return response;
    }

    public static AuctionCarResponse success(String message,int carId) {
        AuctionCarResponse response = new AuctionCarResponse();
        response.setMessage(message);
        response.setSuccess(true);
        response.setCarId(carId);
        return response;
    }


}
