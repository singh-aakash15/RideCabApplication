package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.Payment;
import com.myproject.ridecabapp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
