package com.cabbookingsystem.entity;

import com.cabbookingsystem.enums.RideStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Driver driver;

    private String pickupLocation;
    private String dropoffLocation;

    private double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}
