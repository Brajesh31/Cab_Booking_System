package com.cabbookingsystem.dto;

import com.cabbookingsystem.enums.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {
    private Long rideId;
    private Double amount;
    private PaymentMethod method;
}
