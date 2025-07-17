package com.cabbookingsystem.dto;

import lombok.Data;

@Data
public class RideBookingRequest {
    private String pickupLocation;
    private String dropoffLocation;
}
