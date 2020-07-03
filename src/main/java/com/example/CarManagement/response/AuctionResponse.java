package com.example.CarManagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionResponse {

    private List<AuctionCarResponse> carAuctionResposneList;
    private String message;
    private boolean success;

    public static AuctionResponse failure(String message) {
        AuctionResponse response = new AuctionResponse();
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }

    public static AuctionResponse success(String message,List<AuctionCarResponse> carAuctionResposneList) {
        AuctionResponse response = new AuctionResponse();
        response.setMessage(message);
        response.setSuccess(true);
        response.setCarAuctionResposneList(carAuctionResposneList);
        return response;
    }


}
