package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.entities.RideRequest;
import com.myproject.ridecabapp.exceptions.ResourceNotFoundException;
import com.myproject.ridecabapp.repositories.RideRequestRepository;
import com.myproject.ridecabapp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
   private final RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(()-> new ResourceNotFoundException("" +
                "Ride Request not found with id :" + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
      rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Ride Request not found with id :" + rideRequest.getId()));
 rideRequestRepository.save(rideRequest); //we are updating ride request.If already exists create else update it
    }
}
