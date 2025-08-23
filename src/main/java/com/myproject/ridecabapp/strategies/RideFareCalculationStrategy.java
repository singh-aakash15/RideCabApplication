package com.myproject.ridecabapp.strategies;

import com.myproject.ridecabapp.entities.RideRequest;

// will calculate fare according to different strategies

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);

}
