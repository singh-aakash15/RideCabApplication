package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);

    Page<Ride> findByRider(Rider rider, Pageable pageRequest);
}
