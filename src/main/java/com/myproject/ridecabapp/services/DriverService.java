package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.RideDto;
import com.myproject.ridecabapp.dto.RiderDto;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();
}
