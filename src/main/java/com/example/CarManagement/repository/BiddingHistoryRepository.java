package com.example.CarManagement.repository;

import com.example.CarManagement.models.BiddingHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BiddingHistoryRepository extends JpaRepository<BiddingHistory,Integer>{

    Optional<List<BiddingHistory>> getByCarId(int carId);
    List<BiddingHistory> getByUserIdAndCarId(int userId,int carId);

}
