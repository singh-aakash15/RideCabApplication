package com.myproject.ridecabapp.strategies.impl;

import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Payment;
import com.myproject.ridecabapp.entities.enums.PaymentStatus;
import com.myproject.ridecabapp.entities.enums.TransactionMethod;
import com.myproject.ridecabapp.repositories.PaymentRepository;
import com.myproject.ridecabapp.services.WalletService;
import com.myproject.ridecabapp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider-100
//Driver -70 deduct 30rs from driver's wallet
//does not  depend on rider's wallet
@Service
@RequiredArgsConstructor

public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
       paymentRepository.save(payment);
    }
}

