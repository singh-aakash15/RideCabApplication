package com.myproject.ridecabapp.entities;

import jakarta.persistence.*;

import java.util.List;
// Wallett, Each entity belongs to a user, a rider/driver/user can have a wallett
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;  // wallet has list of transaction
}
