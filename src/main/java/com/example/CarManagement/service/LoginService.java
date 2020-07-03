package com.example.CarManagement.service;


import com.example.CarManagement.Validations.ValidationUtils;
import com.example.CarManagement.enums.UserType;
import com.example.CarManagement.models.User;
import com.example.CarManagement.repository.UserRepository;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
        return;

    }

    public Optional<User> getByUserId(int userId) {
        return userRepository.getByUserId(userId);
    }

    public Tuple2<Boolean, User> isAuthorizedUser(int userId, UserType userType) {
        Optional<User> user = userRepository.getByUserId(userId);
        if(user== null || !ValidationUtils.hasRequiredRoles(user.get(), userType)){
            return new Tuple2<>(false,null);
        }
        return new Tuple2<>(true,user.get());
    }

}
