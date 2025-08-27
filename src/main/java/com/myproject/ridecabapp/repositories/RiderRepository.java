package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.Rider;
import com.myproject.ridecabapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
