package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.User;
import com.myproject.ridecabapp.entities.Wallet;
import com.myproject.ridecabapp.entities.enums.TransactionMethod;
import com.myproject.ridecabapp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    @Override
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        return null;
    }

    @Override
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        return null;
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return null;
    }

    @Override
    public Wallet createNewWallet(User user) {
        return null;
    }

    @Override
    public Wallet findByUser(User user) {
        return null;
    }
}
