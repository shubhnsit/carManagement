package com.example.CarManagement.request;
import com.example.CarManagement.enums.AuctionState;
import lombok.Data;

@Data
public class AuctionStateChangeRequest {

    private int userId;
    private int carId;
    private AuctionState state;

}
