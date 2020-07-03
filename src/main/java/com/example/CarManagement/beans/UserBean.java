package com.example.CarManagement.beans;

import com.example.CarManagement.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {

    private int userId;
    private String userName;
    private String email;
    private UserType userType;

}
