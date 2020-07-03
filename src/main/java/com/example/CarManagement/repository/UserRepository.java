package com.example.CarManagement.repository;

import com.example.CarManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> getByUserId(int userId);

}
