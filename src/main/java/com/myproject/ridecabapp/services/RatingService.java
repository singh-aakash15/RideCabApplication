package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.dto.DriverDto;
import com.myproject.ridecabapp.dto.RiderDto;
import com.myproject.ridecabapp.entities.Ride;

    public interface RatingService {

        DriverDto rateDriver(Ride ride, Integer rating);
        RiderDto rateRider(Ride ride, Integer rating);

        void createNewRating(Ride ride);
    }

