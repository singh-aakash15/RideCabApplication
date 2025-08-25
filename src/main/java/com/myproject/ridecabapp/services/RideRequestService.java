package com.myproject.ridecabapp.services;


import com.myproject.ridecabapp.entities.RideRequest;
import org.springframework.stereotype.Service;

@Service
public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);


    void update(RideRequest rideRequest);
}
