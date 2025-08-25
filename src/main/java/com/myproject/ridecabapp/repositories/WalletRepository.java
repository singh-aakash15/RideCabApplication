package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.User;
import com.myproject.ridecabapp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet>findByUser(User user);
}
