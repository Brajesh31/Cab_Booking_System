package com.cabbookingsystem.service;

import com.cabbookingsystem.dto.RideBookingRequest;
import com.cabbookingsystem.entity.Ride;

import java.util.List;

public interface RideService {
    Ride bookRide(RideBookingRequest request);
    void updateStatus(Long rideId, String status);
    List<Ride> getUserRides(Long userId);
}
