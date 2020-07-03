package com.example.CarManagement.service;

import com.example.CarManagement.beans.BiddingHistoryBean;
import com.example.CarManagement.beans.CarBean;
import com.example.CarManagement.enums.AuctionState;
import com.example.CarManagement.mapper.BeanMapper;
import com.example.CarManagement.models.User;
import com.example.CarManagement.repository.BiddingHistoryRepository;
import com.example.CarManagement.request.AuctionRequest;
import com.example.CarManagement.request.AuctionStateChangeRequest;
import com.example.CarManagement.request.BuyerBidRequest;
import com.example.CarManagement.response.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    BeanMapper mapper = Mappers.getMapper(BeanMapper.class);

    @Autowired
    private CarService carService;

    @Autowired
    private BiddingHistoryRepository repo;

    @Transactional
    public AuctionStateChangeResponse stateChange(AuctionStateChangeRequest request) {

        Optional<CarBean> car = carService.getCar(request.getCarId()).map(mapper::carToCarBean);
        if(car.isPresent()){
            AuctionState existingState = car.get().getState();
            AuctionState newState = request.getState();
            if(isValidAuctionState(newState,existingState)){
               if(newState.equals(AuctionState.TERMINATE)) {
                   Optional<Integer> maxBidAmount= repo.getByCarId(request.getCarId()).get().stream().max(Comparator.comparing(c -> c.getAmount())).map(c -> c.getAmount());
                   carService.updateCarStateAndSalePrice(request, maxBidAmount.get());
               }
               else {
                   carService.updateCarState(request);
               }
                return AuctionStateChangeResponse.success(String.format(GenericMessages.AUCTION_STATE_CHANGED.message(),existingState,newState));
            }
            else{
                return AuctionStateChangeResponse.failure(String.format(GenericErrors.INVALID_AUCTION_STATE_MOVE.message(),existingState,newState));
            }
        }
        else {
            return AuctionStateChangeResponse.failure(GenericErrors.CAR_NOT_FOUND.message());
        }

    }

    private boolean isValidAuctionState(AuctionState newState, AuctionState existingState) {

        switch (existingState){
            case NEW_ENTRY:
                if(newState.equals(AuctionState.INITIATED))
                    return true;
            case INITIATED:
                if(newState.equals(AuctionState.SUSPENDED) ||
                        newState.equals(AuctionState.TERMINATE))
                    return true;
            case SUSPENDED:
            case TERMINATE:
                default:
                    return false;
        }

    }

    public AuctionResponse auction(AuctionRequest request) {
       List<AuctionCarResponse> auctionCarResponseList = new ArrayList<>();
        try {
            int userId = request.getUserId();
           request.getBiddingRequestList().stream().forEach(br -> {
               Optional<CarBean> car = carService.getCar(br.getCarId()).map(mapper::carToCarBean);
               if(car.isPresent() && car.get().getState().equals(AuctionState.INITIATED)){
                   auctionCarResponseList.add(AuctionCarResponse.success(GenericErrors.CAR_REGISTERED_FOR_AUCTION.message(),car.get().getCarId()));
                   br.getSalePrice().stream().forEach(sp -> {
                       BiddingHistoryBean biddingHistoryBean = BiddingHistoryBean.builder().amount(sp).
                               carId(br.getCarId()).userId(userId).build();
                       repo.save(mapper.biddingHistoryBeanToBiddingHistory(biddingHistoryBean));
                   });
               }else{
                   auctionCarResponseList.add(AuctionCarResponse.failure(GenericErrors.CAR_NOT_REGISTERED_FOR_AUCTION.message(),car.get().getCarId()));
               }
           });
       }
       catch(Exception e){
           e.printStackTrace();
           return AuctionResponse.failure(GenericErrors.AUCTION_NOT_COMPLETED.message());
       }

       return AuctionResponse.success(GenericMessages.BIDDING_REQUEST_SUCCESSFUL.message(),auctionCarResponseList);


    }

    public BiddingHistoryResponse showBuyerBids(int carId, User user) {
        List<BiddingHistoryBean> biddingHistoryBeans =  repo.getByUserIdAndCarId(user.getUserId(),carId).stream().
                map(mapper::biddingHistoryToBiddingHistoryBean).collect(Collectors.toList());

        if(biddingHistoryBeans.isEmpty()){
            return BiddingHistoryResponse.failure(GenericErrors.NO_BIDDING_HISTORY_FOUND.message());
        }
        return BiddingHistoryResponse.success(biddingHistoryBeans);
    }

    public BiddingHistoryResponse showAdminBids(int carId) {
        List<BiddingHistoryBean> biddingHistoryBeans =  repo.getByCarId(carId).get().stream().
                map(mapper::biddingHistoryToBiddingHistoryBean).collect(Collectors.toList());

        if(biddingHistoryBeans.isEmpty()){
            return BiddingHistoryResponse.failure(GenericErrors.NO_BIDDING_HISTORY_FOUND.message());
        }

        return BiddingHistoryResponse.success(biddingHistoryBeans);
    }

}

