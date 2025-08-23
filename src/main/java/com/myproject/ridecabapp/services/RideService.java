package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.dto.RideRequestDto;
import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.RideRequest;
import com.myproject.ridecabapp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
