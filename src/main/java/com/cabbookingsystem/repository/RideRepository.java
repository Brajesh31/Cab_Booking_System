package com.cabbookingsystem.repository;

import com.cabbookingsystem.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByUserUserId(Long userId);
    List<Ride> findByDriverDriverId(Long driverId);
}
