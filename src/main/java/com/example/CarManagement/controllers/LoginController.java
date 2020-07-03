package com.example.CarManagement.controllers;

import com.example.CarManagement.beans.UserBean;
import com.example.CarManagement.mapper.BeanMapper;
import com.example.CarManagement.response.GenericMessages;
import com.example.CarManagement.response.GenericResponse;
import com.example.CarManagement.response.ResponseError;
import com.example.CarManagement.service.LoginService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    BeanMapper mapper = Mappers.getMapper(BeanMapper.class);

    @GetMapping(value ="/fetch")
    public ResponseEntity<GenericResponse<UserBean>> getByUserId(@RequestParam("id") int userId){
        return ResponseEntity.ok(new GenericResponse(loginService.getByUserId(userId).map(mapper::userToUserBean),null));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<GenericResponse<String>> registerUser(@RequestBody UserBean user){
        loginService.registerUser(mapper.userBeanToUser(user));
        return ResponseEntity.ok(new GenericResponse<>(GenericMessages.USER_ADD_SUCCESSFUL.message(),null));

    }

}
