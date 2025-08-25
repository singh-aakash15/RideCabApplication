package com.myproject.ridecabapp.entities;

import com.myproject.ridecabapp.entities.enums.PaymentMethod;
import com.myproject.ridecabapp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Ride {

    public String getRideStatus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime createdTime; // driver accepts the ride

    @ManyToOne(fetch = FetchType.LAZY)   // RideRequest table can store requests from multiple riders
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;   // RideRequest table can store requests from multiple drivers

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private String otp;

    private Double fare; //calculating fare
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
