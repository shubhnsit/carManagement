package com.example.CarManagement.response;

import com.example.CarManagement.beans.BiddingHistoryBean;
import com.example.CarManagement.beans.CarBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BiddingHistoryResponse {

    private List<BiddingHistoryBean> bids;
    private String message;
    private Boolean success;

    public static BiddingHistoryResponse failure(String message) {
        BiddingHistoryResponse response = new BiddingHistoryResponse();
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }

    public static BiddingHistoryResponse success(List<BiddingHistoryBean> biddingHistoryBeans) {
        BiddingHistoryResponse response = new BiddingHistoryResponse();
        response.setBids(biddingHistoryBeans);
        response.setSuccess(true);
        return response;
    }


}
