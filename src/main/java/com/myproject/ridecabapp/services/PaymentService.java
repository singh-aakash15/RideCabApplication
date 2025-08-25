package com.myproject.ridecabapp.services;

import com.myproject.ridecabapp.entities.Payment;
import com.myproject.ridecabapp.entities.Ride;
import com.myproject.ridecabapp.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
