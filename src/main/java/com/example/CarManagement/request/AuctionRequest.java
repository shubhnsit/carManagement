package com.example.CarManagement.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class AuctionRequest {

    private int userId;
    @JsonProperty("bids")
    private List<BiddingRequest> biddingRequestList;

}
