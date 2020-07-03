package com.example.CarManagement.controllers;

import com.example.CarManagement.beans.CarBean;
import com.example.CarManagement.enums.UserType;
import com.example.CarManagement.response.*;
import com.example.CarManagement.models.User;
import com.example.CarManagement.service.CarService;
import com.example.CarManagement.service.LoginService;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/fetch")
    public ResponseEntity<GenericResponse<CarAvailabilityResponse>> fetchCars(@RequestParam("id") int userId, @RequestParam("type") String type){
        UserType userType = UserType.valueOf(type);

        Tuple2<Boolean,User> isAuthorizedUser = loginService.isAuthorizedUser(userId,userType);
        if(!isAuthorizedUser._1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse<>(null,
                    new ResponseError(401,CarRegistrationResponse.failure(GenericErrors.INVALID_USER.message()).getMessage())));
        }


        CarAvailabilityResponse response = new CarAvailabilityResponse();

        switch (userType){
            case ADMIN:
                response = carService.getAllAvailableCars();
                break;
            case BUYER:
                response = carService.fetchCarsForBuyer();
                break;
            case SELLER:
                response = carService.fetchCarsBySeller(isAuthorizedUser._2);
                break;
            default:
                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse<>(null,
                            new ResponseError(400,CarRegistrationResponse.failure(GenericErrors.INVALID_USER_TYPE.message()).getMessage())));
        }

        return ResponseEntity.ok(new GenericResponse(response,null));
    }


    @PostMapping(value = "/register")
    public ResponseEntity<GenericResponse<CarRegistrationResponse>> registerCar(@RequestBody @Validated CarBean carBean){

        Tuple2<Boolean,User> isAuthorizedUser = loginService.isAuthorizedUser(carBean.getOwnerId(),UserType.SELLER);
        if(!isAuthorizedUser._1){
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse<>(null,
                 new ResponseError(401,CarRegistrationResponse.failure(GenericErrors.INVALID_USER.message()).getMessage())));
        }

        CarRegistrationResponse response = carService.registerCar(carBean,isAuthorizedUser._2);
        return ResponseEntity.ok(new GenericResponse<>(response,null));

    }


}
