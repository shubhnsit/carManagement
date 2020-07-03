package com.example.CarManagement.models;

import com.example.CarManagement.enums.AuctionState;
import com.example.CarManagement.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int carId;
    private int ownerId;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    private String companyName;
    private String model;
    private String manufacturedYear;
    private String kmsDriven;
    @Enumerated(EnumType.STRING)
    private AuctionState state;
    private Integer salePrice;

}
