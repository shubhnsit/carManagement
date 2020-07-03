package com.example.CarManagement.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiddingHistoryBean {

    private int bidId;
    private int userId;
    private int carId;
    private int amount;
}
