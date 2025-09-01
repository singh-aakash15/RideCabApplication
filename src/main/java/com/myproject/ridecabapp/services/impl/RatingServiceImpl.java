package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.RiderDto;
import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Rating;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.Rider;
import com.myproject.ridecabapp.exceptions.ResourceNotFoundException;
import com.myproject.ridecabapp.exceptions.RuntimeConflictException;
import com.myproject.ridecabapp.repositories.DriverRepository;
import com.myproject.ridecabapp.repositories.RatingRepository;
import com.myproject.ridecabapp.repositories.RiderRepository;
import com.myproject.ridecabapp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: "+ride.getId()));

        if(ratingObj.getDriverRating() != null)
            throw new RuntimeConflictException("Driver has already been rated, cannot rate again");

        ratingObj.setDriverRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByDriver(driver) //going to the driver
                .stream() // getting all ratings for the driver
                .mapToDouble(Rating::getDriverRating) //mapping it to double
                .average().orElse(0.0); //taking the avg
        driver.setRating(newRating); //setting the new driver rating

        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: "+ride.getId()));
        if(ratingObj.getRiderRating() != null)
            throw new RuntimeConflictException("Rider has already been rated, cannot rate again");

        ratingObj.setRiderRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);

        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
