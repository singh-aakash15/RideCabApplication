package com.myproject.ridecabapp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_rating_rider", columnList = "rider_id"),
        @Index(name = "idx_rating_driver", columnList = "driver_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@OneToOne
    private Ride ride;

@ManyToOne
    private Driver driver;

@ManyToOne
    private Rider rider;

private Integer driverRating; //rating of driver
    private Integer riderRating; //rating of rider
}
