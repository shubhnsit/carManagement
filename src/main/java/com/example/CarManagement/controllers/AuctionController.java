package com.example.CarManagement.controllers;

import com.example.CarManagement.enums.AuctionState;
import com.example.CarManagement.enums.UserType;
import com.example.CarManagement.models.User;
import com.example.CarManagement.request.AuctionRequest;
import com.example.CarManagement.request.AuctionStateChangeRequest;
import com.example.CarManagement.request.BiddingRequest;
import com.example.CarManagement.request.BuyerBidRequest;
import com.example.CarManagement.response.*;
import com.example.CarManagement.service.AuctionService;
import com.example.CarManagement.service.LoginService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private LoginService loginService;

    ObjectMapper mapper = new ObjectMapper();

    @PutMapping(value = "/state")
    public ResponseEntity<GenericResponse<AuctionStateChangeResponse>> stateChange(@RequestBody @Validated AuctionStateChangeRequest request){

        Tuple2<Boolean,User> isAuthorizedUser = loginService.isAuthorizedUser(request.getUserId(),UserType.ADMIN);
        if(!isAuthorizedUser._1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse<>(null,
                    new ResponseError(401,CarRegistrationResponse.failure(GenericErrors.INVALID_USER.message()).getMessage())));
        }

        if(!AuctionState.isValid(request.getState().code())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse<>(null,
                    new ResponseError(400,CarRegistrationResponse.failure(GenericErrors.INVALID_AUCTION_STATE.message()).getMessage())));
        }

        AuctionStateChangeResponse response = auctionService.stateChange(request);
        return ResponseEntity.ok(new GenericResponse<>(response,null));

    }

    @PostMapping(value = "/create",consumes={"application/json"})
    public ResponseEntity<GenericResponse<Object>> auction(@RequestBody @Validated AuctionRequest request){

        Tuple2<Boolean,User> isAuthorizedUser = loginService.isAuthorizedUser(request.getUserId(),UserType.BUYER);
        if(!isAuthorizedUser._1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse<>(null,
                    new ResponseError(401,CarRegistrationResponse.failure(GenericErrors.INVALID_USER.message()).getMessage())));
        }

        AuctionResponse response = auctionService.auction(request);
        return ResponseEntity.ok(new GenericResponse<>(response,null));

    }


    @GetMapping(value = "/bid/show",consumes={"application/json"})
    public ResponseEntity<GenericResponse<Object>> showBids(@RequestParam("id") int userId,@RequestParam("carId") int carId,@RequestParam("type") String type){
        UserType userType = UserType.valueOf(type);

        Tuple2<Boolean,User> isAuthorizedUser = loginService.isAuthorizedUser(userId,userType);
        if(!isAuthorizedUser._1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse<>(null,
                    new ResponseError(401,CarRegistrationResponse.failure(GenericErrors.INVALID_USER.message()).getMessage())));
        }
        BiddingHistoryResponse response = new BiddingHistoryResponse();

        switch (userType){
            case ADMIN:
                response = auctionService.showAdminBids(carId);
                break;
            case BUYER:
                response = auctionService.showBuyerBids(carId,isAuthorizedUser._2);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse<>(null,
                        new ResponseError(400,CarRegistrationResponse.failure(GenericErrors.INVALID_USER_TYPE.message()).getMessage())));
        }

        return ResponseEntity.ok(new GenericResponse<>(response,null));

    }


}
