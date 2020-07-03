package com.example.CarManagement.enums;

import org.springframework.util.StringUtils;

public enum UserType {

    SELLER("SELLER"),BUYER("BUYER"),ADMIN("ADMIN");

    private String code;

    UserType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static boolean isValid(String type){
        if(!StringUtils.isEmpty(type)){
            for(UserType p : UserType.values()){
                if(p.toString().equals(type))
                    return true;
            }
        }
        return false;
    }


}
