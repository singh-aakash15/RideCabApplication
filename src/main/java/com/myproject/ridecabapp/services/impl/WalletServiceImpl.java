package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.dto.RideDto;
import com.myproject.ridecabapp.dto.WalletDto;
import com.myproject.ridecabapp.dto.WalletTransactionDto;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.User;
import com.myproject.ridecabapp.entities.Wallet;
import com.myproject.ridecabapp.entities.WalletTransaction;
import com.myproject.ridecabapp.entities.enums.TransactionMethod;
import com.myproject.ridecabapp.entities.enums.TransactionType;
import com.myproject.ridecabapp.exceptions.ResourceNotFoundException;
import com.myproject.ridecabapp.repositories.WalletRepository;
import com.myproject.ridecabapp.services.WalletService;
import com.myproject.ridecabapp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {


    private final ModelMapper modelMapper;
    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

//
//    @Override
//    public Wallet addMoneyToWallet(User user, Double amount) {
//        return null;
//    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet=findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(()-> new ResourceNotFoundException("Wallet not found"));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet= new Wallet();
        wallet.setUser(user);  //create a wallet and save it
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found"));
    }
}
