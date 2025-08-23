package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.RideDto;
import com.myproject.ridecabapp.dto.RideRequestDto;
import com.myproject.ridecabapp.dto.RiderDto;
import com.myproject.ridecabapp.entities.Rider;
import com.myproject.ridecabapp.entities.User;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);

    Rider getCurrentRider();

}
