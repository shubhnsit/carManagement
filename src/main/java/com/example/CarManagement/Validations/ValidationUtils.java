package com.example.CarManagement.Validations;

import com.example.CarManagement.enums.UserType;
import com.example.CarManagement.models.User;

public class ValidationUtils {

    public static boolean hasRequiredRoles(User user,UserType userType){

        if(user.getUserType() == userType)
            return true;

        return false;

    }

}
