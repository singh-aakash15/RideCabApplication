package com.myproject.ridecabapp.strategies;

import com.myproject.ridecabapp.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);

}