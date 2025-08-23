package com.myproject.ridecabapp.dto;

import com.myproject.ridecabapp.entities.enums.PaymentMethod;
import com.myproject.ridecabapp.entities.enums.RideStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;




// this will have things like id,pickup loc, rider, driver.

// No need to write annotations in DTO as we are not dealing with database/spring JPA here.
public class RideDto {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime; // driver accepts the ride
    private RiderDto rider;            // RideRequest table can store requests from multiple riders
    private DriverDto driver;         // RideRequest table can store requests from multiple drivers
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private Double fare;    //calculating the fare
    private LocalDateTime startedAt; // driver starts the ride
    private LocalDateTime endedAt; // driver ends the ride
    private String otp;

}