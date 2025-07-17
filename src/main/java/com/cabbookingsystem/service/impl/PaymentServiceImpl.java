package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.PaymentRequest;
import com.cabbookingsystem.entity.*;
import com.cabbookingsystem.enums.RideStatus;
import com.cabbookingsystem.repository.*;
import com.cabbookingsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RideRepository rideRepository;
  

     @Override
    public Payment processPayment(PaymentRequest request) {
        Ride ride = rideRepository.findById(request.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        // Restrict payment to only when ride is COMPLETED
        if (ride.getStatus() != RideStatus.COMPLETED) {
            throw new RuntimeException("Payment allowed only after ride is COMPLETED");
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = ride.getUser();

        if (!user.getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized payment attempt");
        }

        Payment payment = Payment.builder()
                .ride(ride)
                .user(user)
                .amount(request.getAmount())
                .method(request.getMethod())
                .status("SUCCESS")
                .timestamp(LocalDateTime.now().toString())
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getReceipt(Long rideId) {
        return paymentRepository.findByRideRideId(rideId);
    }
}
