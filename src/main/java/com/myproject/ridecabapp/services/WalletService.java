package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.User;
import com.myproject.ridecabapp.entities.Wallet;
import com.myproject.ridecabapp.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount,
                            String transactionId, Ride ride,
                            TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount,
                                 String transactionId, Ride ride,
                                 TransactionMethod transactionMethod
                               );

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

}
