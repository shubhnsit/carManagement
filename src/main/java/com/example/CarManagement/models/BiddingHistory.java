package com.example.CarManagement.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"userId", "carId","amount"})
)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BiddingHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int bidId;
    private int userId;
    private int carId;
    private int amount;

}

