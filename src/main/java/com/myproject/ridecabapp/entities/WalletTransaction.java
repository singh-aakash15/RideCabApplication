package com.myproject.ridecabapp.entities;

import com.myproject.ridecabapp.entities.enums.TransactionMethod;
import com.myproject.ridecabapp.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private TransactionType transactionType; // CREDIT or DEBIT

    private TransactionMethod transactionMethod; // RIDE or Banking

    @OneToOne
    private Ride ride;

    // we can add this transaction in wallet
    // one wallet can have many tranactions


    private String transactionId;

    @ManyToOne
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;

}
