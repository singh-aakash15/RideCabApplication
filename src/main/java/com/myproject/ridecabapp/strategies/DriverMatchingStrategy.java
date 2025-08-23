package com.myproject.ridecabapp.strategies;

import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
