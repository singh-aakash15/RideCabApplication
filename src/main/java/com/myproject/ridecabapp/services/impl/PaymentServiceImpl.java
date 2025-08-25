package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.entities.Payment;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.enums.PaymentStatus;
import com.myproject.ridecabapp.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Override
    public void processPayment(Ride ride) {

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        return null;
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {

    }
}
