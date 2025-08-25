package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.entities.WalletTransaction;
import com.myproject.ridecabapp.repositories.WalletTransactionRepository;
import com.myproject.ridecabapp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

    }
}
