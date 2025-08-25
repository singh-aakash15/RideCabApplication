package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.RideDto;
import com.myproject.ridecabapp.dto.RideRequestDto;
import com.myproject.ridecabapp.dto.RiderDto;
import com.myproject.ridecabapp.entities.*;
import com.myproject.ridecabapp.entities.enums.RideRequestStatus;
import com.myproject.ridecabapp.entities.enums.RideStatus;
import com.myproject.ridecabapp.repositories.RideRequestRepository;
import com.myproject.ridecabapp.repositories.RiderRepository;
import com.myproject.ridecabapp.services.DriverService;
import com.myproject.ridecabapp.services.RideService;
import com.myproject.ridecabapp.services.RiderService;
import com.myproject.ridecabapp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RideService rideService;
    private final RiderRepository riderRepository;
    private final DriverService driverService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider= getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers =rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);


        // send notification to drivers
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider=getCurrentRider();
        Ride ride= rideService.getRideById(rideId);
        if(!ride.getRider().equals(rider)){
            throw new RuntimeException("Rider does not own this ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){  //verifying if ride is confirmed,only can be cancelled if status confirmed
            throw new RuntimeException("Invalid Status" + ride.getRideStatus);
        }

        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.CANCELLED);

        driverService.updateDriverAvailabilty(ride.getDriver(), true);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {

        //impelement spring security
        return riderRepository.findById(1L).orElseThrow(()-> new RuntimeException("Rider not found"));
    }
}
