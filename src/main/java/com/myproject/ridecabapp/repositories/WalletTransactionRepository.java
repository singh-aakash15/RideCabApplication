package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}
