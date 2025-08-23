package com.myproject.ridecabapp.strategies.impl;

import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.RideRequest;
import com.myproject.ridecabapp.repositories.DriverRepository;
import com.myproject.ridecabapp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation()); //it wil lget
    }
}
