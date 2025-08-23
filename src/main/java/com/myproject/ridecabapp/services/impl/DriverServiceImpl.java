package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.RideDto;
import com.myproject.ridecabapp.dto.RiderDto;
import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.RideRequest;
import com.myproject.ridecabapp.entities.enums.RideRequestStatus;
import com.myproject.ridecabapp.entities.enums.RideStatus;
import com.myproject.ridecabapp.exceptions.ResourceNotFoundException;
import com.myproject.ridecabapp.repositories.DriverRepository;
import com.myproject.ridecabapp.services.DriverService;
import com.myproject.ridecabapp.services.RideRequestService;
import com.myproject.ridecabapp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {


    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest=rideRequestService.findRideRequestById(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride Request cannot be accepted" + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver=getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver is not available");
        }

        currentDriver.setAvailable(false);
        Driver savedDriver=driverRepository.save(currentDriver);

        Ride ride=rideService.createNewRide(rideRequest,savedDriver); // creating a new ride

        return modelMapper.map(ride,RideDto.class);

//        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {
        // we need to check if driver owns this ride
        Ride ride= rideService.getRideById(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver does not own this ride");
        }


       if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
           throw new RuntimeException("Ride cannot be started as ride status is not confirmed");
        }

       if(otp.equals(ride.getOtp())){
           throw new RuntimeException("OTP not valid");
       }
       // update ride status to started
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.ONGOING);
       return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }
    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(1L).orElseThrow(()->new ResourceNotFoundException("Driver not found"));
    }
}
