package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.PaymentRequest;
import com.cabbookingsystem.entity.Payment;

public interface PaymentService {
    Payment processPayment(PaymentRequest request);
    Payment getReceipt(Long rideId);
}
