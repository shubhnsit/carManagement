package com.example.CarManagement.service;

import com.example.CarManagement.beans.CarBean;
import com.example.CarManagement.enums.AuctionState;
import com.example.CarManagement.mapper.BeanMapper;
import com.example.CarManagement.models.Car;
import com.example.CarManagement.models.User;
import com.example.CarManagement.repository.CarRepository;
import com.example.CarManagement.request.AuctionStateChangeRequest;
import com.example.CarManagement.response.CarAvailabilityResponse;
import com.example.CarManagement.response.CarRegistrationResponse;
import com.example.CarManagement.response.GenericErrors;
import com.example.CarManagement.response.GenericMessages;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    BeanMapper mapper = Mappers.getMapper(BeanMapper.class);

    public CarRegistrationResponse registerCar(CarBean carBean,User user) {

        carBean.setOwnerId(user.getUserId());
        carBean.setState(AuctionState.NEW_ENTRY);

        carRepository.save(mapper.carBeanToCar(carBean));
        return CarRegistrationResponse.success(GenericMessages.CAR_REGISTERED.message());

    }

    public CarAvailabilityResponse getAllAvailableCars() {
        List<CarBean> carsList = carRepository.findAll().stream().map(mapper::carToCarBean).collect(Collectors.toList());
        if(carsList.isEmpty()){
          return CarAvailabilityResponse.failure(GenericErrors.NO_AVAILABLE_CAR_ADMIN.message());
        }
        return CarAvailabilityResponse.success(carsList);
    }

    public CarAvailabilityResponse fetchCarsBySeller(User user) {

        List<CarBean> carsList = carRepository.findCarsByOwnerId(user.getUserId()).stream().map(mapper::carToCarBean).collect(Collectors.toList());
        if(carsList.isEmpty()){
            return CarAvailabilityResponse.failure(GenericErrors.NO_AVAILABLE_CAR_SELLER.message());
        }
        return CarAvailabilityResponse.success(carsList);

    }

    public CarAvailabilityResponse fetchCarsForBuyer() {
        List<CarBean> carsList = carRepository.findAll().stream().filter(c->c.getState()==AuctionState.INITIATED).map(mapper::carToCarBean).collect(Collectors.toList());
        if(carsList.isEmpty()){
            return CarAvailabilityResponse.failure(GenericErrors.NO_AVAILABLE_CAR.message());
        }
        for(CarBean carBean : carsList){
            carBean.setSalePrice(null);
        }
        return CarAvailabilityResponse.success(carsList);
    }

    public Optional<Car> getCar(int carId) {
        return carRepository.getByCarId(carId);
    }

    public void updateCarState(AuctionStateChangeRequest request) {
        carRepository.updateCarState(request.getCarId(),request.getState());
    }

    public void updateCarStateAndSalePrice(AuctionStateChangeRequest request, int salePrice) {
        carRepository.updateCarStateAndSalePrice(request.getCarId(),request.getState(),salePrice);
    }
}
