package com.example.CarManagement.request;
import lombok.Data;
import java.util.List;

@Data
public class BiddingRequest {

    private int carId;
    private List<Integer> salePrice;

}
