package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Rating;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
