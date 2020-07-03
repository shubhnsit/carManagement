package com.example.CarManagement.repository;

import com.example.CarManagement.enums.AuctionState;
import com.example.CarManagement.models.Car;
import com.example.CarManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer>{

    Optional<Car> getByCarId(int id);

    @Modifying
    @Query("update Car c set c.state = :state where c.carId = :carId")
    void updateCarState(@Param("carId") int carId, @Param("state") AuctionState state);

    @Modifying
    @Query("update Car c set c.state = :state,c.salePrice = :salePrice where c.carId = :carId")
    void updateCarStateAndSalePrice(@Param("carId") int carId, @Param("state") AuctionState state, @Param("salePrice") int salePrice);


    List<Car> findCarsByOwnerId(int userId);
}
