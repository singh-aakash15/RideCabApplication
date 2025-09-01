package com.myproject.ridecabapp.strategies.impl;

import com.myproject.ridecabapp.entities.Driver;
import com.myproject.ridecabapp.entities.Payment;
import com.myproject.ridecabapp.entities.Rider;
import com.myproject.ridecabapp.entities.Wallet;
import com.myproject.ridecabapp.entities.enums.PaymentStatus;
import com.myproject.ridecabapp.entities.enums.TransactionMethod;
import com.myproject.ridecabapp.repositories.PaymentRepository;
import com.myproject.ridecabapp.services.WalletService;
import com.myproject.ridecabapp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//Rider has 232, Driver has 500
//Ride cost is 100, out of which commision is 30, driver's cut will be 70
//rider= 232-100=132
//driver= 500+70=570
@Service
@RequiredArgsConstructor

public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),
                payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(),
                driversCut, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}

