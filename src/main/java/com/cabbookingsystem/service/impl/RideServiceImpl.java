package com.cabbookingsystem.service.impl;

import com.cabbookingsystem.dto.RideBookingRequest;
import com.cabbookingsystem.entity.*;
import com.cabbookingsystem.enums.RideStatus;
import com.cabbookingsystem.repository.*;
import com.cabbookingsystem.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    @Override
    public Ride bookRide(RideBookingRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Driver driver = driverRepository.findByAvailableTrue()
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("No drivers available"));

        driver.setAvailable(false);
        driverRepository.save(driver);

        Ride ride = Ride.builder()
                .user(user)
                .driver(driver)
                .pickupLocation(request.getPickupLocation())
                .dropoffLocation(request.getDropoffLocation())
                .fare(Math.random() * 200 + 50)
                .status(RideStatus.REQUESTED)
                .build();

        return rideRepository.save(ride);
    }

    @Override
    public void updateStatus(Long rideId, String status) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setStatus(RideStatus.valueOf(status));
        rideRepository.save(ride);
    }

    @Override
    public List<Ride> getUserRides(Long userId) {
        return rideRepository.findByUserUserId(userId);
    }
}
