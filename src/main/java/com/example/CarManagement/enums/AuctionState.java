package com.example.CarManagement.enums;

import org.springframework.util.StringUtils;

public enum AuctionState {

    NEW_ENTRY("NEW_ENTRY"),INITIATED("INITIATED"),SUSPENDED("SUSPENDED"),TERMINATE("TERMINATE");

    private String code;

    AuctionState(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static boolean isValid(String type){
        if(!StringUtils.isEmpty(type)){
            for(AuctionState p : AuctionState.values()){
                if(p.toString().equals(type))
                    return true;
            }
        }
        return false;
    }

}
