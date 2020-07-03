package com.example.CarManagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionStateChangeResponse {

    private String message;
    private boolean success;

    public static AuctionStateChangeResponse failure(String message) {
        AuctionStateChangeResponse response = new AuctionStateChangeResponse();
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }

    public static AuctionStateChangeResponse success(String message) {
        AuctionStateChangeResponse response = new AuctionStateChangeResponse();
        response.setMessage(message);
        response.setSuccess(true);
        return response;
    }


}
