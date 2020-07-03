package com.example.CarManagement.beans;

import com.example.CarManagement.enums.AuctionState;
import com.example.CarManagement.enums.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarBean {

    private int carId;
    private int ownerId;
    private CarType carType;
    private String companyName;
    private String model;
    private String manufacturedYear;
    private String kmsDriven;
    private AuctionState state;
    private Integer salePrice;

}
