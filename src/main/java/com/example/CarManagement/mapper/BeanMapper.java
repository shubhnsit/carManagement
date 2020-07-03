package com.example.CarManagement.mapper;

import com.example.CarManagement.beans.BiddingHistoryBean;
import com.example.CarManagement.beans.CarBean;
import com.example.CarManagement.beans.UserBean;
import com.example.CarManagement.models.BiddingHistory;
import com.example.CarManagement.models.Car;
import com.example.CarManagement.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeanMapper {

    CarBean carToCarBean(Car car);
    Car carBeanToCar(CarBean carBean);

    UserBean userToUserBean(User user);
    User userBeanToUser(UserBean userBean);

    BiddingHistoryBean biddingHistoryToBiddingHistoryBean(BiddingHistory biddingHistory);
    BiddingHistory biddingHistoryBeanToBiddingHistory(BiddingHistoryBean biddingHistoryBean);

}
