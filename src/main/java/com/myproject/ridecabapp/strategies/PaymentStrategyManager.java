package com.myproject.ridecabapp.strategies;

import com.myproject.ridecabapp.entities.enums.PaymentMethod;
import com.myproject.ridecabapp.strategies.impl.CashPaymentStrategy;
import com.myproject.ridecabapp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


//We have to use this in our end ride method
@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;
    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
